package com.morris.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {

    @Test
    public void testString() {

        Jedis jedis = null;
        try {

            jedis = new JedisPool("10.0.4.91", 6379).getResource();
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

}
