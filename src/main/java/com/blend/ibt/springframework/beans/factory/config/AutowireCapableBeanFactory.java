package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.BeanFactory;

/**
 * 自动化处理Bean工厂配置的接口
 * @author tt
 */
public interface AutowireCapableBeanFactory extends BeanFactory {


    /**
     * 执行BeanPostProcessors 接口实现类的 postProcessBeforeInitialization
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;


    /**
     * 执行BeanPostProcessors 接口实现类的 postProcessAfterInitialization
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;

}
