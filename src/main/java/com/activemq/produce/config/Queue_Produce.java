package com.activemq.produce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class Queue_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//jms的模板

    @Autowired
    private Queue queue;//注入myqueue

    public void produceMsg() {
        //使用send发送消息
        jmsMessagingTemplate.convertAndSend(queue, "*************:" + UUID.randomUUID().toString().substring(0, 6));
    }

    //间隔时间定时投放
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        //使用send发送消息
        jmsMessagingTemplate.convertAndSend(queue, "*************Scheduled:" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("****定时投递");
    }

}
