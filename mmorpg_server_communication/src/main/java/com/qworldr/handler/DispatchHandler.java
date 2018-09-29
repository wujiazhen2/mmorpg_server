package com.qworldr.handler;

import com.qworldr.bean.IdentityProvide;
import com.qworldr.dispatcher.InvokerDefinition;
import com.qworldr.dispatcher.InvokerManager;
import com.qworldr.exception.PrivilegeException;
import com.qworldr.server.GameServer;
import com.qworldr.thread.DispatcherExecutor;
import com.qworldr.thread.HashDispatcherTask;
import com.qworldr.utils.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class DispatchHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchHandler.class);
    @Autowired
    private InvokerManager invokerManager;
    private DispatcherExecutor dispatcherExecutor;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokerDefinition invokerDefintion = invokerManager.getInvokerDefintion(msg.getClass());
        dispatcherExecutor.submit(new HashDispatcherTask() {
            @Override
            public String getDispatcherString() {
                return String.valueOf(ChannelUtils.getSession(ctx.channel()).getId());
            }

            @Override
            public void run() {
                try {
                    invokerDefintion.invoke(ChannelUtils.getSession(ctx.channel()),msg);
                } catch (Exception e) {
                    LOGGER.debug("调用错误",e);
                }
            }
        });
    }


    public void setDispatcherExecutor(DispatcherExecutor dispatcherExecutor){
        this.dispatcherExecutor=dispatcherExecutor;
    }
}
