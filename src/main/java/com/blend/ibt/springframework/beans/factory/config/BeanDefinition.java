package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.PropertyValues;

/**
 * @author tt
 */
public class BeanDefinition {

    private Class beanClass;

    /**
     * bean的属性
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!=null ? propertyValues:new PropertyValues();
    }

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public Class getBeanClass(){
        return beanClass;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
