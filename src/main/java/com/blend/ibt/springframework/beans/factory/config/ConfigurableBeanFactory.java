package com.blend.ibt.springframework.beans.factory.config;

import com.blend.ibt.springframework.beans.factory.HierarchicalBeanFactory;
import com.blend.ibt.springframework.core.convert.ConversionService;
import com.blend.ibt.springframework.utils.StringValueResolver;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口
 * @author tt
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();


    /**
     * add a String resolver for embedded values such as annotation attributes.
     *
     * @param valueResolver
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);


    /**
     * Resolve the given embedded value,e.g. an annotation attribute.
     *
     * @param value
     * @return
     */
    String resolveEmbeddedValue(String value);

    /**
     *
     * @param conversionService
     */
    void setConversionService(ConversionService conversionService);

    /**
     * @return
     */
    ConversionService getConversionService();


}
