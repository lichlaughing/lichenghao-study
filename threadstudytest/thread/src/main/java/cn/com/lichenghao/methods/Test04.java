package cn.com.lichenghao.methods;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示hasQueuedThreads(Thread thread)返回true/false,指定的线程是否在等待获取锁
 */
public class Test04 {
    static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Test04 test04 = new Test04();
        Runnable runnable = () -> test04.m1();
        Thread t1 = new Thread(runnable);
        t1.setName("t1");
        Thread t2 = new Thread(runnable);
        t2.setName("t2");
        Thread t3 = new Thread(runnable);
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
        System.out.println(LOCK.hasQueuedThread(t1));
        System.out.println(LOCK.hasQueuedThread(t2));
        System.out.println(LOCK.hasQueuedThread(t3));
    }

    public void m1() {
        try {
            LOCK.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } finally {
            LOCK.unlock();
        }
    }
}
