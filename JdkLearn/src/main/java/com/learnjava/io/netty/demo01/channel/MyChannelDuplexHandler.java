package com.learnjava.io.netty.demo01.channel;

import io.netty.channel.ChannelDuplexHandler;

/**
 * @author zhr_java@163.com
 * @date 2022/3/7 00:03
 *
 * 用于处理入站和出站事件,继承啦ChannelDuplexHandler的所有方法，可以根据业务重写父类的方法，该类包含啦入栈和出栈的全部操作
 */
public class MyChannelDuplexHandler extends ChannelDuplexHandler {
}
