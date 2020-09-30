package com.lich.base;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * hash 类型
 */
public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("kay1", "value1");
        map.put("key2", "va1ue2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        jedis.hmset("hash", map);
        //向名称为hash的hash中添加key为hey5, value为value5元素
        jedis.hset("hash", "kay5", "value5");
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));//return Map<String, String)
        System.out.println("散列hash的所有键为: " + jedis.hkeys("hash"));//return Set<String)
        System.out.println("散列hash的所有值为，" + jedis.hvals("hash"));//return Listestring>
        System.out.println("将key6保存的值加上一个整数，如果key6不 存在则添加key6: " + jedis.hincrBy("hash", "key6", 1));
        System.out.println("散列hash的所有键值对为，" + jedis.hgetAll("hash"));
        System.out.println("将key6保存的值加上一个整数， 如果key6不存在则添加key6: " + jedis.hincrBy("hash", "key6", 1));
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        System.out.println("删除一个或者多 个键值对。" + jedis.hdel("hash", "key2"));
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        System.out.println("散列hash中键值对的个数，" + jedis.hlen("hash"));
        System.out.println("判断hash中是否存在key2: " + jedis.hexists("hash", "key2"));
        System.out.println("判断hash中是否存在kay3." + jedis.hexists("hash", "key3"));
        System.out.println("获取hash中的值: " + jedis.hmget("hash", "kay3"));
        System.out.println("获取hash中的值，" + jedis.hmget("hash", "key3", "key4"));
    }
}
