package com.blend.ibt.springframework.beans.factory.config;

/**
 * @author tt
 */
public class BeanReference {
    private final String beanName;


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }

}
