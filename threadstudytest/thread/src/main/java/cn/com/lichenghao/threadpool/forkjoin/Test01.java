package cn.com.lichenghao.threadpool.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author chenghao.li
 * 演示：ForkJoinPool 计算1+2+3+...+10000求和
 */
public class Test01 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountRecursiveTask countRecursiveTask = new CountRecursiveTask(0, 10000);
        ForkJoinTask<Long> submit = forkJoinPool.submit(countRecursiveTask);
        try {
            Long res = submit.get();
            System.out.println("计算结果：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CountRecursiveTask extends RecursiveTask<Long> {

        // 分解的任务数量
        private static final int TASK_COUNT = 100;
        // 判断需要分解任务的阈值
        private static final int HOLD = 1000;

        private long start;
        private long end;

        public CountRecursiveTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            Long sum = 0L;
            if (end - start < HOLD) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                List<CountRecursiveTask> subTask = new ArrayList<>();
                long step = (end - start) / TASK_COUNT;
                long pos = start;
                for (int i = 0; i < TASK_COUNT; i++) {
                    long endIndex = pos + step;
                    if (endIndex > end) {
                        endIndex = end;
                    }
                    CountRecursiveTask countRecursiveTask = new CountRecursiveTask(pos, endIndex);
                    subTask.add(countRecursiveTask);
                    countRecursiveTask.fork();
                    pos += step + 1;
                }
                for (CountRecursiveTask countRecursiveTask : subTask) {
                    sum += countRecursiveTask.join();
                }
            }
            return sum;
        }
    }
}
