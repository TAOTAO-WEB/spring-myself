package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValues;

/**
 * @author tt
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在Bean执行初始化方法之前，执行此方法
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    /**
     * 在Bean对象实例化完成后，设置属性操作之前执行此方法
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs,Object bean,String beanName) throws BeansException;


    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean,String beanName) throws BeansException;

    /**
     * 在spring中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean,String beanName){
        return bean;
    }

}
