package com.blend.ibt.test;

import com.blend.ibt.bean.UserService;
import com.blend.ibt.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.blend.ibt.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeanXmlTest {

    @Test
    public void test_xml(){
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.读取配置文件 注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService",UserService.class);
        userService.queryUser();

    }

    @Test
    public void test(){
        Map<String,Integer> map = new HashMap<>();
        map.put("123",123);
        map.put("33",33);
        System.out.println(Arrays.toString(map.keySet().toArray(new String[0])));
    }
}
