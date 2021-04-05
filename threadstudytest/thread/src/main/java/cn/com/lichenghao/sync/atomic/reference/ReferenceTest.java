package cn.com.lichenghao.sync.atomic.reference;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chenghao.li
 */
public class ReferenceTest {

    private static AtomicReference atomicReference = new AtomicReference<>("ABC");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (atomicReference.compareAndSet("ABC", "DEF")) {
                    System.out.println(Thread.currentThread().getName() + "修改为 DEF");
                }
            }).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (atomicReference.compareAndSet("DEF", "ABC")) {
                    System.out.println(Thread.currentThread().getName() + "修改为 ABC");
                }
            }).start();
        }
    }
}
