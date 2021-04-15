package cn.com.lichenghao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示tryLock(long time, TimeUnit t) 等待后获取锁，
 * 如果等待了还获取不到就返回false;如果线程被中断了，就取消等待，并且抛出异常
 */
public class LockTest06 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest06 lockTest05 = new LockTest06();
        Thread t1 = new Thread(() -> lockTest05.print());
        Thread t2 = new Thread(() -> lockTest05.print());
        t1.start();
        t2.start();
        // 调用interrupt()会终止等待锁
        //t2.interrupt();
    }

    public void print() {
        try {
            boolean b = lock.tryLock(2, TimeUnit.SECONDS);
            try {
                if (b) {
                    System.out.println(Thread.currentThread().getName() + "--获得锁");
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + "--" + i);
                    }
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    System.out.println(Thread.currentThread().getName() + "--没有获得锁");
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
