package cn.com.lichenghao.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示多个Condition，部分通知
 */
public class ConditionTest02 {
    static Lock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionTest02 conditionTest02 = new ConditionTest02();
        new Thread(() -> conditionTest02.m1()).start();
        new Thread(() -> conditionTest02.m2()).start();
        TimeUnit.SECONDS.sleep(1);
        conditionTest02.signalM1();
    }

    public void m1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "等待...");
            c1.await();
            System.out.println(Thread.currentThread().getName() + "等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalM1() {
        try {
            lock.lock();
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "等待...");
            c2.await();
            System.out.println(Thread.currentThread().getName() + "等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalM2() {
        try {
            lock.lock();
            c2.signal();
        } finally {
            lock.unlock();
        }
    }
}
