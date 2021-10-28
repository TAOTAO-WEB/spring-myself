package com.blend.ibt.springframework.beans.factory;

/**
 * @author tt
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例
     * @return
     */
    boolean isSingleton();



}
