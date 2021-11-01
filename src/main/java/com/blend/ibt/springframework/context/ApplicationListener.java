package com.blend.ibt.springframework.context;

import java.util.EventListener;

/**
 * @author tt
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event
     * @param event
     */
    void onApplicationEvent(E event);

}
