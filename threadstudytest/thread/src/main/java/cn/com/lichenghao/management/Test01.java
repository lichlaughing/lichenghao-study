package cn.com.lichenghao.management;

/**
 * @author chenghao.li
 * 演示：捕获线程的异常
 */
public class Test01 {
    public static void main(String[] args) {
        // 设置全局异常回调
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + "线程产生异常：" + e.getMessage());
        });
        new Thread(() -> {
            System.out.println(1 / 0);
        }).start();

        new Thread(() -> {
            String str = null;
            String[] split = str.split(".");
            System.out.println(split);
        }).start();
    }
}
