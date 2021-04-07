package cn.com.lichenghao.sync.communication;

/**
 * @author chenghao.li
 * 演示wait线程等待
 */
public class WaitTest01 {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "- 开始等待.....");
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
