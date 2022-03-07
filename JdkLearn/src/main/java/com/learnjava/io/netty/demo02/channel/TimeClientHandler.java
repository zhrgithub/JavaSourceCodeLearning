package com.learnjava.io.netty.demo02.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 21:08
 *     <p>客户端拦截器处理
 *     <p>它看起来非常简单，看起来与服务器端示例没有任何不同。然而，这个处理程序有时会拒绝工作来提升IndexOutOfBoundsException.
 *     我们将在下一节讨论为什么会发生这种情况。
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf m = (ByteBuf) msg; // (1)在 TCP/IP 中，Netty 将从对等端发送的数据读取到ByteBuf.
    try {
      long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
      System.out.println(new Date(currentTimeMillis));
      ctx.close();
    } finally {
      m.release();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}