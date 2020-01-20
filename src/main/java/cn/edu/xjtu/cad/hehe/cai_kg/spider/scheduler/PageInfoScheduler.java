package cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.PaperMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
public class PageInfoScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {


    private final Logger LOGGER = LoggerFactory.getLogger(PageInfoScheduler.class);
    @Autowired
    PaperMapper paperMapper;

    private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

    private AtomicBoolean inited = new AtomicBoolean(false);



    public PageInfoScheduler() {
    }

    private void init(){
        queue.addAll(paperMapper.getAllTo().stream().map(url->new Request(url)).collect(Collectors.toList()));
        LOGGER.info("当前需要采集的文献数目为:"+queue.size());
        inited.getAndSet(true);
    }
    @Override
    public int getLeftRequestsCount(Task task) {
        if(!inited.get()){
            init();
        }
        return queue.size();

    }

    @Override
    public int getTotalRequestsCount(Task task) {
        if(!inited.get()){
            init();
        }
        return getDuplicateRemover().getTotalRequestsCount(task);
    }

    @Override
    public Request poll(Task task) {
        if(!inited.get()){
            init();
        }
        return queue.poll();
    }
}
