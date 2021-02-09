package cn.com.lichenghao.locks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 通过定时任务模拟竞争锁
 */
@Component
public class Task {

    @Autowired
    private Lock lock;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void task1() throws InterruptedException {
        Boolean test1 = lock.getLock("test1", 5);
        System.out.println("test1获取锁:" + test1);
        Thread.sleep(3000);
        Boolean aBoolean = lock.releaseLock();
        System.out.println("test1释放锁:" + test1);
    }

    @Scheduled(cron = "0/10 * * * * ? ")
    public void task2() {
        Boolean test1 = lock.getLock("test1", 5);
        System.out.println("test2获取锁:" + test1);
        Boolean aBoolean = lock.releaseLock();
        System.out.println("test2释放锁:" + test1);
    }
}
