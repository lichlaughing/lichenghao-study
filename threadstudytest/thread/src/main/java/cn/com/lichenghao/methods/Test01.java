package cn.com.lichenghao.methods;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示getHoldCount()返回锁调用lock的次数
 */
public class Test01 {
    static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> new Test01().m1()).start();
    }

    public void m1() {
        try {
            LOCK.lock();
            System.out.println(LOCK.getHoldCount());
            m2();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void m2() {
        try {
            LOCK.lock();
            System.out.println(LOCK.getHoldCount());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } finally {
            LOCK.unlock();
        }
    }
}
