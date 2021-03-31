package cn.com.lichenghao.sync;

/**
 * @author Chenghao.li
 * 演示 对象锁、类锁、常量锁
 */
public class Test03 {

    private static final String LOCK = "LOCK";

    public static void main(String[] args) {
        // 对象锁
        Test03 t1 = new Test03();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> t1.print()).start();
        }
        // 类锁
        for (int i = 0; i < 2; i++) {
            new Thread(() -> new Test03().print2()).start();
        }
        // 常量锁
        for (int i = 0; i < 2; i++) {
            new Thread(() -> new Test03().print3()).start();
        }
    }

    public void print() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        }
    }

    public void print2() {
        synchronized (Test03.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        }
    }

    public void print3() {
        synchronized (LOCK) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        }
    }
}
