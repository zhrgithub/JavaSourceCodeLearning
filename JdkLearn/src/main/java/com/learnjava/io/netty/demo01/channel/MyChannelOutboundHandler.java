package com.learnjava.io.netty.demo01.channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * @author zhr_java@163.com
 * @date 2022/3/6 23:55
 *     <p>实现ChannelOutboundHandler接口 主要用于写数据，把对象数据转化成字节；出站的接口主要用于绑定、连接、断开连接、关闭、等等、、、
 */
public class MyChannelOutboundHandler implements ChannelOutboundHandler {
  @Override
  public void bind(
      ChannelHandlerContext channelHandlerContext,
      SocketAddress socketAddress,
      ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.bind(socketAddress, channelPromise);
  }

  @Override
  public void connect(
      ChannelHandlerContext channelHandlerContext,
      SocketAddress socketAddress,
      SocketAddress localAddress,
      ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.connect(localAddress, channelPromise);
  }

  @Override
  public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.disconnect(channelPromise);
  }

  @Override
  public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.close(channelPromise);
  }

  @Override
  public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.deregister(channelPromise);
  }

  @Override
  public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
    System.out.println("coming in MyChannelOutboundHandler");
  }

  @Override
  public void write(
      ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise)
      throws Exception {
    channelHandlerContext.write(o, channelPromise);
  }

  @Override
  public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
    channelHandlerContext.flush();
  }

  @Override
  public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable)
      throws Exception {
    channelHandlerContext.fireExceptionCaught(throwable);
  }
}
