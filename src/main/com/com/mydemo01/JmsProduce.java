package com.mydemo01;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {



    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        //创建connection并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Queue queue = session.createQueue("test-01");
        //创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            messageProducer.send(session.createTextMessage("消息内容"+i));
        }
        messageProducer.close();
        session.close();
        connection.close();


    }
}
