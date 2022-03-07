package com.learnjava.io.netty.demo02.client;

import com.learnjava.io.netty.demo02.channel.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 21:07
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)Bootstrap与ServerBootstrap非服务器通道类似，但它是客户端的管道引导。
            b.group(workerGroup); // (2)只设置一个EventLoopGroup，它将同时用作boss组和worker组。即使，BOSS、worker不用于客户端。
            b.channel(NioSocketChannel.class); // (3)用于创建客户端管道
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)这里没有用childOption()，因为客户端没有parent
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)通过connect方法绑定终端地址并连接

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
