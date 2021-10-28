package com.blend.ibt.springframework.beans.factory;

/**
 * 感知所属beanName
 * @author tt
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
