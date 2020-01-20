package cn.edu.xjtu.cad.hehe.cai_kg.service;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.SeedWordMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.dao.WordCloudMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.WordCloud;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline.PaperInfoPipeline;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Service
public class SpiderService {

    @Autowired
    SeedWordMapper seedWordMapper;


    @Autowired
    WordCloudMapper wordCloudMapper;
    @Autowired
    PaperInfoPipeline paperInfoPipeline;

    @Autowired
    Spider paperInfoSpider;


    @Autowired
    Spider wordCloudSpider;

    @Autowired
    Spider paperURLSpider;

    public SpiderService() {

    }


    public void configureSpider() {
        this.startSpider();
    }

    public static void main(String[] args) {
        SpiderService spiderService = new SpiderService();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("httpProxy", "你的代理ip：端口");
        ChromeOptions options = new ChromeOptions();

    }


    public void startSpider() {
        this.paperURLSpider.start();
        this.paperInfoSpider.start();
    }

    /**
     * 获取种子词
     *
     * @return
     */
    private List<String> getSeedWord() {
        return seedWordMapper.getSeedWordFromCur(1000);
    }

    /**
     * 获取爬虫数据演示
     */
    public JSONArray getSpiderShow() {
        final int ddddd = 40;
        List<WordCloud> wordClouds = wordCloudMapper.getAll();
        Date date1 = wordClouds.get(0).getCreateTime();
        Date date2 = wordClouds.get(wordClouds.size() - 1).getCreateTime();
        double dateDelta = (date2.getTime() - date1.getTime()) / (ddddd-1.0);//获取时间差,分割成20个时间段
        Map<String, Integer> wordAndGroup = new HashMap<>();
        long originTime = date1.getTime();
        Map<String, Map<String, Integer>> mapMap = new HashMap<>();
        wordClouds.forEach(wordCloud -> {
            long time = wordCloud.getCreateTime().getTime();//收录时间
            int group = Math.min(ddddd,(int) ((time - originTime) / dateDelta));//所述分组
            if(group>=ddddd){
                System.out.println(wordCloud);
            }
            //对词云进行处理，得到当前词云的所有词，用set去重
            Set<String> wordSet = new HashSet<>();
            String curWord = "";
            try {
                curWord = URLDecoder.decode(wordCloud.getWord(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (curWord.trim().length() > 0) {
                wordSet.add(curWord);
            }
            String cloud = wordCloud.getReleatedWord();
            if (cloud != null && cloud.trim().length() > 0) {
                for (String s : cloud.subSequence(1, cloud.length() - 1).toString().split(",")) {
                    wordSet.add(s.trim());
                }
            }
            //对当前词云的所有词进行分组
            wordSet.forEach(word -> {
                if (wordAndGroup.containsKey(word) && wordAndGroup.get(word) < group) {
                    //如果当前词已有分组且分组小于后来的分组，那么说明之前就出现过，不进行处理
                } else {
                    //如果当前词没有分组，那么创建词的分组
                    wordAndGroup.put(word, group);
                }

            });
            //同时设置当前词与其他词之间的共现关系
            Map<String, Integer> map = mapMap.getOrDefault(curWord, new HashMap<>());
            for (String target : wordSet) {
                if (!target.equals(curWord)) {
                    //保证不与自己连线
                    map.put(target, map.getOrDefault(target, 0) + 1);
                }
            }
            mapMap.put(curWord, map);
        });
        JSONArray res = new JSONArray();
        for (int i = 0; i < ddddd; i++) {
            JSONObject obj = new JSONObject();
            obj.put("nodes", new JSONArray());
            obj.put("links", new JSONArray());
            res.add(obj);
        }
        for (Map.Entry<String, Integer> e : wordAndGroup.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", e.getKey());
            jsonObject.put("group", e.getValue());
            res.getJSONObject(e.getValue()).getJSONArray("nodes").add(jsonObject);
        }
        for (Map.Entry<String, Map<String, Integer>> e1 : mapMap.entrySet()) {
            String source = e1.getKey();
            for (Map.Entry<String, Integer> e2 : e1.getValue().entrySet()) {
                String target = e2.getKey();
                int value = e2.getValue();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("source", source);
                jsonObject.put("target", target);
                jsonObject.put("value", value);
                if(!wordAndGroup.containsKey(source) || !wordAndGroup.containsKey(target) || res.getJSONObject(Math.max(wordAndGroup.get(source), wordAndGroup.get(target)))==null){
                    System.out.println(e2);
                }
                res.getJSONObject(Math.max(wordAndGroup.get(source), wordAndGroup.get(target))).getJSONArray("links").add(jsonObject);
            }
        }

        System.out.println(res);

        return res;
    }

}
