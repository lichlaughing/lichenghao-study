package cn.com.lichenghao.methods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示getQueueLength()返回等待获取锁的线程数
 */
public class Test02 {
    static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> test02.m1()).start();
        }
    }

    public void m1() {
        try {
            LOCK.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("等待锁的线程数：" + LOCK.getQueueLength());
            for (int i = 0; i < 5; i++) {
                // System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
