package com.blend.ibt.springframework.context;

import java.util.EventObject;

/**
 * 事件功能的抽象类
 * @author tt
 */
public abstract class ApplicationEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
