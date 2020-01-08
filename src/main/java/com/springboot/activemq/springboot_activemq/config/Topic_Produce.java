package com.springboot.activemq.springboot_activemq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

@Component
public class Topic_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//jms的模板

    @Autowired
    private Topic topic;//注入myqueue

    public void produceMsg() {
        //使用send发送消息
        jmsMessagingTemplate.convertAndSend(topic, "*************:" + UUID.randomUUID().toString().substring(0, 6));
    }

}
