package com.blend.ibt.springframework.context.event;

import com.blend.ibt.springframework.context.ApplicationEvent;
import com.blend.ibt.springframework.context.ApplicationListener;

/**
 * 事件广播器
 * @author tt
 */
public interface ApplicationEventMulticaster {

    /**
     * add a listener to be notified of all events
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);


    /**
     * remove a listener from the notification list
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);


    /**
     * 广播
     * multicast the given application event to appropriate listeners
     * @param event
     */
    void multicastEvent(ApplicationEvent event);



}
