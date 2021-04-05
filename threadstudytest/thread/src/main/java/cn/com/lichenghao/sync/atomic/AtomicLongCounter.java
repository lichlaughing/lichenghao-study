package cn.com.lichenghao.sync.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenghao.li
 * 单例模式,使用AtomicLongCounter模拟计数器。记录网站的请求总数，成功数，失败数
 */
public class AtomicLongCounter {
    private volatile static AtomicLongCounter atomicLongCounter;

    private static final AtomicLong REQUEST_COUNT = new AtomicLong(0);
    private static final AtomicLong SUCCESS_REQUEST_COUNT = new AtomicLong(0);
    private static final AtomicLong FAIL_REQUEST_COUNT = new AtomicLong(0);

    private AtomicLongCounter() {

    }

    public static AtomicLongCounter getInstance() {
        if (atomicLongCounter == null) {
            synchronized (AtomicLongCounter.class) {
                if (atomicLongCounter == null) {
                    atomicLongCounter = new AtomicLongCounter();
                }
            }
        }
        return atomicLongCounter;
    }

    /**
     * 新的请求
     */
    public void requestReceive() {
        REQUEST_COUNT.incrementAndGet();
    }

    /**
     * 成功的请求
     */
    public void successRequestReceive() {
        SUCCESS_REQUEST_COUNT.incrementAndGet();
    }

    /**
     * 失败的请求
     */
    public void failRequestReceive() {
        FAIL_REQUEST_COUNT.incrementAndGet();
    }

    /**
     * 插件请求总数
     *
     * @return
     */
    public long getRequestCount() {
        return REQUEST_COUNT.get();
    }

    /**
     * 插件成功的请求总数
     *
     * @return
     */
    public long getSuccessRequestCount() {
        return SUCCESS_REQUEST_COUNT.get();
    }

    /**
     * 查看失败的请求总数
     *
     * @return
     */
    public long getFailRequestCount() {
        return FAIL_REQUEST_COUNT.get();
    }
}
