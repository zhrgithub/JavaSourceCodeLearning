package com.learnjava.io.netty.timeServer.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 10:39
 */
public class TimeDecoderTwo extends ReplayingDecoder<Void> {
    @Override
    protected void decode(
            ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
//        out.add(in.readBytes(in.readInt()));
        out.add(in.readBytes(4));
    }
}
