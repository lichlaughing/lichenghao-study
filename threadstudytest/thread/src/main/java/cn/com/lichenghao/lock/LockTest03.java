package cn.com.lichenghao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示中断线程，依然会获得的锁的情况
 */
public class LockTest03 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockTest03 lockTest03 = new LockTest03();
        Runnable r1 = () -> lockTest03.print1();
        Runnable r2 = () -> lockTest03.print2();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r2);
        Thread t4 = new Thread(r2);
        t1.start();
        t2.start();
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        // 中断线程，也会获得锁
        t4.start();
        t4.interrupt();
    }

    public void print1() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "--A");
            }
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "--B");
            }
        } finally {
            lock.unlock();
        }
    }
}
