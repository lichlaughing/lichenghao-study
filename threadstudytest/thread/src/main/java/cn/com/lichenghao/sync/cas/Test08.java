package cn.com.lichenghao.sync.cas;

/**
 * @author chenghao.li
 * 演示 CAS 原子操作
 */
public class Test08 {
    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                long l = casCounter.incrementAndGet();
                System.out.println(l);
            }).start();
        }
    }
}

/**
 * 原子计数器
 */
class CASCounter {
    private volatile long value = 0;

    public long getValue() {
        return value;
    }

    public boolean compareAndSwap(long expected, long newValue) {
        synchronized (this) {
            if (this.value == expected) {
                this.value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

    public long incrementAndGet() {
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }
}