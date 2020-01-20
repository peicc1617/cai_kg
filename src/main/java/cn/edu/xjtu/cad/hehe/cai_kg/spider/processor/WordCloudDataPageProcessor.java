package cn.edu.xjtu.cad.hehe.cai_kg.spider.processor;

import cn.edu.xjtu.cad.hehe.cai_kg.spider.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 解析关键词云的Processor
 */
public class WordCloudDataPageProcessor implements PageProcessor {

    private static final String SEARCH_LIST = ".*s.wanfangdata.com.cn/Paper.aspx.*";

    //配置
    Site site = new Site()
            .me()
            .setTimeOut(10000)
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setCycleRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

    @Override
    public void process(Page page) {
        //        初始化设置
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);//关闭图片显示
        options.setExperimentalOption("prefs",prefs);
        options.addArguments("--headless");//不显示窗口
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);//设置超时时间为30s
        driver.get(page.getUrl().toString());
        //获取词云
        List<String> cloudWords = new ArrayList<>();
        //词云的链接
        List<String> targets  = new ArrayList<>();
        driver.findElements(By.cssSelector("#relatedword ul li a"))
                .stream()
                .forEach(a->{
                    String url = Utils.clipSearchURL(a.getAttribute("href"));
                    if(url.matches(SEARCH_LIST)){
                        cloudWords.add(a.getText());
//                        page.addTargetRequest(url);
//                        targets.add(url);
                    }
                });
        //获取当前的关键字
        page.putField("word", Utils.getURLParam(page.getUrl().toString(),"q").get(0));
        //词云
        page.putField("cloudWords",cloudWords);
        page.putField("targets",cloudWords);
        driver.quit();
        page.setDownloadSuccess(true);
    }



    @Override
    public Site getSite() {
        return this.site;
    }
}
