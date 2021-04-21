package cn.com.lichenghao.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示Condition/await(),signalAll()实现交替打印
 */
public class ConditionTest04 {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean flag = false;

    public static void main(String[] args) {
        ConditionTest04 conditionTest04 = new ConditionTest04();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    conditionTest04.printA();
                }
            }).start();
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    conditionTest04.printB();
                }
            }).start();
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
            condition.signalAll();
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
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
