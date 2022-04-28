package com.jhj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jeremy
 * @date 2022/4/28 16:39
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestField {

    String name() default "";

    Class type() default Class.class;

    String comment() default "";
}
