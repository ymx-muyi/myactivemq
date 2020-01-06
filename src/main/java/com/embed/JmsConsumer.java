package com.embed;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 内嵌式activemq
 */
public class JmsConsumer {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //根据连接工厂创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建session，设置事务和签收方式
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
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
