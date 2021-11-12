package com.blend.ibt.springframework.core.convert.converter;

/**
 * 类转换工厂
 * @author tt
 */
public interface ConverterFactory<S,R> {
    /**
     * get the converter to convert from s to target type t,where t is also an instance of r
     * @param targetType
     * @param <T>
     * @return
     */
    <T extends R> Converter<S,T> getConverter(Class<T> targetType);
}
