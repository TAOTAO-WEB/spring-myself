package com.blend.ibt.springframework.context.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.config.BeanPostProcessor;
import com.blend.ibt.springframework.context.ApplicationContext;
import com.blend.ibt.springframework.context.ApplicationContextAware;

/**
 * 由于ApplicationContext获取并不能直接在创建Bean时候直接拿到，所以需要在refresh操作时候，把ApplicationContext写入一个包装的BeanPostProcessor中去
 * 再由AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization调用
 * @author tt
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
