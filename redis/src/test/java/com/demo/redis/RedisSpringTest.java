package com.demo.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author WH1707008
 * @date 2018/7/6 12:34
 * Description:
 */
public class RedisSpringTest {
    private static final Log log = LogFactory.getLog(RedisSpringTest.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            ShardedJedisPool pool = (ShardedJedisPool) context.getBean("shardJedisPool");
            ShardedJedis jedis = pool.getResource();

            String key = "host";
            String value = "";

            jedis.del(key);//删除数据

            jedis.set(key, "10.5.33.235");//存数据
            value = jedis.get(key);//取数据
            log.info(key + "=" + value);

            jedis.set(key, "10.5.33.212");//存数据
            value = jedis.get(key);//取数据
            log.info(key + "=" + value);


            jedis.del(key);//删除数据
            value = jedis.get(key);//取数据
            log.info(key + "=" + value);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
