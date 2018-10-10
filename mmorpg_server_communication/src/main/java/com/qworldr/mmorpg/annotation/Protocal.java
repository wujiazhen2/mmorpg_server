package com.qworldr.mmorpg.annotation;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Protocal {
    //protocalID
    short value();
    String description() default "";
}
