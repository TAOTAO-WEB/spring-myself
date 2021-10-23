package com.blend.ibt.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.blend.ibt.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通过classloader读取path下的文件信息
 * @author tt
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = classLoader!=null? classLoader: ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path){
        this(path,null);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        //读取文件
        InputStream in = classLoader.getResourceAsStream(path);
        if(in==null){
            throw new FileNotFoundException(this.path+" cannot be opened because it does not exist");
        }
        return in;
    }


}
