package cn.com.lichenghao.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示await()/signal()
 */
public class ConditionTest01 {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Sub sub = new Sub();
        sub.start();
        TimeUnit.SECONDS.sleep(1);
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-等待...");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "-等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
