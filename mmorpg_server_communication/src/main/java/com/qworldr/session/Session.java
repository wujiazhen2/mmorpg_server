package com.qworldr.session;

import com.qworldr.bean.Identity;
import io.netty.channel.Channel;


public interface Session extends Identity {
    Channel getChannel();
}
