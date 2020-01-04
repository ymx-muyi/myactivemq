package com.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者事务
 * 在创建session的时候也要把参数设置成true，代表启用了事务，并且也要在session进行关闭之前，使用commit，
 * 否则会造成消息重复消费
 */
public class JmsConsumer {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        //根据连接工厂创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //第一个参数为是否开始事务
        //第二个为签收类型
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
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
