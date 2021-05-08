package cn.com.lichenghao.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示：线程池吞掉异常
 */
public class Test04 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1
                , 5
                , 0
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(5)
                , Executors.defaultThreadFactory());

        /*threadPoolExecutor.submit(() -> {
            System.out.println(10 / 0);
        });*/

        threadPoolExecutor.execute(() -> {
            System.out.println(10 / 0);
        });
    }
}
