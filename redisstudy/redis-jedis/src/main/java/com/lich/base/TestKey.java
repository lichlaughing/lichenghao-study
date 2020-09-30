package com.lich.base;

import redis.clients.jedis.Jedis;

public class TestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("清空数据：" + jedis.flushDB());
        System.out.println("判断key是否存在："+jedis.exists("A"));
        System.out.println("set k v:"+jedis.set("k1","v1"));
        System.out.println("判断key类型："+jedis.type("k1"));
        System.out.println("keys *:"+jedis.keys("*"));
        System.out.println("删除key:"+jedis.del("k1"));
        System.out.println("随机返回一个key:"+jedis.randomKey());
        System.out.println("重命名key:"+jedis.rename("k1","k2")); //OK
        System.out.println("获取key的值："+jedis.get("k2"));
        System.out.println("按照索引查询："+jedis.select(1));
        System.out.println("数据库中key数量："+jedis.dbSize());
    }
}
