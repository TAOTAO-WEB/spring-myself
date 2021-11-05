package com.blend.ibt.test;

import com.blend.ibt.bean.IUserService;
import com.blend.ibt.bean.UserService3;
import com.blend.ibt.bean.UserServiceInterceptor;
import com.blend.ibt.springframework.aop.AdvisedSupport;
import com.blend.ibt.springframework.aop.TargetSource;
import com.blend.ibt.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.blend.ibt.springframework.aop.framework.Cglib2AopProxy;
import com.blend.ibt.springframework.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

public class DynamicTest {
    @Test
    public void test_dynamic(){
        //目标对象
        IUserService userService = new UserService3();

        //组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.blend.ibt.bean.IUserService.*(..))"));

        //代理对象
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        //测试调用
        System.out.println("测试结果"+proxy_jdk.queryUserInfo());

        //代理对象
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();

        //测试调用
        System.out.println("测试结果"+proxy_cglib.register("花花"));

    }
}
