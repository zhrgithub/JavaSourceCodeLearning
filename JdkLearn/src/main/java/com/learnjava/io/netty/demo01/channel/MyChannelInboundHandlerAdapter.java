package com.learnjava.io.netty.demo01.channel;

import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhr_java@163.com
 * @date 2022/3/6 23:59
 *
 * 入栈管道适配器，继承了入栈操作的所有方法，好处是父类已经实现啦所有管道的方法
 * 可以根据实际业务重写方法逻辑
 */
public class MyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
}
