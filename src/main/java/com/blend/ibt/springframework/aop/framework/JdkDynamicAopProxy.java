package com.blend.ibt.springframework.aop.framework;

import com.blend.ibt.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实现AopProxy，InvocationHandler 把代理对象getProxy和反射调用方法invoke分开处理
 *
 * @author tt
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 代理一个对象
     * @return
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),advised.getTargetSource().getTargetClass(),this);
    }


    /**
     * 处理匹配的方法后，使用用户自己提供的方法拦截实现
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(advised.getMethodMatcher().matches(method,advised.getTargetSource().getTarget().getClass())){
             MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
             return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),method,args));
        }
        return method.invoke(advised.getTargetSource().getTarget(),args);
    }
}
