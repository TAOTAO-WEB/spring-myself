package com.blend.ibt.springframework.context.support;

import com.blend.ibt.springframework.beans.factory.FactoryBean;
import com.blend.ibt.springframework.beans.factory.InitializingBean;
import com.blend.ibt.springframework.core.convert.ConversionService;
import com.blend.ibt.springframework.core.convert.converter.Converter;
import com.blend.ibt.springframework.core.convert.converter.ConverterFactory;
import com.blend.ibt.springframework.core.convert.converter.ConverterRegistry;
import com.blend.ibt.springframework.core.convert.converter.GenericConverter;
import com.blend.ibt.springframework.core.convert.support.DefaultConversionService;
import com.blend.ibt.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * 创建类型转换工厂
 * @author tt
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {


    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();

    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry){
        if(converters != null){
            for(Object converter:converters){
                if(converter instanceof GenericConverter){
                    registry.addConverter((GenericConverter) converter);
                }else if(converter instanceof Converter<?,?>){
                    registry.addConverter((Converter<?, ?>) converter);
                }else if(converter instanceof ConverterFactory<?,?>){
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                }else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +"Converter, ConverterFactory, or GenericConverter inter faces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
