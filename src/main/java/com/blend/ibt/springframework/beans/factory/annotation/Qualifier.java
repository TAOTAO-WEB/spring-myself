package com.blend.ibt.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 *
 * @author tt
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
