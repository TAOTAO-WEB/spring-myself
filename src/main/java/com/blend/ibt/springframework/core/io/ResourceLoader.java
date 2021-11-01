package com.blend.ibt.springframework.core.io;

/**
 * 资源获取接口 外部用户传递资源地址
 * 根据给定的资源文件地址返回对应的Resource
 * @author tt
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
