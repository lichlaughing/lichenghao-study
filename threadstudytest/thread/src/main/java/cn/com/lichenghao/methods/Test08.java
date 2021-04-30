package cn.com.lichenghao.methods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenghao.li
 * 演示 isLocked() 判断锁是否被线程持有
 */
public class Test08 {
    static ReentrantLock LOCK = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Test08 test08 = new Test08();
        new Thread(() -> test08.m1()).start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(LOCK.isLocked());
    }

    public void m1() {
        try {
            System.out.println(LOCK.isLocked());
            LOCK.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(LOCK.isLocked());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (LOCK.isHeldByCurrentThread()) {
                LOCK.unlock();
            }
        }
    }
}
