package cn.edu.xjtu.cad.hehe.cai_kg.spider.processor;

import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.print.Doc;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 解析文献信息的Processor
 */
public class PaperInfoProcessor implements PageProcessor {


    static Map<String,String> cnMap = new HashMap<String,String>(){{
        put("T-0","工业技术理论");
        put("T-1","工业技术现状与发展");
        put("T-2","机构、团体、会议");
        put("T-6","参考工具书");
        put("T-9","工业经济");
        put("TB","一般工业技术");
        put("TD","矿业工程");
        put("TE","石油、天然气工业");
        put("TF","冶金工业");
        put("TG","金属学与金属工艺");
        put("TH","机械、仪表工业");
        put("TJ","武器工业");
        put("TK","能源与动力工程");
        put("TL","原子能技术");
        put("TP","自动化技术、计算机技术");
        put("TQ","化学工业");
        put("TS","轻工业、手工业");
        put("F2","经济计划与管理");
        put("F4","工业经济");
        put("X7","废物处理与综合利用");
        put("X9","安全科学");
        put("V2","航空");
        put("V4","航天（宇宙航行）");

    }};

    Site site = new Site()
            .me()
            .setTimeOut(5000)
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

    @Override
    public void process(Page page) {
        Document doc = Jsoup.parse(page.getHtml().toString());
        String cn = getCN(doc);
        String ca = getCA(cn);
        if(cn!=null){
            //只有满足特定领域的才会收集
            Paper paper = new Paper(
                    page.getUrl().toString(),
                    doc.select(".row-keyword .text a").eachText().stream().collect(Collectors.joining(";")),
                    doc.toString(),
                    doc.select(".baseinfo-feild.abstract .text").first().text(),
                    cn,
                    ca);
            page.putField("paper",paper);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    /**
     * 解析分类号
     * @param document
     * @return
     */
    String getCN(Document document){
        String cn = null;
        if(document!=null){
            Elements elements = document.select(".baseinfo-feild .row");
            if(elements!=null){
                for (Element element : elements) {
                    String attr = element.select("span.pre").text().trim();
                    if(attr.equals("分类号：")){
                        cn = element.select("span.text").text();
                    }
                }
            }
        }
        return cn;
    }

    /**
     * 根据分类号得到分类
     * @param cnText
     * @return
     */
    String getCA(String cnText){
        String ca = null;
        if(cnText!=null&&cnText.trim().length()>0){
            List<String> cnList = Arrays.stream(cnText.split(" ")).filter(k->k.trim().length()>0).collect(Collectors.toList());
            List<String> caList = cnList.stream().map(cn->{
                for (String key : cnMap.keySet()) {
                    if(cn.toUpperCase().contains(key)){
                        return cnMap.get(key);
                    }
                }
                return null;
            }).filter(e->e!=null).collect(Collectors.toList());
            ca = caList.stream().collect(Collectors.joining(";"));
        }
        return ca;
    }

}
