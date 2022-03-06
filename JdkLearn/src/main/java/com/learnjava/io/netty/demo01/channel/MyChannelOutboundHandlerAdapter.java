package com.learnjava.io.netty.demo01.channel;

import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 00:01
 *
 * 出站操作，继承啦ChannelOutboundHandlerAdapter类，好处是父类已经实现啦所有管道的方法
 * 自己可以根据业务重写父类中的管道方法
 */
public class MyChannelOutboundHandlerAdapter extends ChannelOutboundHandlerAdapter {
}
