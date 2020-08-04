package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * redis管道技术pipeline
 */
public class PipeLineTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        long begin = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            jedis.set("jedis-"+i,"jedis-"+i);
//        }
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            for (int j = i*100; j <(i+1)*100; j++) {
                pipeline.del("jedis-"+i*j);
            }

        }
        pipeline.sync();
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-begin);
    }
}
