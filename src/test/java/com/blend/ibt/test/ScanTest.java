package com.blend.ibt.test;

import com.blend.ibt.bean.IUserService;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ScanTest {
    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
        IUserService userService = applicationContext.getBean("userService5",IUserService.class);
        System.out.println(userService);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
