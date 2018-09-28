package com.qworldr.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Protocal {
    //protocalID
    short value();

    String desc() default "";
}
