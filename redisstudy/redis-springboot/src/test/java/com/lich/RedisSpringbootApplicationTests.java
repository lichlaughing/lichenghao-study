package com.lich;

import com.lich.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        User lib = new User("李白", 100);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("k1", lib);
        Object k1 = valueOperations.get("k1");
        System.out.println(k1);
    }
}
