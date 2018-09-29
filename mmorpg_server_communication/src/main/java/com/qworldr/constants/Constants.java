package com.qworldr.constants;

import com.qworldr.session.Session;
import io.netty.util.AttributeKey;

public class Constants {
    public static  final AttributeKey<Session> SESSIONKEY = AttributeKey.valueOf("session");
}
