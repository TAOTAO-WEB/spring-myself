package com.blend.ibt.springframework.context.support;

import com.blend.ibt.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.blend.ibt.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * @author tt
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(null!=configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }


    }

    /**
     * 从入口上下文类，拿到配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();
}
