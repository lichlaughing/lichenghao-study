package cn.com.lichenghao.sync.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenghao.li
 * 演ThreadLocal增加资源
 */
public class ThreadLocalTest01 {

    static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Sub()).start();
        }
    }

    static class Sub implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 60; i++) {
                try {
                    if (simpleDateFormatThreadLocal.get() == null) {
                        simpleDateFormatThreadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    }
                    Date parse = simpleDateFormatThreadLocal.get().parse("2020-01-01 10:10:" + i % 60);
                    System.out.println(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
