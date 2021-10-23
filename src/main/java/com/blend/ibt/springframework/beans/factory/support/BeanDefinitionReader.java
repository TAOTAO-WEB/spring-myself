package com.blend.ibt.springframework.beans.factory.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.core.io.Resource;
import com.blend.ibt.springframework.core.io.ResourceLoader;

/**
 * @author tt
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
