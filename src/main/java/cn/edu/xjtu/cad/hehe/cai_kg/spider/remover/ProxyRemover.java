package cn.edu.xjtu.cad.hehe.cai_kg.spider.remover;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

public class ProxyRemover implements DuplicateRemover {
    private int cnt = 0;
    @Override
    public boolean isDuplicate(Request request, Task task) {
        cnt++;
        return false;
    }

    @Override
    public void resetDuplicateCheck(Task task) {

    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return cnt;
    }
}
