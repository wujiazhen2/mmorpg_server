package com.qworldr.mmorpg.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.exception.ProtocalNotExists;
import com.qworldr.mmorpg.codec.CodecManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 *  \--4字节--\-2字节-\-------data-------\
 *   协议长度  协议id     协议数据
 */
public class PacketCodec extends ByteToMessageCodec {


    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        int len = 6;
        Protocal annotation = msg.getClass().getAnnotation(Protocal.class);
        short packetid = annotation.value();
        try {
            Codec codec = CodecManager.getInstance().getCodec(packetid);
            if(codec==null){
                throw new ProtocalNotExists(String.format("协议号 %d 不存在",packetid));
            }
            byte[] data = codec.encode(msg);
            len += data.length;
            out.writeInt(len);
            out.writeShort(packetid);
            out.writeBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        try {
            int len = in.readInt();
            if (in.readableBytes() < len - 4) {
                in.readableBytes();
                return;
            }
            short protocalId = in.readShort();
            byte[] data = new byte[len - 6];
            in.readBytes(data);
            Codec codec = CodecManager.getInstance().getCodec(protocalId);
            if (codec == null) {
                throw new ProtocalNotExists(String.format("协议号 %d 不存在", protocalId));
            }
            Object decode = codec.decode(data);
            out.add(decode);
        }finally {
            ReferenceCountUtil.release(in);
        }
    }


}
