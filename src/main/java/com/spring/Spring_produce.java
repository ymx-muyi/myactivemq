package com.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class Spring_produce {

    @Autowired
    private JmsTemplate jmsTemplate ;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取Spring_produce
        Spring_produce spring_produce =(Spring_produce) ctx.getBean("spring_produce");
      /*  spring_produce.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring和activemq整合的case");
                return textMessage;
            }
        });*/
        //使用lambda
        spring_produce.jmsTemplate.send( (session) -> {
                TextMessage textMessage = session.createTextMessage("spring和activemq整合listener的case");
        return textMessage;
        });

        System.out.println("****************** send  task over");

    }

}
