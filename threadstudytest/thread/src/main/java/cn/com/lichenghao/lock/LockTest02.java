package cn.com.lichenghao.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示锁的可重入性
 */
public class LockTest02 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest02 lockTest01 = new LockTest02();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> lockTest01.print()).start();
        }
    }

    public void print() {
        lock.lock();
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "--" + i);
            }
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
}
