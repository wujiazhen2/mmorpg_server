package com.qworldr.mmorpg.anno;

import java.lang.annotation.*;


/**
 * @author wujiazhen
 * 用于声明实体类id的生成策略
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Generator {
    String value() default "uuid";
}
