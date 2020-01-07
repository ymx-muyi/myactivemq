package com.springboot.activemq.springboot_activemq;

import com.springboot.activemq.springboot_activemq.config.Queue_Produce;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = SpringbootActivemqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class SpringbootActivemqApplicationTests {

    @Resource
    private Queue_Produce queue_produce;

    @Test
    void testsend() {
        queue_produce.produceMsg();
    }

}
