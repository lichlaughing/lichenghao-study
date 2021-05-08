package cn.com.lichenghao.threadpool;

import java.util.concurrent.*;

/**
 * @author chenghao.li
 * 演示：使用Callable任务
 */
public class Test06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1
                , 5
                , 0
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(5)
                , Executors.defaultThreadFactory());
        Future<Integer> submit = threadPoolExecutor.submit(new MyTask(10, 0));
        Integer integer = submit.get();
        System.out.println(integer);
    }

    static class MyTask implements Callable<Integer> {

        private Integer a;
        private Integer b;

        public MyTask(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            return a / b;
        }
    }
}
