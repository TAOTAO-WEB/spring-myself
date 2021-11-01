package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在DefaultSingletonBeanRegistry基础上增加了对factoryBean的特数处理功能
 * @author tt
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    /**
     * 存放单例类型的对象，避免重复创建
     * cache of singleton objects created by factoryBeans
     */
    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object!=NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
        if(factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object==null){
                object = doGetObjectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName,(object != null ? object:NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        }else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory,final String beanName){
        try{
            return factory.getObject();
        }catch (Exception e){
            throw new BeansException("FactoryBean threw exception on object[" + beanName +"] creation",e);
        }
    }

}
