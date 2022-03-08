package com.learnjava.io.netty.timeServer.util;

import com.learnjava.io.netty.timeServer.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 12:53
 */
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

  @Override
  protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
    out.writeInt((int) msg.value());
  }
}
