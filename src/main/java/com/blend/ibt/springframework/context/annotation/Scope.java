package com.blend.ibt.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 用于配置作用顶多自定义注解，方便通过配置Bean对象注解的时候，拿到Bean对象的作用域
 * 默认singleton
 * @author tt
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    String value() default "singleton";
}
