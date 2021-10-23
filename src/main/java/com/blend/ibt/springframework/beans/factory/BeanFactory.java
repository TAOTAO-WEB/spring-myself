package com.blend.ibt.springframework.beans.factory;


import com.blend.ibt.springframework.beans.BeansException;

/**
 * @author tt
 */
public interface BeanFactory {

    /**
     *
     * 获取bean实例接口
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 用于带参构造函数实例化
     * @param name
     * @param args 入参
     * @return
     * @throws BeansException
     */
    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
