package cn.edu.xjtu.cad.hehe.cai_kg.spider.provider;

import cn.edu.xjtu.cad.hehe.cai_kg.spider.remover.ProxyRemover;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler.ProxyScheduler;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
public class MyProxyProvider implements ProxyProvider {
    private Logger LOGGER = LoggerFactory.getLogger(MyProxyProvider.class);

    private final static int proxyPoolSize = 12;

    private AtomicBoolean inited = new AtomicBoolean(false);

    private final BlockingQueue<Proxy> proxies;

    private final String TEST_URL = "http://old.wanfangdata.com.cn";

    private final String PROXY_URL = "http://www.xsdaili.com/get?orderid=198232494481816&num=10&an_ha=1&sp1=1&dedup=1&format=json&gj=1";

    private Spider spider;

    public MyProxyProvider() {
        this(new ArrayBlockingQueue<>(proxyPoolSize,true));
    }

    public MyProxyProvider(ArrayBlockingQueue<Proxy> proxies) {
        this.proxies = proxies;
    }


    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        if(!inited.get()){
            initSpider();
        }
        //Donothing
        if(validateProxy(proxy)){
            proxies.add(proxy);
            LOGGER.info("代理放回代理池"+proxy);
        }
    }

    private List<Proxy> xsProxy(String content) {
        return JSONObject.parseObject(content).getJSONArray("data").toJavaList(String.class).stream().map(d -> {
            String[] arr = d.split(":");
            return new Proxy(arr[0], Integer.valueOf(arr[1]));
        }).collect(Collectors.toList());
    }

    private synchronized void initSpider() {
        if(!inited.get()) {
            this.spider = Spider.create(new PageProcessor() {
                private Site site = new Site().setRetryTimes(3).setSleepTime(6000);

                @Override
                public void process(Page page) {
                    page.putField("content", page.getRawText());
                    page.addTargetRequest(page.getRequest());
                }

                @Override
                public Site getSite() {

                    return this.site;
                }
            }).setScheduler(new ProxyScheduler(new Request(PROXY_URL)))
                    .addUrl(PROXY_URL)
                    .addPipeline((resultItems, task) -> {
                        String content = resultItems.get("content");
                        if (content != null && content.trim().length() > 0) {
                            xsProxy(content).forEach(proxy -> {
                                try {
                                    proxies.put(proxy);
                                    LOGGER.info("新增代理" + proxy);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    })
                    .thread(1);
             this.spider.start();
             inited.set(true);
        }
    }

    private boolean validateProxy(Proxy proxy) {
        int statusCode = 0;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpGet = new HttpGet(TEST_URL);
            RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(proxy.getHost(), proxy.getPort()))
                    .setSocketTimeout(2000)
                    .setConnectTimeout(2000)
                    .setConnectionRequestTimeout(2000).build();
            httpGet.setConfig(config);
            LOGGER.info("正在验证代理的可用性");
            //执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 获取响应实体
            try {
                statusCode = response.getStatusLine().getStatusCode();
            } catch (Exception e){

            }finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
        } catch (Exception e) {
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return statusCode == 200;
        }
    }


    @Override
    public Proxy getProxy(Task task) {
        if(!inited.get()){
            initSpider();
        }
        Proxy proxy = null;
        while (proxy==null){
            LOGGER.info("当前代理池可用代理数目:"+this.proxies.size());
            try {
                proxy = this.proxies.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(validateProxy(proxy)){
                LOGGER.info("当前代理可用"+proxy.toString());
            }else {
                LOGGER.info("当前代理不可用可用"+proxy);
                proxy=null;
            }
        }
        LOGGER.info("采用代理"+proxy);
        return proxy;
    }


}
