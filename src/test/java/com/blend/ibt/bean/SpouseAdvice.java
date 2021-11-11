package com.blend.ibt.bean;

import com.blend.ibt.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("两人（切面）："+method);
    }
}
