package com.qworldr.mmorpg.constants;

import com.qworldr.mmorpg.session.Session;
import io.netty.util.AttributeKey;

public class Constants {
    public static  final AttributeKey<Session> SESSIONKEY = AttributeKey.valueOf("session");
}
