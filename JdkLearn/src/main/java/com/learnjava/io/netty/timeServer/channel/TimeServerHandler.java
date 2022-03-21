package com.learnjava.io.netty.timeServer.channel;

import com.learnjava.io.netty.timeServer.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 20:31
 *     <p>时间服务器，和之前的例子不同，这里实现的是在建立连接的时候立即发送一个32位的数据信息，不接收任何请求，并在消息发送后关闭连接
 *     <p>构造和发送消息，以及在完成时关闭连接
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  // 可以将所有的客户端连接管道存入管道集合中
  public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  @Override
  public void channelActive(final ChannelHandlerContext ctx) { // (1)建立连接并准备好生成流量，写一个表示当前时间的 32 位整数。
    Channel channel = ctx.channel();
    channels.add(channel);
    // (2)开辟4个字节的缓存
    final ByteBuf time = ctx.alloc().buffer(4);
    //        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
    //
    //        final ChannelFuture f =
    //            ctx.writeAndFlush(time); //
    // (3)写入管道并刷新，netty不需要ByteBuffer.flip()，因为netty对不同的操作类型有不同的指针
    //        f.addListener(future -> {
    //                assert f == future;
    //                System.out.println("操作成功");
    //                ctx.close();
    //            }); // (4)添加发送信息的监听事件，如果f与future相等表示发送成功
    UnixTime unixTime = new UnixTime();
    time.writeInt((int) unixTime.value());
    ChannelFuture f = ctx.writeAndFlush(time);
    f.addListener(
        future -> {
          assert future == f;
          System.out.println("服务器响应成功！！！");
        });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    cause.printStackTrace();
    ctx.close();
  }
}
