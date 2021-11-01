package com.blend.ibt.springframework.context.event;

import com.blend.ibt.springframework.beans.factory.BeanFactory;
import com.blend.ibt.springframework.context.ApplicationEvent;
import com.blend.ibt.springframework.context.ApplicationListener;

/**
 *
 * @author tt
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for(final ApplicationListener listener:getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}
