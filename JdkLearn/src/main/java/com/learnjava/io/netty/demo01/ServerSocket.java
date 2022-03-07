package com.learnjava.io.netty.demo01;

import com.learnjava.io.netty.demo01.channel.MyChannelInboundHandlerAdapter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 17:53
 *     <p>用于实现socketServer服务器
 */
public class ServerSocket {
  // 绑定端口
  private int port = 8080;

  public ServerSocket(int port) {
    this.port = port;
  }

  public void run() {
    // 当前是主从Rector的多线程模式
    // 创建mainReactor，根据需要设置接收连接的线程池的大小，这里默认是1（支持的并发量在一千左右），最大不超过Integer.MAX_VALUE
    NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    // 创建工作线程组,根据需要设置数据处理的线程池的大小，这里默认是10（支持的并发量在一万左右），最大不超过Integer.MAX_VALUE
    NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);

    try {
      // 服务器端的启动引导
      final ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap

          // 组装NioEventLoopGroup，NioEventLoopGroup里面有很多个NioEventLoop，相当于线程池
          .group(bossGroup, workerGroup)
          // 设置channel类型为NIO类型,这里指的是每一个管道都是这种类型的；而且一个NioEventLoop可以处理近一千个管道，但是同时只能是一对一的处理
          // 也就是说同一时刻一个NioEventLoop处理一个channel；NioEventLoop相当于一个线程处理一个队列里面的数据
          .channel(NioServerSocketChannel.class)

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
                  ch.pipeline().addLast("1", new MyChannelInboundHandlerAdapter());
                }
              })
          // 设置服务端管道的连接配置参数，根据名称返回对应的操作类型，并且设置对应的属性值
          .option(ChannelOption.SO_BACKLOG, 1024) // option()用于NioServerSocketChannel接受传入连接的
          .childOption(
              ChannelOption.SO_KEEPALIVE,
              true) // childOption() 用于父 ServerChannel 接受的 Channels，在本例中为 NioSocketChannel。
          .childOption(ChannelOption.TCP_NODELAY, true);

      // 可以对返回是ChannelFuture的对象添加监听
      ChannelFuture future =
          serverBootstrap
              .bind(port)
              .sync()
              .addListener(
                  future1 -> {
                    if (future1.isSuccess()) {
                      System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
                    } else {
                      System.err.println("端口[" + port + "]绑定失败!");
                    }
                  });
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ServerSocket serverSocket = new ServerSocket(8080);
    serverSocket.run();
  }
}
