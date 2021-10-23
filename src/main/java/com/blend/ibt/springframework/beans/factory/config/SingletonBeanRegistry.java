package com.blend.ibt.springframework.beans.factory.config;

/**
 * @author tt
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例对象的接口
     * @param beanName bean的名称
     * @return
     */
    Object getSingleton(String beanName);

}
