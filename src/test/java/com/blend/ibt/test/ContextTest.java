package com.blend.ibt.test;

import com.blend.ibt.bean.UserService;
import com.blend.ibt.common.MyBeanFactoryPostProcessor;
import com.blend.ibt.common.MyBeanPostProcessor;
import com.blend.ibt.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.blend.ibt.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ContextTest {

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        //1. 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2. 读取配置文件xml 注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //3.BeanDefinition 加载完成 & Bean实例化之前 修改BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化后 修改属性
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        //5. 获取bean对象调用方法
        UserService userService = beanFactory.getBean("userService",UserService.class);
        userService.queryUser();
        System.out.println(userService.toString());
    }

    @Test
    public void test_xml(){
        //1. 初始化BeanFactory
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        //2. 获取Bean对象调用方法
        UserService userService = xmlApplicationContext.getBean("userService",UserService.class);
        userService.queryUser();
        System.out.println(userService.toString());

    }
}
