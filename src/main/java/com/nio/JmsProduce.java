package com.nio;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("nio://47.93.206.135:61618");
        //根据连接工厂创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建session，设置事务和签收方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Queue queue = session.createQueue("test-01");
        //创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //DeliveryMode.PERSISTENT  代表持久化
        //DeliveryMode.NON_PERSISTENT  不持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT );
        for (int i = 0; i < 3; i++) {
            //发送消息
            messageProducer.send(session.createTextMessage("消息内容"+i));
        }
        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();


    }
}
