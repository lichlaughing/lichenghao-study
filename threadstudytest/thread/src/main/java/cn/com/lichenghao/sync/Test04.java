package cn.com.lichenghao.sync;

/**
 * @author chenghao.li
 * 演示死锁
 */
public class Test04 {
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> deadLock.methodA()).start();
        new Thread(() -> deadLock.methodB()).start();
    }

    static class DeadLock {
        private static final String LOCK_A = "LOCK_A";
        private static final String LOCK_B = "LOCK_B";

        public void methodA() {
            synchronized (LOCK_A) {
                System.out.println(Thread.currentThread().getName() + "- 拿到了 A 锁,继续拿 B 锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_B) {
                    System.out.println(Thread.currentThread().getName() + "- 拿到了 B 锁！");
                }
            }
        }

        public void methodB() {
            synchronized (LOCK_B) {
                System.out.println(Thread.currentThread().getName() + "- 拿到了 B 锁,继续拿 A 锁");
                synchronized (LOCK_A) {
                    System.out.println(Thread.currentThread().getName() + "- 拿到了 A 锁！");
                }
            }
        }
    }
}
