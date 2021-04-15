package cn.com.lichenghao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示tryLock(),不会等待获取不到锁就返回false
 */
public class LockTest05 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest05 lockTest05 = new LockTest05();
        Thread t1 = new Thread(() -> lockTest05.print());
        Thread t2 = new Thread(() -> lockTest05.print());
        t2.start();
        t1.start();
    }

    public void print() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "--获得锁");
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "--没有获得锁");
        }
    }
}
