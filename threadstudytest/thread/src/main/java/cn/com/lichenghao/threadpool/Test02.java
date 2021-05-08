package cn.com.lichenghao.threadpool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示：线程池的计划任务
 */
public class Test02 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);

        // 延迟一段时间后执行任务
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + "-延迟任务执行：" + LocalTime.now());
        }, 2, TimeUnit.SECONDS);

        // 以固定的频率执行任务，开启任务的时间是固定的。如下3秒后开始执行，任务完成后2秒周期执行
        // 如果任务执行时长超过了时间间隔，那么任务完成后继续执行下个任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-周期任务执行：" + LocalTime.now());
        }, 3, 2, TimeUnit.SECONDS);

        // 上次任务结束后，在固定的延迟后，再次执行任务
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-周期任务执行：" + LocalTime.now());
        }, 3, 2, TimeUnit.SECONDS);
    }
}
