package com.blend.ibt.springframework.core.convert.converter;

/**
 * 类型转换处理接口
 * @author tt
 */
public interface Converter<S,T> {

    /**
     * convert the source object of type to target type
     * @param source
     * @return
     */
    T convert(S source);
}
