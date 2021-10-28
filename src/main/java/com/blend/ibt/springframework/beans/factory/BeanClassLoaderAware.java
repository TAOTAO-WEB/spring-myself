package com.blend.ibt.springframework.beans.factory;

/**
 * 感知所属的ClassLoader
 * @author tt
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
