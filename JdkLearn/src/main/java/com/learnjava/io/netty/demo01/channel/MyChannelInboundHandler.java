package com.learnjava.io.netty.demo01.channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

/**
 * @author zhr_java@163.com
 * @date 2022/3/6 23:53
 *     <p>实现ChannelInboundHandler接口 入栈处理器，主要用于读取管道中的数据，把管道中的字节数据转化成字符数据，管道注册，活动，读取，移除等等。。。
 *     方法都需要自己亲自实现
 */
public class MyChannelInboundHandler implements ChannelInboundHandler {
  @Override
  public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
    channelHandlerContext.fireChannelRegistered();
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
    channelHandlerContext.fireChannelRead(o);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o)
      throws Exception {}

  @Override
  public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext)
      throws Exception {}

  @Override
  public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {}

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable)
      throws Exception {}
}
