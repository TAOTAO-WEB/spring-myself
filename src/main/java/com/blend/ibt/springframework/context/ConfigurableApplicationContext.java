package com.blend.ibt.springframework.context;

import com.blend.ibt.springframework.beans.BeansException;

/**
 *
 *
 * @author tt
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
