package com.learnjava.io.netty.demo02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 20:31
 *     <p>时间服务器，和之前的例子不同，这里实现的是在建立连接的时候立即发送一个32位的数据信息，不接收任何请求，并在消息发送后关闭连接
 *     <p>构造和发送消息，以及在完成时关闭连接
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(final ChannelHandlerContext ctx) { // (1)
    final ByteBuf time = ctx.alloc().buffer(4); // (2)
    time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

    final ChannelFuture f = ctx.writeAndFlush(time); // (3)
    f.addListener(
        new ChannelFutureListener() {

          @Override
          public void operationComplete(ChannelFuture future) {

            assert f == future;
            ctx.close();
          }
        }); // (4)
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    cause.printStackTrace();
    ctx.close();
  }
}
