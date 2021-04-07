package cn.com.lichenghao.sync.communication;

import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示notifyAll唤醒wait的线程
 */
public class NotifyTest02 {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "- 开始等待.....");
                synchronized (LOCK) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "- 等待结束!");
            }).start();
        }

        // 2秒后唤醒所有等待的线程
        TimeUnit.SECONDS.sleep(2);
        synchronized (LOCK) {
            LOCK.notifyAll();
        }
    }
}
