package com.blend.ibt.springframework.beans.factory;


import com.blend.ibt.springframework.beans.BeansException;

import java.util.Map;

/**
 * 根据各种条件获取bean的配置清单
 * @author tt
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回Bean实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry
     * @return 注册表中所有Bean名称
     */
    String[] getBeanDefinitionNames();
}
