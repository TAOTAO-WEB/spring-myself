package com.blend.ibt.springframework.aop.aspectj;

import com.blend.ibt.springframework.aop.Pointcut;
import com.blend.ibt.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 实现了PointcutAdvisor接口，把切面pointcut，拦截方法advice和具体的拦截表达式包装在一起
 *
 * @author tt
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    //切面
    private AspectJExpressionPointcut pointcut;

    //表达式
    private String expression;

    //具体的拦截方法
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        if(null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
