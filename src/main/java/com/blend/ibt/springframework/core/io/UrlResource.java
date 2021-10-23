package com.blend.ibt.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通过http读取url地址 可以把配置文件放在云服务器上
 * @author tt
 */
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try{
            return con.getInputStream();
        }catch (IOException e){
            if(con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }
}
