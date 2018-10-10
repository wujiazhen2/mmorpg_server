package com.qworldr.mmorpg.anno;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Generator {
    String value() default "uuid";
}
