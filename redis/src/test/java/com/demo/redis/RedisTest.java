package com.demo.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

/**
 * @author WH1707008
 * @date 2018/7/6 12:34
 * Description:
 */
public class RedisTest {
    private static final Log log=LogFactory.getLog(RedisTest.class);

    public static void main(String[] args) {
        Jedis jedis=new Jedis("10.5.33.235");
        String key="host";
        String value="";

        jedis.del(key);//删除数据

        jedis.set(key,"10.5.33.235");//存数据
        value=jedis.get(key);//取数据
        log.info(key+"="+value);

        jedis.set(key, "10.5.33.212");//存数据
        value=jedis.get(key);//取数据
        log.info(key+"="+value);


        jedis.del(key);//删除数据
        value=jedis.get(key);//取数据
        log.info(key+"="+value);

    }
}
