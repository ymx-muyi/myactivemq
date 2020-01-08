package com.springboot.activemq.springboot_activemq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.UUID;

@Component
public class Queue_Consumer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//jms的模板

    @Autowired
    private Queue queue;//注入myqueue
    //监听的目的地
    @JmsListener(destination = "${myqueue}")
    public void recevice(TextMessage textMessage) throws JMSException {
        System.out.println("监听内容"+textMessage.getText());
    }


}
