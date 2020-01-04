package com.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 非事务签收机制
 */
public class JmsProduce {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-01");
        MessageProducer messageProducer = session.createProducer(queue);
        //DeliveryMode.PERSISTENT  代表持久化
        //DeliveryMode.NON_PERSISTENT  不持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT );
        messageProducer.send(session.createTextMessage("消息内容-----"));
        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();

    }
}
