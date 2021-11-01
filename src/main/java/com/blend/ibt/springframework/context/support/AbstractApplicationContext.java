package com.blend.ibt.springframework.context.support;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.blend.ibt.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.blend.ibt.springframework.beans.factory.config.BeanPostProcessor;
import com.blend.ibt.springframework.context.ApplicationEvent;
import com.blend.ibt.springframework.context.ApplicationListener;
import com.blend.ibt.springframework.context.ConfigurableApplicationContext;
import com.blend.ibt.springframework.context.event.ApplicationEventMulticaster;
import com.blend.ibt.springframework.context.event.ContextClosedEvent;
import com.blend.ibt.springframework.context.event.ContextRefreshedEvent;
import com.blend.ibt.springframework.context.event.SimpleApplicationEventMulticaster;
import com.blend.ibt.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 继承DefaultResourceLoader为了处理xml配置文件
 *
 * @author wangtao
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1. 创建BeanFactory  并且加载BeanDefinition
        refreshBeanFactory();

        //2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3. 添加ApplicationContextAwareProcessor 让继承自ApplicationContextAware的Bean对象感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4. 在Bean实例化之前 执行BeanFactoryProcessors
        invokeBeanFactoryPostProcessors(beanFactory);

        //5. BeanPostProcessors 需要提前于其他bean实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6. 初始化事件发布者
        initApplicationEventMulticaster();

        //7. 注册事件监听器
        registerListeners();

        //8. 提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();

        //9. 发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for(ApplicationListener listener:applicationListeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    public void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name,requiredType);
    }

    /**
     * 注册钩子函数 jvm停止运行时自动调用 用于关闭
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        //执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
