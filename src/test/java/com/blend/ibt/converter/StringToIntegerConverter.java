package com.blend.ibt.converter;

import com.blend.ibt.springframework.core.convert.converter.Converter;

public class StringToIntegerConverter implements Converter<String,Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
