package cn.com.lichenghao.methods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示hasWaiters(Condition condition)方法，判断是否有线程在等待condition的条件
 */
public class Test06 {
    static ReentrantLock LOCK = new ReentrantLock();
    static Condition condition = LOCK.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Test06 test06 = new Test06();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> test06.m1()).start();
        }
        TimeUnit.SECONDS.sleep(1);
        try {
            LOCK.lock();
            System.out.println("是否有线程在等待condition的条件：" + LOCK.hasWaiters(condition));
        } finally {
            LOCK.unlock();
        }
        // 唤醒
        try {
            LOCK.lock();
            condition.signalAll();
            System.out.println("是否有线程在等待condition的条件：" + LOCK.hasWaiters(condition));
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
