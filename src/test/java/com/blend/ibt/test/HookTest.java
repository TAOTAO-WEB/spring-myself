package com.blend.ibt.test;

import cn.hutool.core.util.StrUtil;
import com.blend.ibt.bean.UserService;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class HookTest {
    @Test
    public void test_xml(){
        //1. 初始化beanFactory
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        xmlApplicationContext.registerShutdownHook();

        //2. 获取bean对象调用方法
        UserService userService = xmlApplicationContext.getBean("userService",UserService.class);
        userService.queryUser();
        System.out.println(userService.toString());

    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }
}
