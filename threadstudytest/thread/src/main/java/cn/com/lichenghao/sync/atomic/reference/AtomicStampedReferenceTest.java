package cn.com.lichenghao.sync.atomic.reference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author chenghao.li
 * 演示AtomicStampedReference解决 CAS ABA 问题
 */
public class AtomicStampedReferenceTest {
    private static AtomicStampedReference<String> atomicStampedReference =
            new AtomicStampedReference<>("ABC", 0);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            atomicStampedReference.compareAndSet("ABC", "DEF", atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "-" + atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet("DEF", "ABC", atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        }).start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet("ABC", "NEW ABC", stamp, stamp + 1);
            System.out.println(b);
        }).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(atomicStampedReference.getReference());
    }
}
