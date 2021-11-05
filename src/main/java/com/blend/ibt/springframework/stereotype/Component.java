package com.blend.ibt.springframework.stereotype;

import java.lang.annotation.*;

/**
 *
 * @author tt
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
