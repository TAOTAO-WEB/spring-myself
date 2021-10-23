package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.BeanFactory;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;

/**
 * @author tt
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name,Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if(bean != null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)creatBean(name,beanDefinition,args);

    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object creatBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;
}
