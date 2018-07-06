package com.test.biz;

import com.test.params.MailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author WH1707008
 * @date 2018/7/5 18:17
 * Description:邮件发送业务逻辑类
 */
@Component("mailBiz")
public class MailBiz {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    /**
     * 发送邮件
     * @param  mailParam 需要设置四个参数
     *       templateName,toMail,subject,mapModel
     * @throws Exception
     *
     */
    public void mailSend(final MailParam mailParam){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    simpleMailMessage.setFrom(simpleMailMessage.getFrom());
                    simpleMailMessage.setTo(mailParam.getTo());
                    simpleMailMessage.setSubject(mailParam.getSubject());
                    simpleMailMessage.setText(mailParam.getContent());
                    mailSender.send(simpleMailMessage);
                }catch (MailException e){
                    throw e;
                }
            }
        });
    }

}
