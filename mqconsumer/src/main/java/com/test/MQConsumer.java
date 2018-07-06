package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author WH1707008
 * @date 2018/7/5 19:41
 * Description:ActiveMQ测试启动类
 */
public class MQConsumer {
    private static Log log=LogFactory.getLog(MQConsumer.class);

    public static void main(String[] args) {
        try{
            ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
        }catch (Exception e){
            log.error("==>MQ context start error:", e);
            System.exit(0);
        }
    }

}
