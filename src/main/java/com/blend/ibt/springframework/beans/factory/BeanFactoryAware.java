package com.blend.ibt.springframework.beans.factory;

import com.blend.ibt.springframework.beans.BeansException;

/**
 * 感知所属的BeanFactory
 * @author tt
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
