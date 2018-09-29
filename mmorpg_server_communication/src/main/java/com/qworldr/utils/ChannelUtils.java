package com.qworldr.utils;

import com.qworldr.constants.Constants;
import com.qworldr.session.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class ChannelUtils {

    public static Session getSession(Channel channel){
        Attribute<Session> attr = channel.attr(Constants.SESSIONKEY);
        return attr.get();
    }
}
