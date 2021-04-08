package cn.com.lichenghao.sync.communication;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chenghao.li
 * 演示wait条件发生改变，引起的问题
 */
public class WaitTest05 {

    public static void main(String[] args) throws InterruptedException {
        Sub sub = new Sub();
        // 一百个线程取值，一百个线程设置值
        for (int i = 0; i < 100; i++) {
            new Thread(() -> sub.getStr()).start();
            new Thread(() -> sub.setStr()).start();
        }
    }

    static class Sub {
        static String str = "";

        public void setStr() {
            synchronized (this) {
                while (StringUtils.isNotBlank(str)) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                str = "hello";
                System.out.println(Thread.currentThread().getName() + "- 设置值成功!");
                this.notifyAll();
            }
        }

        public void getStr() {
            synchronized (this) {
                while (StringUtils.isBlank(str)) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "- 取值成功!>" + str);
                str = "";
                this.notifyAll();
            }
        }
    }
}
