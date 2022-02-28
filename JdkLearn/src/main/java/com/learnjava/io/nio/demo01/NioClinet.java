package com.learnjava.io.nio.demo01;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author lhy
 * @date 2021/5/22
 */
public class NioClinet {

    public static void main(String[] args) throws Exception {

        // 获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));

        System.out.println("创建selector之前");


        Selector selector = Selector.open();
        // 切换至非阻塞模式
        channel.configureBlocking(false);

        Scanner scan = new Scanner(System.in);

        channel.register(selector, SelectionKey.OP_READ);

        while (scan.hasNext()) {
            String next = scan.nextLine();
            // 向缓冲区里写入数据
            if (next!=null&&next.length()>0){
                channel.write(Charset.forName("UTF-8").encode(next));
            }
        }

        // 关闭通道
        channel.close();
    }
}
