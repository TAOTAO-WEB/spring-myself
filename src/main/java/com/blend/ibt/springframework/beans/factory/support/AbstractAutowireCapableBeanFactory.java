package com.blend.ibt.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValue;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author tt
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = creatBeanInstant(beanDefinition,beanName,args);
            //给Bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object creatBeanInstant(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor con:declaredConstructors){
            if(null!=args && con.getParameterTypes().length==args.length){
                constructorToUse = con;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    /**
     * bean属性填充
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue:propertyValues.getPropertyValues()){

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference){
                    //A 依赖 B，获取B的实例化
                    BeanReference beanReference = (BeanReference)value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values:"+beanName);
        }
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
