package com.qworldr.mmorpg.anno;

import com.qworldr.mmorpg.enu.ReaderType;

import java.lang.annotation.*;


/**
 * @author wujiazhen
 * 声明配置类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resource {
    String value() default "";

    String suffix() default "";

    ReaderType reader() default ReaderType.NONE;

    String path() default  "";
}
