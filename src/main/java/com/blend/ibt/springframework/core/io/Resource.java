package com.blend.ibt.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author tt
 */
public interface Resource {
    /**
     * 获取inputStream流的方法
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
