package cn.edu.xjtu.cad.hehe.cai_kg.config;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.UrlMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline.PaperInfoPipeline;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline.PaperURLPipeline;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline.WordCloudPipeline;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.processor.PaperInfoProcessor;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.processor.PaperURLProcess;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.processor.WordCloudDataPageProcessor;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.provider.MyProxyProvider;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler.MysqlScheduler;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler.PageInfoScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

@Configuration
public class SpiderConfig {

    private Logger LOGGER = LoggerFactory.getLogger(SpiderConfig.class);

    @Autowired
    UrlMapper urlMapper;

    @Autowired
    WordCloudPipeline wordCloudPipeline;

    @Autowired
    PaperURLPipeline paperURLPipeline;

    @Autowired
    PaperInfoPipeline paperInfoPipeline;

    @Autowired
    PageInfoScheduler pageInfoScheduler;
    @Autowired
    MyProxyProvider myProxyProvider;

    /**
     * 得到词云解析的spider对象
     * @return
     */
    @Bean(name = "wordCloudSpider")
    public Spider wordCloudSpider() {
        //判断是windows还是linux
//        String driverPath = chromeDriverPathWindows;
        String os = System.getProperty("os.name");
        if(!os.toLowerCase().contains("win")){
        }else {
//            LOGGER.info("当前系统为linux,chromeDriver指定目录:" + chromeDriverPathWindows);
            System.setProperty("webdriver.chrome.driver","D:/webmagic/chromedriver_win32/chromedriver.exe");
        }
        return Spider.create(new WordCloudDataPageProcessor())
                .setScheduler(new MysqlScheduler(urlMapper,"search_url"))
                .addUrl("http://s.wanfangdata.com.cn/Paper.aspx?q=%E7%B2%BE%E7%9B%8A%E7%94%9F%E4%BA%A7")
                .addPipeline(wordCloudPipeline)
                .thread(6);
    }

    /**
     * 得到文献链接的spider对象
     * @return
     */
    @Bean(name = "paperURLSpider")
    public Spider paperURLSpider() {
        return Spider.create(new PaperURLProcess())
                .setScheduler(new MysqlScheduler(urlMapper,"search_url2"))
                .addUrl("http://s.wanfangdata.com.cn/Paper.aspx?q=%E7%B2%BE%E7%9B%8A%E7%94%9F%E4%BA%A7")
                .addPipeline(paperURLPipeline)
                .thread(4);
    }

    @Bean(name = "paperInfoSpider")
    public Spider paperInfoSpider(){
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(myProxyProvider);
        return Spider.create(new PaperInfoProcessor())
                .setScheduler(pageInfoScheduler)
                .setDownloader(httpClientDownloader)
                .addPipeline(paperInfoPipeline)
                .thread(14);
    }





}
