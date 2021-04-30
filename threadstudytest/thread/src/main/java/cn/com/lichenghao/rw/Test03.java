package cn.com.lichenghao.rw;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenghao.li
 * 演示：写锁互斥
 */
public class Test03 {
    public static void main(String[] args) {
        Sub sub = new Sub();
        new Thread(() -> sub.read()).start();
        new Thread(() -> sub.write()).start();
    }

    static class Sub {
        ReadWriteLock rw = new ReentrantReadWriteLock();

        public void read() {
            try {
                rw.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "-获取读锁-" + System.currentTimeMillis());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "-读完毕-" + System.currentTimeMillis());
                rw.readLock().unlock();
            }
        }

        public void write() {
            try {
                rw.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "-获取写锁-" + System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "-写完毕-" + System.currentTimeMillis());
                rw.writeLock().unlock();
            }
        }
    }
}
