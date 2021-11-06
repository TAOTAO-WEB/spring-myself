package com.blend.ibt.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author wangtao
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
