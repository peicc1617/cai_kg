package cn.edu.xjtu.cad.hehe.cai_kg.spider.processor;

import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.stream.Collectors;

public class PaperURLProcess implements PageProcessor {
    Site site = new Site()
            .me()
            .setTimeOut(10000)
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setCycleRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

    @Override
    public void process(Page page) {
        //获取html
        Html html = page.getHtml();
        //解析文献链接
        html.css("p.pager a").links().all().forEach(href->page.addTargetRequest(Utils.clipSearchURL(href)));
        List<Paper> paperList = html.css(".record-title a.title").all().stream().map(a->{
            Element e =  Jsoup.parse(a).select("a").first();
            Paper paper = new Paper();
            paper.setHref(e.attr("href"));
            paper.setTitle(e.text());
            return paper;
        }).collect(Collectors.toList());
        page.putField("paperList",paperList);
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
