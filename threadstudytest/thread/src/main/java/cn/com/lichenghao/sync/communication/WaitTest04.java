package cn.com.lichenghao.sync.communication;

/**
 * @author chenghao.li
 * 演示wait-notify组合中解决通知过早的问题
 */
public class WaitTest04 {
    private static final Object LOCK = new Object();
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "- 开始等待.....");
            synchronized (LOCK) {
                while (flag) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + "- 线程被终止!");
                    }
                }
                System.out.println(Thread.currentThread().getName() + "- 等待结束！");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                LOCK.notify();
                flag = false;
                System.out.println(Thread.currentThread().getName() + "- notify通知了.....");
            }
        });

        t2.start();
        t1.start();
    }
}
