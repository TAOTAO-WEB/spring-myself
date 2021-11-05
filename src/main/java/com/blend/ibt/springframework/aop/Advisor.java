package com.blend.ibt.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author wangtao
 */
public interface Advisor {

    /**
     * return the advice part of this aspect
     * an advice may be an interceptor, a before advice,a throws advice,etc.
     * @return
     */
    Advice getAdvice();

}
