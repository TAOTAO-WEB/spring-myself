package com.blend.ibt.test;


import com.blend.ibt.bean.UserDao;
import com.blend.ibt.bean.UserService;
import com.blend.ibt.springframework.beans.PropertyValue;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.config.BeanReference;
import com.blend.ibt.springframework.beans.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ApiTest {

    @Test
    public void apiTest(){
        //1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        beanFactory.registerBeanDefinition("userService2",beanDefinition);

        //3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUser();

        UserService userService_sin = (UserService) beanFactory.getBean("userService2","123");
        userService_sin.queryUser();
        System.out.println(userService_sin.toString());

    }

    @Test
    public void test_BeanFactory(){
        //1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.UserDao 注册
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        //3. UserService 注册属性【uid，userDao】
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //4.UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //5 UserService 获取bean
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUser();

    }


    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"123"});
        System.out.println(obj);
    }

    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_construct() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("123");
        System.out.println(userService);
    }

    @Test
    public void test_parameterTypes() throws Exception{
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[1];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("123");
        System.out.println(userService);


    }



}
