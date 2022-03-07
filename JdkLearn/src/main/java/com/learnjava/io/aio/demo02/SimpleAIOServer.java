package com.learnjava.io.aio.demo02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SimpleAIOServer {

  public static void main(String[] args) {

    try {
      final int port = 5555;

      // 首先打开一个ServerSocket通道并获取AsynchronousServerSocketChannel实例：
      AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();

      // 绑定需要监听的端口到serverSocketChannel:
      serverSocketChannel.bind(new InetSocketAddress(port));

      // 实现一个CompletionHandler回调接口handler，
      // 之后需要在handler的实现中处理连接请求和监听下一个连接、数据收发，以及通信异常。
      CompletionHandler<AsynchronousSocketChannel, Object> handler =
          new CompletionHandler<AsynchronousSocketChannel, Object>() {

            @Override
            public void completed(final AsynchronousSocketChannel result, final Object attachment) {

              // 继续监听下一个连接请求
              serverSocketChannel.accept(attachment, this);
              try {
                System.out.println("接受了一个连接：" + result.getRemoteAddress().toString());
                // 给客户端发送数据并等待发送完成，可以根据客户端请求的文件名称给客户端发送文件
                result.write(ByteBuffer.wrap("From Server:Hello i am server".getBytes())).get();
                ByteBuffer readBuffer = ByteBuffer.allocate(128);
                // 阻塞等待客户端接收数据
                Integer length = result.read(readBuffer).get();

                System.out.println(
                    new String(readBuffer.array(), 0, length, Charset.forName("UTF-8")));

              } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
              }
            }

            @Override
            public void failed(final Throwable exc, final Object attachment) {

              System.out.println("出错了：" + exc.getMessage());
            }
          };
      serverSocketChannel.accept(null, handler);
      // 由于serverSocketChannel.accept(null, handler);是一个异步方法，调用会直接返回，
      // 为了让子线程能够有时间处理监听客户端的连接会话，
      // 这里通过让主线程休眠一段时间(当然实际开发一般不会这么做)以确保应用程序不会立即退出。
      TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
