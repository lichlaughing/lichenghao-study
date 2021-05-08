package cn.com.lichenghao.threadpool;

import java.util.concurrent.*;

/**
 * @author chenghao.li
 * 演示：扩展线程池，重写submit方法
 */
public class Test05 {
    public static void main(String[] args) {
        OrgThreadPool orgThreadPool = new OrgThreadPool(1
                , 5
                , 0
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(5));

        orgThreadPool.submit(() -> {
            System.out.println(10 / 0);
        });
    }

    /**
     * 自定义线程池，重写submit方法
     */
    static class OrgThreadPool extends ThreadPoolExecutor {

        public OrgThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public Runnable wrap(Runnable task, Exception exception) {
            return () -> {
                try {
                    task.run();
                } catch (Exception e) {
                    exception.printStackTrace();
                    throw e;
                }
            };
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, new Exception("OrgException")));
        }
    }
}
