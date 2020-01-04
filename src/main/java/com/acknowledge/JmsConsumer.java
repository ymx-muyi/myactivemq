package com.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 签收机制 事务偏生产者，签收偏消费者
 * 不考虑事务的情况下，生产者设置签收类型对消费者这边是没影响的
 * 当生产者设置手动签收，如果消费者设置自动签收，那么消费者依然可以消费，并且只能一次
 *
 *
 * 签收分为三种
 *      自动签收(默认机制)  AUTO_ACKNOWLEDGE
 *       手动签收    Session.CLIENT_ACKNOWLEDGE
 *       允许重复消息  Session.DUPS_OK_ACKNOWLEDGE
 *       Session.SESSION_TRANSACTED
 */
public class JmsConsumer {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        //根据连接工厂创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //非事务签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Queue queue = session.createQueue("test-01");
        //创建消息的消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //第一种
        /*while(true){
            TextMessage msg = (TextMessage) consumer.receive();
            System.out.println(msg);
        }*/
        //第二种使用监听
        consumer.setMessageListener((message) -> {
            if(message!=null && message instanceof TextMessage ){
                try {
                    System.out.println(((TextMessage) message).getText());
                   // message.acknowledge();//手动签收
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        session.commit();
        session.close();
        connection.close();


    }

}
