package com.qworldr.mmorpg.common.utils;

import com.qworldr.mmorpg.session.TcpSession;

/**
 * @Author wujiazhen
 */
public class PacketUtils {

    public static void sendPacket(TcpSession session, Object packet){
        session.getChannel().writeAndFlush(packet);
    }
}
