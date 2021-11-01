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

    /**
     * check whether the specified class is a CGLIB-generated class
     * @param clazz
     * @return
     */
    public static boolean isCglibProxyClass(Class<?> clazz){
        return (clazz != null && isCglibProxyClassName(clazz.getName()));

    }

    /**
     *
     * @param className
     * @return
     */
    public static boolean isCglibProxyClassName(String className){
        return (className != null && className.contains("$$"));
    }

}
