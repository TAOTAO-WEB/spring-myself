package com.blend.ibt.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配，找到表达式范围内匹配下的目标类和方法
 * @author tt
 */
public interface MethodMatcher {

    /**
     * perform static checking whether the given method matches
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method,Class<?> targetClass);
}
