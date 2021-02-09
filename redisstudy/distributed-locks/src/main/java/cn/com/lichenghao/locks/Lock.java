package cn.com.lichenghao.locks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 最简单的获取锁的方式
 */
@Component
public class Lock {

    private static final String DISTRIBUTED_KEY = "lOCK";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取锁
     *
     * @param key     表示持有锁的对象
     * @param timeOut 锁过期时间
     * @return
     */
    public Boolean getLock(String key, int timeOut) {
        ValueOperations value = redisTemplate.opsForValue();
        Boolean aBoolean = value.setIfAbsent(DISTRIBUTED_KEY, key, timeOut, TimeUnit.SECONDS);
        if (aBoolean) {
            return redisTemplate.expire(DISTRIBUTED_KEY, timeOut, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @return
     */
    public Boolean releaseLock() {
        return redisTemplate.delete(DISTRIBUTED_KEY);
    }
}
