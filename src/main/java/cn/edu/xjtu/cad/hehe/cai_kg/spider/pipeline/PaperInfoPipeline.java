package cn.edu.xjtu.cad.hehe.cai_kg.spider.pipeline;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.PaperMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;



@Component
public class PaperInfoPipeline implements Pipeline {
    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PaperInfoPipeline.class);

    @Autowired
    PaperMapper  paperMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Paper paper = resultItems.get("paper");
        if(paper!=null){
            long id = paperMapper.getPaperID(paper.getHref());
            paper.setId(id);
            LOGGER.info("获取到的文献信息"+paper);
            //更新基本信息
            paperMapper.updatePaper(paper);
            //保存摘要
            paperMapper.savePaperAbstr(id,paper.getAbstr());
            //保存html
            paperMapper.savePaperHtml(id,paper.getHtml());
        }
    }
}
