package cn.com.lichenghao.sync.communication;

import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示interrupt终止线程
 */
public class WaitTest02 {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "- 开始等待.....");
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "- 线程被终止!");
                }
            }
        });
        thread.start();
        // 一秒后终止线程
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        boolean interrupted = thread.isInterrupted();
        System.out.println(interrupted);
    }
}
