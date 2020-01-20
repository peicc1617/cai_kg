package cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.PaperMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.dao.UrlMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 保存paperURL
 */
@Component
public class PaperURLPipeline implements Pipeline {

    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PaperURLPipeline.class);

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    UrlMapper urlMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Paper> paperList = resultItems.get("paperList");
        if(paperList!=null){
            LOGGER.info("成功获取了文献链接"+paperList);
            paperList.forEach(paper -> {
                //将文献链接保存到数据库
                if(!paperMapper.has(paper.getHref())){
                    //确定数据库中的文献链接不重复
                    paperMapper.addURL(paper);
                }else {
                    LOGGER.info("当前文献已存在");
                }
            });
        }
        //将当前searchURL设置为已爬取
        urlMapper.delete("search_url2",resultItems.getRequest().getUrl());
    }
}
