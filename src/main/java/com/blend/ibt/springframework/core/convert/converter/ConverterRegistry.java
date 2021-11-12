package com.blend.ibt.springframework.core.convert.converter;

/**
 * 类型转换注册接口
 * @author tt
 */
public interface ConverterRegistry {

    /**
     *
     * @param converter
     */
    void addConverter(Converter<?,?> converter);

    /**
     * @param converter
     */
    void addConverter(GenericConverter converter);

    /**
     * @param converterFactory
     */
    void addConverterFactory(ConverterFactory<?,?> converterFactory);

}
