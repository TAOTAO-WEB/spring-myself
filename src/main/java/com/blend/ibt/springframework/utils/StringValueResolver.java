package com.blend.ibt.springframework.utils;

/**
 * 解析字符串
 * @author tt
 */
public interface StringValueResolver {

    /**
     * 解析字符串操作
     * @param strVal
     * @return
     */
    String resolveStringValue(String strVal);
}
