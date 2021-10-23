package com.blend.ibt.springframework.core.io;

/**
 * 资源获取接口 外部用户传递资源地址
 * @author tt
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
