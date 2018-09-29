package com.qworldr.session;

import com.google.common.collect.Maps;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Component;
import io.netty.channel.Channel;
import java.util.Map;

@Component
public class SessionManager {

    Map<ChannelId,Session>  sessions= Maps.newConcurrentMap();

    public void addSession(Session session){
        Channel channel = session.getChannel();
        sessions.put(channel.id(),session);
    }

    private Session getSession(ChannelId channelId){
        return  sessions.get(channelId);
    }
}
