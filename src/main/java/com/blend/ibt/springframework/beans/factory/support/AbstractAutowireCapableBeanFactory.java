package com.blend.ibt.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValue;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.*;
import com.blend.ibt.springframework.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 综合AbstractBeanFactory并对接口AutowireCapableBeanFactory进行实现
 * @author tt
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     *
     *初始化策略
     *
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try{
            //判断是否返回代理Bean对象
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if(null != bean) {
                return bean;
            }
            //实例化Bean
            bean = createBeanInstant(beanDefinition,beanName,args);
            //在设置属性之前，允许BeanPostProcessor修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName,bean,beanDefinition);
            //给Bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }

        // 注册实现了DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);

        //判断 SCOPE_SINGLETON,SCOPE_PROTOTYPE
        if(beanDefinition.isSingleton()){
            registerSingleton(beanName,bean);
        }
        return bean;
    }

    /**
     * 在设置Bean属性之前，允许BeanPostProcessor修改属性值
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        for(BeanPostProcessor beanPostProcessor:getBeanPostProcessors()){
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(),bean,beanName);
                if(null != pvs){
                    for(PropertyValue propertyValue:pvs.getPropertyValues()){
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }

    }

    protected Object resolveBeforeInstantiation(String beanName,BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorsBeforeInitialization(beanDefinition.getBeanClass(),beanName);
        if(null != bean){
            bean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        }
        return bean;
    }

    protected Object createBeanInstant(BeanDefinition beanDefinition, String beanName, Object[] args){
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



    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){

        //invokeAwareMethods
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 执行bean初始化方法
        try{
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Invocation of init method of bean["+beanName+"] failed",e);
        }

        // 2.执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName);
        return wrappedBean;
    }

    private void  invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception{
        // 1.实现接口InitializingBean
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        //2. 配置信息init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null==initMethod){
                throw new BeansException("Could not find an init method named "+initMethodName+" one bean with name "+beanName);

            }
            initMethod.invoke(bean);
        }

    }

    protected void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        //非singleton类型的bean不执行销毁方法
        if(!beanDefinition.isSingleton()) return;

        if(bean instanceof DisposableBean ||
                StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

    protected Object applyBeanPostProcessorsBeforeInitialization(Class<?> beanClass,String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor processor:getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null==current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor processor:getBeanPostProcessors()){
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
