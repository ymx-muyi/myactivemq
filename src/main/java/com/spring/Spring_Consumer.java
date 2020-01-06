package com.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;
@Service
public class Spring_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate ;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Spring_Consumer spring_produce =(Spring_Consumer) ctx.getBean("spring_Consumer");
        String retValue = (String)spring_produce.jmsTemplate.receiveAndConvert();
        System.out.println("消费者接收到的消息"+retValue);
    }
}
