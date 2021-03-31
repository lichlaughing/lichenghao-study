package cn.com.lichenghao.sync;

/**
 * @author Chenghao.li
 * 演示 synchronized 修改代码块，修饰实例方法，修饰静态方法
 */
public class Test02 {

    public static void main(String[] args) {

        // 修饰代码块
        SumWithLock sumWithLock = new SumWithLock();
        for (int i = 0; i < 2; i++) {
            new Thread(sumWithLock).start();
        }

        // 修饰实例方法
        Test02 t02 = new Test02();
        for (int i = 0; i < 2; i++) {
            // 必须是相同的锁
            new Thread(() -> t02.sum()).start();
            // 这个是不同的锁对象，所以不是同步的
            new Thread(() -> new Test02().sum()).start();
        }

        // 修饰静态方法
        for (int i = 0; i < 2; i++) {
            new Thread(() -> Test02.sum2()).start();
        }
    }

    /**
     * 同步代码块
     */
    static class SumWithLock implements Runnable {
        int sum = 0;

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 10000; i++) {
                    sum++;
                }
                System.out.println(sum);
            }
        }
    }

    /**
     * 同步实例方法
     */
    public synchronized void sum() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }

    /**
     * 同步静态方法
     */
    public synchronized static void sum2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}
