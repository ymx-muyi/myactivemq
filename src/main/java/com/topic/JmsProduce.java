package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * 非持久化订阅
 * 不能恢复或重新派送一个为签收的消息
 * 也就是消费者必须在发送者之前启动
 */
public class JmsProduce {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        //根据连接工厂创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建session，设置事务和签收方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic("test-topic");
        //创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
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
