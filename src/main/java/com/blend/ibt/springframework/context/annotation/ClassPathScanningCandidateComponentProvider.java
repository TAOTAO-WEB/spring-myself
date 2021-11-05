package com.blend.ibt.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.stereotype.Component;
import com.blend.ibt.springframework.utils.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author tt
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描所有@Component注解的Bean对象
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for(Class<?> clazz:classes){
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
