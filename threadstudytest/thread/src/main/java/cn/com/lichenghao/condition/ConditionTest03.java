package cn.com.lichenghao.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示Condition实现交替打印
 */
public class ConditionTest03 {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean flag = false;

    public static void main(String[] args) {
        ConditionTest03 conditionTest03 = new ConditionTest03();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> conditionTest03.printA()).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> conditionTest03.printB()).start();
        }
    }

    public void printA() {
        try {
            lock.lock();
            while (flag) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "-A");
            flag = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            while (!flag) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "-B");
            flag = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
