package cn.com.lichenghao.threadpool;

import java.util.concurrent.*;

/**
 * @author chenghao.li
 * 演示：线程池的扩展
 */
public class Test03 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5
                , 10
                , 0
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(5)
        ) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("线程：" + t.getId() + ",准备执行任务" + ((Task) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((Task) r).name + "任务执行完毕");
            }
        };

        // 执行多个任务
        for (int i = 0; i < 10; i++) {
            Task task = new Task("task:" + i);
            threadPoolExecutor.execute(task);
        }
    }

    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("任务：" + name + "执行了");
        }
    }
}
