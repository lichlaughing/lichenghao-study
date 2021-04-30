package cn.com.lichenghao.methods;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示 isFair() 和 isHeldByCurrentThread()
 */
public class Test07 {
    static ReentrantLock LOCK = new ReentrantLock(true);

    public static void main(String[] args) {
        Test07 test07 = new Test07();
        new Thread(() -> test07.m1()).start();
    }

    public void m1() {
        try {
            System.out.println(LOCK.isFair());
            System.out.println(LOCK.isHeldByCurrentThread());
            LOCK.lock();
            System.out.println(LOCK.isHeldByCurrentThread());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } finally {
            if (LOCK.isHeldByCurrentThread()) {
                LOCK.unlock();
            }
        }
    }
}
