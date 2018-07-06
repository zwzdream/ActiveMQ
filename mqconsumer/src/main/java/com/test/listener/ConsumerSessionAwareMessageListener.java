package com.test.listener;

import com.alibaba.fastjson.JSONObject;
import com.test.biz.MailBiz;
import com.test.params.MailParam;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author WH1707008
 * @date 2018/7/5 17:45
 * Description:队列监听器
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

    private static final Log log = LogFactory.getLog(ConsumerSessionAwareMessageListener.class);
    @Autowired
    private JmsTemplate activeMqJmsTemplate;
    @Autowired
    private Destination sessionAwareQueue;
    @Autowired
    private MailBiz bailBiz;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        try {
            ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
            final String ms = msg.getText();
            log.info("==>receive message:" + ms);
            //转换成相应的对象
            MailParam mailParam = JSONObject.parseObject(ms, MailParam.class);
            if (mailParam == null) {
                return;
            }
            try {
                bailBiz.mailSend(mailParam);
            } catch (Exception e) {
                //发送异常，重新放回队列
                //需要加规则，不然会成死循环，这里注释掉
//                activeMqJmsTemplate.send(sessionAwareQueue, session1 -> session1.createTextMessage(ms));
                log.error("==>MailException:", e);
            }
        } catch (Exception e) {
            log.error("==>", e);
        }

    }
}
