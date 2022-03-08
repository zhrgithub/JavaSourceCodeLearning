package com.learnjava.io.netty.timeServer.util;

import com.learnjava.io.netty.timeServer.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 12:33
 */
public class TimeDecoderThree extends ReplayingDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    if (in.readableBytes() < 4) {
      return;
    }

    out.add(new UnixTime(in.readUnsignedInt()));
  }
}
