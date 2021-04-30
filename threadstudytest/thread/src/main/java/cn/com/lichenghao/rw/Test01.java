package cn.com.lichenghao.rw;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenghao.li
 * 演示：读写锁的读读共享
 */
public class Test01 {
    public static void main(String[] args) {
        Sub sub = new Sub();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> sub.read()).start();
        }
    }

    static class Sub {
        ReadWriteLock rw = new ReentrantReadWriteLock();

        public void read() {
            try {
                rw.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "-获取读锁-" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rw.readLock().unlock();
            }
        }
    }
}
