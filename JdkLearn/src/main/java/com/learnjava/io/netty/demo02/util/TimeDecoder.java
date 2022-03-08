package com.learnjava.io.netty.demo02.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 10:23
 */
public class TimeDecoder
    extends ByteToMessageDecoder { // (1)ByteToMessageDecoder是一种ChannelInboundHandler可以轻松处理碎片问题的实现。
  @Override
  protected void decode(
      ChannelHandlerContext ctx,
      ByteBuf in,
      List<Object> out) { // (2)ByteToMessageDecoderdecode()每当接收到新数据时，都会使用内部维护的累积缓冲区调用该方法。

    if (in.readableBytes() < 4) {
      return; // (3)decode()out当累积缓冲区中没有足够的数据时，可以决定不添加任何内容。当收到更多数据时ByteToMessageDecoder将再次调用。decode()
    }
    // (4)如果decode()添加一个对象out，则意味着解码器成功解码了一条消息。ByteToMessageDecoder将丢弃累积缓冲区的读取部分。
    // 请记住，您不需要解码多条消息。ByteToMessageDecoder将继续调用该decode()方法，直到它对out.
    out.add(in.readBytes(4));
  }
}
