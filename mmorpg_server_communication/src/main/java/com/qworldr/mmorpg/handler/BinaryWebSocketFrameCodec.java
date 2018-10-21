package com.qworldr.mmorpg.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.codec.CodecManager;
import com.qworldr.mmorpg.exception.ProtocalNotExists;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;


public class BinaryWebSocketFrameCodec extends MessageToMessageCodec {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Protocal annotation = msg.getClass().getAnnotation(Protocal.class);
        if(annotation!=null) {
            super.write(ctx, msg, promise);
        }else {
            ctx.write(msg,promise);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        ByteBuf in=null;
        if(msg instanceof BinaryWebSocketFrame){
            BinaryWebSocketFrame binaryWebSocketFrame=(BinaryWebSocketFrame)msg;
            in=binaryWebSocketFrame.content();
        }else{
            throw new RuntimeException("message is not BinaryWebSocketFrame");
        }
        int len = in.readInt();
        if(in.readableBytes()<len-4){
            in.readableBytes();
            return;
        }
        short protocalId = in.readShort();
        byte[] data= new byte[len-6];
        in.readBytes(data);
        Codec codec = CodecManager.getInstance().getCodec(protocalId);
        if(codec==null){
            throw new ProtocalNotExists(String.format("协议号 %d 不存在",protocalId));
        }
        Object decode = codec.decode(data);
        out.add(decode);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws IOException {
        int len = 6;
        Protocal annotation = msg.getClass().getAnnotation(Protocal.class);
        short packetid = annotation.value();
        ByteBuf byteBuf =ctx.alloc().buffer();
        try {
            Codec codec = CodecManager.getInstance().getCodec(packetid);
            if(codec==null){
                throw new ProtocalNotExists(String.format("协议号 %d 不存在",packetid));
            }
            byte[] data = codec.encode(msg);
            len += data.length;
            byteBuf.writeInt(len);
            byteBuf.writeShort(packetid);
            byteBuf.writeBytes(data);
            BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(byteBuf);
            out.add(binaryWebSocketFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
