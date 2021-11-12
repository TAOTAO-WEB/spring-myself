package com.blend.ibt.springframework.core.convert.support;

import com.blend.ibt.springframework.core.convert.converter.Converter;
import com.blend.ibt.springframework.core.convert.converter.ConverterFactory;
import com.blend.ibt.springframework.utils.NumberUtils;

/**
 *
 * @author tt
 */
public class StringToNumberConverterFactory implements ConverterFactory<String,Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String,T>{
        private final Class<T> targetType;

        private StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if(source.isEmpty()) return null;
            return NumberUtils.parseNumber(source,this.targetType);
        }
    }

}
