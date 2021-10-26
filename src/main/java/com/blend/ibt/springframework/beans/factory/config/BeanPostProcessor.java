package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.BeansException;

/**
 * 在 Bean 对象实例化之后修改/替换 Bean 对象的扩展点
 * @author tt
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;


    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws  BeansException;


}
