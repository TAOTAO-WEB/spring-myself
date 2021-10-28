package com.blend.ibt.test;

import com.blend.ibt.bean.UserService;
import com.blend.ibt.bean.UserService2;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class PrototypeTest {
    @Test
    public void test_prototype(){
        //1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        //2. 获取Bean对象调用方法
        UserService2 userService1 = applicationContext.getBean("userService2", UserService2.class);
        UserService2 userService2 = applicationContext.getBean("userService2", UserService2.class);

        //3. 配置 scope=prototype/singleton
        System.out.println(userService1);
        System.out.println(userService2);

        //4 打印十六进制哈希
        System.out.println(userService1+"1. 十六进制哈希："+Integer.toHexString(userService1.hashCode()));
        System.out.println(userService2+"2. 十六进制哈希："+Integer.toHexString(userService2.hashCode()));
    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService2 userService = applicationContext.getBean("userService2", UserService2.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }


}
