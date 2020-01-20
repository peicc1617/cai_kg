package cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline;


import cn.edu.xjtu.cad.hehe.cai_kg.dao.UrlMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.dao.WordCloudMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
public class WordCloudPipeline implements Pipeline {
    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WordCloudPipeline.class);

    @Autowired
    WordCloudMapper wordCloudMapper;
    @Autowired
    UrlMapper urlMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        String word = resultItems.get("word");
        try {
            //转码
            word = URLDecoder.decode(word,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            List<String> cloudWords =  resultItems.get("cloudWords");
            List<String> targets =  resultItems.get("targets");
            if(word!=null&&word.trim().length()>0&&cloudWords.size()>0){
                LOGGER.info("当前词:"+word+",词云:"+cloudWords.toString());
                wordCloudMapper.add(word,cloudWords.toString());
            }else {
                LOGGER.info("当前链接不存在词云，链接："+resultItems.getRequest().getUrl());
            }
            //将当前搜索链接设置为完成状态
            urlMapper.delete("search_url",resultItems.getRequest().getUrl());
            //新的搜索链接添加到searchURL2数据库中
            cloudWords.forEach(w->{
                w=w.trim();
                if(!wordCloudMapper.contains(w)){
                    wordCloudMapper.set(w);
                }
            });
            //不直接添加到数据库
//            for (String target : targets) {
//                //防止链接重复
//                if(!urlMapper.contains("search_url2",target)){
//                    urlMapper.add("search_url2",target);
//                }
//            }
        }

    }
}
