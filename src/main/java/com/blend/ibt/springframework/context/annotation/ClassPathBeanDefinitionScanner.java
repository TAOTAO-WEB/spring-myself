package com.blend.ibt.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.blend.ibt.springframework.stereotype.Component;

import java.util.Set;

/**
 * ClassPathBeanDefinitionScanner是继承自ClassPathScanningCandidateComponentProvider的具体扫描包处理的类
 * 在doScan中除了获取到扫描的类信息以后，还需要获取Bean的作用域和类名，如果不配置类名基本是把首字母缩写
 * @author tt
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public void doScan(String... basePackages){
        for(String basePackage:basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for(BeanDefinition beanDefinition:candidates){
                //解析Bean的作用域singleton prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if(null!=scope) return scope.value();
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
