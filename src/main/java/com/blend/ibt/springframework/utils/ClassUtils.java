package com.blend.ibt.springframework.utils;

/**
 * @author tt
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable e){

        }

        if(cl==null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
