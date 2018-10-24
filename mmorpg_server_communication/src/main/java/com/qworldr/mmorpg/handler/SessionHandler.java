package com.qworldr.mmorpg.handler;

import com.qworldr.mmorpg.bean.IdentityProvider;
import com.qworldr.mmorpg.constants.Constants;
import com.qworldr.mmorpg.session.Session;
import com.qworldr.mmorpg.session.TcpSession;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@ChannelHandler.Sharable
public class SessionHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private IdentityProvider identityProvider;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Attribute<Session> attr = ctx.channel().attr(Constants.SESSIONKEY);
        attr.compareAndSet(null, TcpSession.valueOf(ctx.channel()));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Attribute<Session> attr = ctx.channel().attr(Constants.SESSIONKEY);
        identityProvider.clearIdentity(attr.get());
        attr.compareAndSet(attr.get(),null);

        super.channelInactive(ctx);
    }
}
