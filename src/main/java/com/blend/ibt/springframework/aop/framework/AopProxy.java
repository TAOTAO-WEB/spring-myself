package com.blend.ibt.springframework.aop.framework;

/**
 * 用于获取代理类的接口 具体实现方式可以有jdk或者cglib
 * @author tt
 */
public interface AopProxy {

    Object getProxy();
}
