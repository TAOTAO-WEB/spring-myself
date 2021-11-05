package com.blend.ibt.springframework.aop;

/**
 * 定义匹配类 用于切点找到给定的接口和目标类
 * @author tt
 */
public interface ClassFilter {

    /**
     * should the pointcut apply to the given interface or target class
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}
