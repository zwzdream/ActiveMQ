package com.test;

import com.alibaba.fastjson.JSONObject;
import com.test.params.MailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author WH1707008
 * @date 2018/7/5 16:29
 * Description:MQ消息生产者
 */
@Service("mqProducer")
public class MQProducer {
    @Autowired
    private JmsTemplate activeMqJmsTemplate;

    /**
     * 发消息
     * @param mail
     */
    public void sendMessage(final MailParam mail){
        activeMqJmsTemplate.send(session -> session.createTextMessage(JSONObject.toJSONString(mail)));
    }
}
