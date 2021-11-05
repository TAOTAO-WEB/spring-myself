package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.BeansException;

/**
 * @author tt
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在Bean执行初始化方法之前，执行此方法
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;
}
