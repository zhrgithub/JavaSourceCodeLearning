package com.learnjava.io.nio.demo02.client;

import java.io.IOException;

/**
 * @author zhr_java@163.com
 *  * @date 2022/3/4 13:17
 */
public class ClientA {

    public static void main(String[] args)
            throws IOException {
        new NioClient().start("ClientA");
    }

}
