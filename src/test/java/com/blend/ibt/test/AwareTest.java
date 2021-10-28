package com.blend.ibt.test;

import com.blend.ibt.bean.UserService;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class AwareTest {
    @Test
    public void test_xml(){
        //1. 初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        //2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService",UserService.class);
        userService.queryUser();
        System.out.println("ApplicationContextAware"+userService.getApplicationContext());
        System.out.println("BeanFactoryAware:"+userService.getBeanFactory());

    }
}
