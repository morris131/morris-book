package com.morris.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void testString() {

        Jedis jedis = null;
        try {
            jedis = new Jedis("10.0.4.91", 6379);

            String set = jedis.set("hello", "world");
            System.out.println(set);

            String get = jedis.get("hello");
            System.out.println(get);
        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

    @Test
    public void testHash() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("10.0.4.91", 6379);

            jedis.hset("user1", "name", "zhangsan");
            jedis.hset("user1", "age", "22");
            jedis.hset("user1", "sex", "男");

            System.out.println(jedis.hgetAll("user1"));
        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }

    }

    @Test
    public void testList() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("10.0.4.91", 6379);

            jedis.rpush("list", "a");
            jedis.rpush("list", "b");
            jedis.rpush("list", "c");

            System.out.println(jedis.lrange("list", 0, -1));
        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

    @Test
    public void testSet() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("10.0.4.91", 6379);

            jedis.sadd("set", "a");
            jedis.sadd("set", "b");
            jedis.sadd("set", "c");

            System.out.println(jedis.smembers("set"));
        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

    @Test
    public void testZset() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("10.0.4.91", 6379);

            jedis.zadd("zset", 99, "a");
            jedis.zadd("zset", 100, "b");
            jedis.zadd("zset", 80, "c");

            System.out.println(jedis.zrangeWithScores("zset", 0, -1));
        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }
    }

}
