package com.qworldr.annotation;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

public @interface Protocal {
    //protocalID
    short value();

    String desc() default "";
}
