package cn.com.lichenghao.methods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示hasQueuedThreads()返回true/false,是否有线程在等待获取锁
 */
public class Test03 {
    static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Test03 test03 = new Test03();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> test03.m1()).start();
        }
    }

    public void m1() {
        try {
            LOCK.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("是否有线程在等待锁：" + LOCK.hasQueuedThreads());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
