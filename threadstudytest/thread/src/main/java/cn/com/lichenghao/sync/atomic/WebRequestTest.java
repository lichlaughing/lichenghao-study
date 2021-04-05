package cn.com.lichenghao.sync.atomic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 模拟网页请求
 */
public class WebRequestTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicLongCounter counter = AtomicLongCounter.getInstance();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                counter.requestReceive();
                int r = new Random().nextInt(1000);
                if (r % 2 == 0) {
                    counter.successRequestReceive();
                } else {
                    counter.failRequestReceive();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        long requestCount = counter.getRequestCount();
        long successRequestCount = counter.getSuccessRequestCount();
        long failRequestCount = counter.getFailRequestCount();
        System.out.println("请求总数：" + requestCount);
        System.out.println("请求成功总数：" + successRequestCount);
        System.out.println("请求失败总数：" + failRequestCount);
    }
}
