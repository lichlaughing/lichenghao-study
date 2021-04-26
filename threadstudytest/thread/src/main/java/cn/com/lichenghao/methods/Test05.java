package cn.com.lichenghao.methods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示getWaitQueueLength(Condition condition)获取等待条件的线程数
 */
public class Test05 {
    static ReentrantLock LOCK = new ReentrantLock();
    static Condition condition = LOCK.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Test05 test05 = new Test05();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> test05.m1()).start();
        }
        TimeUnit.SECONDS.sleep(1);
        try {
            LOCK.lock();
            System.out.println("等待condition的线程数" + LOCK.getWaitQueueLength(condition));
        } finally {
            LOCK.unlock();
        }
        // 唤醒
        try {
            LOCK.lock();
            condition.signalAll();
            System.out.println("等待condition的线程数" + LOCK.getWaitQueueLength(condition));
        } finally {
            LOCK.unlock();
        }
    }

    public void m1() {
        try {
            LOCK.lock();
            System.out.println(Thread.currentThread().getName() + "-开始等待！");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
