package com.learnjava.io.netty.demo01;

import com.learnjava.io.netty.demo01.channel.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class ServerSocket {

  public static void main(String[] args) {
    // 创建mainReactor
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    // 创建工作线程组
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    final ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap

        // 组装NioEventLoopGroup，NioEventLoopGroup里面有很多个NioEventLoop，相当于线程池
        .group(boosGroup, workerGroup)
        // 设置channel类型为NIO类型,这里指的是每一个管道都是这种类型的；而且一个NioEventLoop可以处理近一千个管道，但是同时只能是一对一的处理
        // 也就是说同一时刻一个NioEventLoop处理一个channel；NioEventLoop相当于一个线程处理一个队列里面的数据
        .channel(NioServerSocketChannel.class)
        // 设置服务端管道的连接配置参数，根据名称返回对应的操作类型，并且设置对应的属性值
        .option(ChannelOption.SO_BACKLOG, 1024) //
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childOption(ChannelOption.TCP_NODELAY, true)
        // 对客户端的管道进行拦截处理，配置入站、出站事件handler（规则：firt in last out）
        .childHandler(
            new ChannelInitializer<NioSocketChannel>() {

              @Override
              protected void initChannel(NioSocketChannel ch) {
                // 配置入站、出站事件channel；默认是根据函数名称先入栈，然后再出站；
                // 可以设置ChannelInboundHandler 用于处理入站 I/O 事件。ChannelOutboundHandler 用于处理出站 I/O 操作。
                // ChannelInboundHandlerAdapter 用于处理入站 I/O 事件。ChannelOutboundHandlerAdapter 用于处理出站
                // I/O 操作。
                // ChannelDuplexHandler 用于处理入站和出站事件。
                // 入栈顺序：1、2、5；出栈顺序：5、4、3
                ch.pipeline().addLast("1", new MyChannelInboundHandler());
                ch.pipeline().addLast("2", new MyChannelInboundHandlerAdapter());
                ch.pipeline().addLast("3", new MyChannelOutboundHandler());
                ch.pipeline().addLast("4", new MyChannelOutboundHandlerAdapter());
                ch.pipeline().addLast("5", new MyChannelDuplexHandler());
              }
            });
    // 绑定端口
    int port = 8080;
    serverBootstrap
        .bind(port)
        .addListener(
            future -> {
              if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
              } else {
                System.err.println("端口[" + port + "]绑定失败!");
              }
            });
  }
}
