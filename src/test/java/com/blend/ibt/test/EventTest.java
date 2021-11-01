package com.blend.ibt.test;

import com.blend.ibt.event.CustomEvent;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class EventTest {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
