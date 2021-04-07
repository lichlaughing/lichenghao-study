package cn.com.lichenghao.sync.communication;

import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示notify唤醒wait的线程
 */
public class NotifyTest01 {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "- 开始等待.....");
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "- 等待结束!");
        });
        thread.start();
        // 1秒后唤醒
        TimeUnit.SECONDS.sleep(1);
        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}
