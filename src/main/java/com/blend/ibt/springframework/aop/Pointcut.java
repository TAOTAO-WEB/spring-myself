package com.blend.ibt.springframework.aop;

/**
 * 切入点接口 定义用于获取ClassFilter MethodMatcher的两个类
 * @author tt
 */
public interface Pointcut {

    /**
     * return the ClassFilter for this pointcut
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * return the MethodMatcher for this pointcut
     * @return
     */
    MethodMatcher getMethodMather();
}
