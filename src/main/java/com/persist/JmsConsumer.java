package com.persist;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.TopicSubscription;

import javax.jms.*;

/**  持久订阅
 * 发布订阅类似于公众号订阅
 *一定要先运行一次消费者，等于向mq注册。类似我订阅了这个主题
 * 2.然后生产者发送消息
 * 3.无论消费者是否在线  ，都会接受到
 */public class JmsConsumer {

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
