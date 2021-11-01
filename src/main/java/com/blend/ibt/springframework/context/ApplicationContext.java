package com.blend.ibt.springframework.context;

import com.blend.ibt.springframework.beans.factory.HierarchicalBeanFactory;
import com.blend.ibt.springframework.beans.factory.ListableBeanFactory;
import com.blend.ibt.springframework.core.io.ResourceLoader;

/**
 * 应用上下文相关接口
 *
 * @author tt
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {

}
