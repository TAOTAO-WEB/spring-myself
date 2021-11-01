package com.blend.ibt.springframework.context.event;

import com.blend.ibt.springframework.context.ApplicationContext;
import com.blend.ibt.springframework.context.ApplicationEvent;

/**
 * @author tt
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
