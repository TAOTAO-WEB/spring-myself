package com.blend.ibt.springframework.context;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.Aware;

/**
 * 感知到所属的ApplicationContext
 * @author tt
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
