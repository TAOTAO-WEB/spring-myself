package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author tt
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    /**
     * 若构造函数为空，则实例化空参构造函数，否则带参数实例化
     * @param beanDefinition
     * @param beanName
     * @param con            拿到符合入参信息对应的构造函数
     * @param args           具体入参信息
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor con, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try{
            if(null!=con){
                return clazz.getDeclaredConstructor(con.getParameterTypes()).newInstance(args);
            }else{
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | InvocationTargetException |NoSuchMethodException |IllegalAccessException e) {
            throw new BeansException("Fail to instantiation [" + clazz.getName() + "]",e);
        }
    }

}
