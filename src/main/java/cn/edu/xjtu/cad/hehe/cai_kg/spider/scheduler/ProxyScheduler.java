package cn.edu.xjtu.cad.hehe.cai_kg.spider.scheduler;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.concurrent.atomic.AtomicInteger;

public class ProxyScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {
    Request request;
    private AtomicInteger hasProcess = new AtomicInteger(0);

    public ProxyScheduler(Request request) {
        this.request = request;
        setDuplicateRemover(
                new DuplicateRemover() {
                    @Override
                    public boolean isDuplicate(Request request, Task task) {
                       return false;
                    }

                    @Override
                    public void resetDuplicateCheck(Task task) {

                    }

                    @Override
                    public int getTotalRequestsCount(Task task) {
                        return hasProcess.get();
                    }
                });
    }

    @Override
    public int getLeftRequestsCount(Task task) {
        return 1;
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return hasProcess.get();
    }

    @Override
    public Request poll(Task task) {
        hasProcess.getAndAdd(1);
        return request;
    }
}
