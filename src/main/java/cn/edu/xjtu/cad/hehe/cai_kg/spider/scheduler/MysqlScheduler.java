package cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.UrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MysqlScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {

    private Logger LOGGER = LoggerFactory.getLogger(MysqlScheduler.class);

    UrlMapper urlMapper;

    private AtomicBoolean inited = new AtomicBoolean(false);

    private AtomicInteger hasProcess = new AtomicInteger(0);
    private BlockingQueue<Request> queue = new LinkedBlockingDeque<>();

    String tableName;

    public MysqlScheduler(UrlMapper urlMapper,String tableName) {
        this.urlMapper = urlMapper;
        this.tableName = tableName;
        initDuplicateRemover();
    }

    private void initDuplicateRemover() {
        setDuplicateRemover(
                new DuplicateRemover() {
                    @Override
                    public boolean isDuplicate(Request request, Task task) {
                        if (!inited.get()) {
                            init(task);
                        }
                        return urlMapper.contains(tableName,request.getUrl());
                    }

                    @Override
                    public void resetDuplicateCheck(Task task) {


                    }

                    @Override
                    public int getTotalRequestsCount(Task task) {
                        return urlMapper.size(tableName);
                    }
                });
    }

    private void init(Task task) {
        readDB();
        this.inited.set(true);
    }

    private void readDB() {
        List<String> urls = urlMapper.getAll(this.tableName);
        urls.forEach(url->queue.add(new Request(url)));
    }

    @Override
    public void pushWhenNoDuplicate(Request request, Task task) {
        if (!inited.get()) {
            init(task);
        }
        queue.add(request);
        urlMapper.add(tableName,request.getUrl());
    }

    @Override
    public Request poll(Task task) {
        if (!inited.get()) {
            init(task);
        }
        hasProcess.getAndAdd(1);
        LOGGER.info("当前已处理数据"+hasProcess.get());
        Request request = queue.poll();
        if(request==null)
            return null;
//        urlMapper.delete(tableName,request.getUrl());
        return request;
    }

    @Override
    public int getLeftRequestsCount(Task task) {
        return queue.size();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return getDuplicateRemover().getTotalRequestsCount(task);
    }

}
