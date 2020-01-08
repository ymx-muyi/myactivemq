package com.springboot.activemq.springboot_activemq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Component
public class Topic_Consumer {
    @Autowired
    private Queue queue;//注入myqueue
    //监听的目的地
    @JmsListener(destination = "${mytopic}")
    public void recevice(TextMessage textMessage) throws JMSException {
        System.out.println("监听消费者订阅内容"+textMessage.getText());
    }


}
