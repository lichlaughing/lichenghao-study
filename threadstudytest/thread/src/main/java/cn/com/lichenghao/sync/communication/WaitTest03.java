package cn.com.lichenghao.sync.communication;

/**
 * @author chenghao.li
 * 演示wait-notify组合中通知过早的问题
 */
public class WaitTest03 {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
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

        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                LOCK.notify();
                System.out.println(Thread.currentThread().getName() + "- notify通知了.....");
            }
        });

        t2.start();
        t1.start();
    }
}
