package com.lich.base;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试事务
 */
public class TestTx {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("k1", "v1");
            multi.set("k2", "v2");
            int i = 1 / 0;
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //放弃事务
            multi.discard();
        } finally {
            System.out.println(jedis.get("k1"));
            System.out.println(jedis.get("k2"));
            jedis.close();
        }
    }
}
