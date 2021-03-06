package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.FactoryBean;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.config.BeanPostProcessor;
import com.blend.ibt.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.blend.ibt.springframework.core.convert.ConversionService;
import com.blend.ibt.springframework.utils.ClassUtils;
import com.blend.ibt.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanDefinition 注册表接口
 * 综合FactoryBeanRegistrySupport和ConfigurableBeanFactory功能
 * @author tt
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     *
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     *  BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ConversionService conversionService;

    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name,Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null){
            return (T) getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean,name);

    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if(object==null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader(){
        return this.beanClassLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for(StringValueResolver resolver:this.embeddedValueResolvers){
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }
}
