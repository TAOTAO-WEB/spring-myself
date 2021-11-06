package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.DisposableBean;
import com.blend.ibt.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对接口singletonBeanRegistry各函数的实现
 * @author tt
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for(int i=disposableBeanNames.length-1;i>=0;i--){
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name"+beanName+"threw an exception",e);
            }
        }
    }

}
