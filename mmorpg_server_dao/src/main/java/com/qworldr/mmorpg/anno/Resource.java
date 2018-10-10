package com.qworldr.mmorpg.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resource {
    String value() default "";
    String suffix() default "";
    String reader() default "";
    String path() default  "";
}
