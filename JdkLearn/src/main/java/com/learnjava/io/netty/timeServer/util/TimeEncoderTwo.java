package com.learnjava.io.netty.timeServer.util;

import com.learnjava.io.netty.timeServer.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 15:33
 */
public class TimeEncoderTwo extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int)m.value());
        System.out.println("编码成功！！！");
        ctx.write(encoded, promise); // (1)
    }

}
