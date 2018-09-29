package com.qworldr.session;

import com.qworldr.bean.Identity;
import io.netty.channel.Channel;


public interface Session<T> extends Identity<T> {
    Channel getChannel();

    void setId(T id);
}
