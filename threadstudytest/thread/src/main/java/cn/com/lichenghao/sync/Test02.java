package cn.com.lichenghao.sync;

import org.junit.Test;

/**
 * @author Chenghao.li
 * 演示 synchronized 同步，原子操作
 */
public class Test01 {

    @Test
    public void t1() {
        SumWithOutLock sumWithOutLock = new SumWithOutLock();
        for (int i = 0; i < 2; i++) {
            new Thread(sumWithOutLock).start();
        }
    }

    @Test
    public void t2() {
        SumWithLock sumWithLock = new SumWithLock();
        for (int i = 0; i < 2; i++) {
            new Thread(sumWithLock).start();
        }
    }

    static class SumWithOutLock implements Runnable {
        int sum = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                sum++;
            }
            System.out.println(sum);
        }
    }

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
}
