package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author tt
 */
public interface InstantiationStrategy {


    /**
     * @param beanDefinition
     * @param beanName
     * @param con 拿到符合入参信息对应的构造函数
     * @param args 具体入参信息
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor con,Object[] args)
            throws BeansException;

}
