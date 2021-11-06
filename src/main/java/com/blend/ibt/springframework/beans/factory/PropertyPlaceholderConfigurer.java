package com.blend.ibt.springframework.beans.factory;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValue;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.blend.ibt.springframework.core.io.DefaultResourceLoader;
import com.blend.ibt.springframework.core.io.Resource;
import com.blend.ibt.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 处理占位符配置
 * 依赖于BeanFactoryPostProcessor在Bean生命周期的属性 可以在Bean对象实例化之前，改变属性信息
 * 所以在这里通过实现BeanFactoryPostProcessor接口，完成对配置文件的加载以及摘取占位符中的在属性文件里的配置
 * @author tt
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //加载属性文件
        try{
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for(String beanName:beanDefinitionNames){
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for(PropertyValue propertyValue:propertyValues.getPropertyValues()){
                    Object value = propertyValue.getValue();
                    if(!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value,properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),value));
                }

            }
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        }catch (IOException e){
            throw new BeansException("Could not load properties",e);
        }

    }


    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }


    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver{

        private final Properties properties;

        private PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,properties);
        }
    }

}
