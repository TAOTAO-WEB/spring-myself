package com.blend.ibt.springframework.aop.framework;

import com.blend.ibt.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 * 主要解决关于jdk和cglib两种代理的选择问题，有了代理工厂可以按照不同的创建需求进行控制
 * @author tt
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
