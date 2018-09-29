package com.qworldr.handler;

import com.qworldr.constants.Constants;
import com.qworldr.session.Session;
import com.qworldr.session.TcpSession;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class SessionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Attribute<Session> attr = ctx.channel().attr(Constants.SESSIONKEY);
        attr.compareAndSet(null, TcpSession.valueOf(ctx.channel()));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Attribute<Session> attr = ctx.channel().attr(Constants.SESSIONKEY);
        attr.compareAndSet(attr.get(),null);
        super.channelInactive(ctx);
    }
}
