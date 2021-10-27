package com.blend.ibt.springframework.beans.factory;

public interface InitializingBean {

    /**
     * bean处理了属性填充后调用 在属性设置后执行
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
