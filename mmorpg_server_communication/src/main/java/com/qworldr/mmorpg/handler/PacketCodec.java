package com.qworldr.mmorpg.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.google.common.collect.Maps;
import com.qworldr.mmorpg.exception.ProtocalNotExists;
import com.qworldr.mmorpg.codec.CodecManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;
import java.util.Map;

/**
 *  \--4字节--\-2字节-\-------data-------\
 *   协议长度  协议id     协议数据
 */
public class PacketCodec extends ByteToMessageCodec {
    private Map<Short, Codec> codecs= Maps.newHashMap();
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        int len = in.readInt();
        if(in.readableBytes()<len-4){
            in.readableBytes();
            return;
        }
        short protocalId = in.readShort();
        byte[] data= new byte[len-6];
        in.readBytes(data);
        Codec codec = CodecManager.getInstance().getCodec(protocalId);
        if(codecs==null){
            throw new ProtocalNotExists(String.format("协议号 %d 不存在",protocalId));
        }
        Object decode = codec.decode(data);
        out.add(decode);
    }


}
