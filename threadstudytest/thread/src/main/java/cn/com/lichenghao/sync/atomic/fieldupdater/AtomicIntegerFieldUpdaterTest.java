package cn.com.lichenghao.sync.atomic.fieldupdater;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author chenghao.li
 * 演示多线程下对某个属性进行修改
 */
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) throws InterruptedException {
        User user = new User("1", 0);
        for (int i = 0; i < 10; i++) {
            new Sub(user).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(user);
    }

    static class Sub extends Thread {
        private User user;

        public Sub(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                atomicIntegerFieldUpdater.incrementAndGet(user);
            }
        }
    }
}
