package com.qworldr.mmorpg.utils;

import com.qworldr.mmorpg.constants.Constants;
import com.qworldr.mmorpg.session.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class ChannelUtils {

    public static Session getSession(Channel channel){
        Attribute<Session> attr = channel.attr(Constants.SESSIONKEY);
        return attr.get();
    }
}
