package com.blend.ibt.springframework.beans.factory;

/**
 * bean的销毁操作
 * @author tt
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
