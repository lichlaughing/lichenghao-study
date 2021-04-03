package cn.com.lichenghao.sync.Volatile;

/**
 * @author chenghao.li
 * 演示 volatile 修饰变量，不是原子操作
 */
public class Test07 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Sub().start();
        }
    }

    static class Sub extends Thread {
        private volatile static int num = 0;

        public void sum() {
            for (int i = 0; i < 10000; i++) {
                num++;
            }
        }

        @Override
        public void run() {
            sum();
            System.out.println(num);
        }
    }
}
