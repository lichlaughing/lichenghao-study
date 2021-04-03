package cn.com.lichenghao.sync.Volatile;

/**
 * @author chenghao.li
 * 演示 volatile 使变量在线程之间可见，但不是原子操作
 */
public class Test06 {
    public static void main(String[] args) throws InterruptedException {
        Sub sub = new Sub();
        new Thread(() -> sub.sayHello()).start();
        // 使用volatile,修改状态，线程可见
        Thread.sleep(2000);
        sub.setFlag(false);
    }

    static class Sub {
        volatile private boolean flag = true;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void sayHello() {
            System.out.println("start ...");
            while (flag) {

            }
            System.out.println("end ...");
        }
    }
}
