package com.blend.ibt.test;

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

    /**
     * Spring在关闭上下文的时候，可以使用钩子函数来关闭残留的资源
     * 钩子函数会在主函数运行完后自动运行
     */
    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
        System.out.println("init");
    }
}
