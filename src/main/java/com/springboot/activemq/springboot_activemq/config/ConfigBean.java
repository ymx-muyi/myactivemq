package com.springboot.activemq.springboot_activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
@EnableJms //开启jms
public class ConfigBean {
    @Value("${myqueue}")//读取配置文件名为myqueue的value值
    private String myqueue;

    @Value("${mytopic}")//读取配置文件名为myqueue的value值
    private String mytopic;

    //注入bean,相当于spring里面使用accplicationContxt读取application.xml
    //里面设置的bean
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myqueue);
    }
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(mytopic);
    }

}