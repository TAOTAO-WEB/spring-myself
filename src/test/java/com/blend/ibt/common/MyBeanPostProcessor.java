package com.blend.ibt.common;

import com.blend.ibt.bean.UserService;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService)bean;
            userService.setLocation("改为:beijing");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
