package com.persist;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.TopicSubscription;

import javax.jms.*;

public class JmsConsumer {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://47.93.206.135:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("zs");
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic toic = session.createTopic("test-persist");
        TopicSubscriber topicSubscription = session.createDurableSubscriber(toic,"remark...");
        connection.start();
        Message message = topicSubscription.receive();//方法会阻塞
        while (null !=message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("收到持久化topic"+textMessage.getText());
            message = topicSubscription.receive(3000l);
        }
        session.close();
        connection.close();
    }

}
