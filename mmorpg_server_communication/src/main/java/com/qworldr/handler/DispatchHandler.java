package com.qworldr.handler;

import com.qworldr.dispatcher.InvokerDefinition;
import com.qworldr.dispatcher.InvokerManager;
import com.qworldr.thread.DispatcherExecutor;
import com.qworldr.thread.HashDispatcherTask;
import com.qworldr.utils.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class DispatchHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private InvokerManager invokerManager;

    private DispatcherExecutor dispatcherExecutor;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokerDefinition invokerDefintion = invokerManager.getInvokerDefintion(msg.getClass());
        dispatcherExecutor.submit(new HashDispatcherTask() {
            @Override
            public String getDispatcherString() {
                return ctx.channel().id().asLongText();
            }

            @Override
            public void run() {
                invokerDefintion.invoke(ChannelUtils.getSession(ctx.channel()),msg);
            }
        });
    }


    public void setDispatcherExecutor(DispatcherExecutor dispatcherExecutor){
        this.dispatcherExecutor=dispatcherExecutor;
    }
}
