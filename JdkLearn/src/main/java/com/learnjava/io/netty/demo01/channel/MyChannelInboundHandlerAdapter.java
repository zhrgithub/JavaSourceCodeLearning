package com.learnjava.io.netty.demo01.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @author zhr_java@163.com
 * @date 2022/3/6 23:59
 *     <p>入栈管道适配器，继承了入栈操作的所有方法，好处是父类已经实现啦所有管道的方法 可以根据实际业务重写方法逻辑
 */
public class MyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // 打印接收到的数据
    //        ByteBuf in = (ByteBuf) msg;
    //        try {
    //            while (in.isReadable()) { // (1)
    //                System.out.print((char) in.readByte());
    //                System.out.flush();
    //            }
    //        } finally {
    //            ReferenceCountUtil.release(msg); // (2)
    //        }

    // 返回给客户端接收到的数据
    ctx.write(msg)
        .addListener(
            future -> {
              if (future.isSuccess()) {
                System.out.println("响应成功！");
              } else {
                System.out.println("失败原因！！！");
                future.cause();
              }
            });
    List<String> names = ctx.pipeline().names();
    names.stream().forEach(name -> System.out.println("管道名称:"+name));
    ctx.flush();
  }
}
