package com.tjwoods.annotation;

import java.lang.annotation.*;

/**
 * 这个是自己定义的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {

    String description() default "no description";
}
