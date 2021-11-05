package com.blend.ibt.springframework.aop;

import java.lang.reflect.Method;

/**
 * spring框架中,Advice都是通过方法拦截器MethodInterceptor实现的
 *
 * @author tt
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    /**
     * callback before a given method is invoked
     * @param method
     * @param args
     * @param target
     */
    void before(Method method,Object[] args,Object target) throws Throwable;

}
