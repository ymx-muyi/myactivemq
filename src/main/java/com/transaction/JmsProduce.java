package com.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者事务  事务偏生产者，签收偏消费者
 * 生产者开始事务：要在创建session的时候，把第一个参数设置成true,然后在session关闭之前执行commit
 */
public class JmsProduce {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建session，设置事务和签收方式 设置为true开启事务
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-01");
        MessageProducer messageProducer = session.createProducer(queue);
        //DeliveryMode.PERSISTENT  代表持久化
        //DeliveryMode.NON_PERSISTENT  不持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT );
        messageProducer.send(session.createTextMessage("消息内容-----"));
        //关闭资源
        messageProducer.close();
        session.commit();//不加commit，消息不会发送到队列中
        session.close();
        connection.close();


    }
}
