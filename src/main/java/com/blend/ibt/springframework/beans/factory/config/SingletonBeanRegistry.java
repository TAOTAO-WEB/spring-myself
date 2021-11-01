package com.blend.ibt.springframework.beans.factory.config;

/**
 * 定义对单例的注册与获取
 * @author tt
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例对象的接口
     * @param beanName bean的名称
     * @return
     */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
