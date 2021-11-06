package com.blend.ibt.springframework.aop.framework.autoproxy;

import com.blend.ibt.springframework.aop.*;
import com.blend.ibt.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.blend.ibt.springframework.aop.framework.ProxyFactory;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValues;
import com.blend.ibt.springframework.beans.factory.BeanFactory;
import com.blend.ibt.springframework.beans.factory.BeanFactoryAware;
import com.blend.ibt.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.blend.ibt.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 融入Bean生命周期的自动代理创建者
 * @author tt
 */
public class DefaultAdvisorAutoProxyCreator implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass)    ||
                Pointcut.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(isInfrastructureClass(beanClass)) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for(AspectJExpressionPointcutAdvisor advisor:advisors){
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(beanClass)) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try{
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMather());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
