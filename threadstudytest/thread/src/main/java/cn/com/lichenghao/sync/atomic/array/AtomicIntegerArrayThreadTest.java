package cn.com.lichenghao.sync.atomic.array;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author chenghao.li
 * 演示多线程下使用原子数组,给数组中的每个元素自增 1000
 */
public class AtomicIntegerArrayThreadTest {
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Sub();
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicIntegerArray);
    }

    static class Sub extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 10; j++) {
                    // 给每个元素自增 1000
                    atomicIntegerArray.incrementAndGet(j % 10);
                }
            }
        }
    }
}
