package com.blend.ibt.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.BeanFactory;
import com.blend.ibt.springframework.beans.factory.BeanFactoryAware;
import com.blend.ibt.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.blend.ibt.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.blend.ibt.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

/**
 * 实现接口InstantiationAwareBeanPostProcessor的一个用于在Bean对象实例化完成后，设置属性操作前的处理属性信息的类和操作方法
 * 只有实现了BeanPostProcessor接口才有机会在Bean的生命周期中处理初始化信息
 * @author tt
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory)beanFactory;
    }

    /**
     * 用于处理含有@Value，@Autowired注解的属性，进行属性的提取和设置
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        //1.处理注解@Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz)? clazz.getSuperclass():clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for(Field field:declaredFields){
            Value valueAnnotation = field.getAnnotation(Value.class);
            if(null != valueAnnotation){
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean,field.getName(),value);
            }
        }

        //2.处理注解@Autowired
        for(Field field:declaredFields){
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if(null != autowiredAnnotation){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if(null != qualifierAnnotation){
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName,fieldType);
                }else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean,field.getName(),dependentBean);
            }
        }
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
