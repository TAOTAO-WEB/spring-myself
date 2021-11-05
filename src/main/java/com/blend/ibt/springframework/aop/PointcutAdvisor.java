package com.blend.ibt.springframework.aop;

/**
 *
 * @author tt
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * get the pointcut that drivers this advisor
     * @return
     */
    Pointcut getPointcut();

}
