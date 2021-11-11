package com.blend.ibt.springframework.beans.factory;

import com.blend.ibt.springframework.beans.BeansException;

/**
 *
 * @author tt
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
