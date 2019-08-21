package com.morris.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class PipelineTest {

    @Test
    public void testMdel() {


        Jedis jedis = null;
        try {

            jedis = new JedisPool("10.0.4.91", 6379).getResource();

            Pipeline pipelined = jedis.pipelined();

            pipelined.del("hello");
            pipelined.del("user1");

            List<Object> objects = pipelined.syncAndReturnAll();

            for (Object object : objects) {
                System.out.println(object);
            }

        } finally {
            if(null != jedis) {
                jedis.close();
            }
        }

    }

}
