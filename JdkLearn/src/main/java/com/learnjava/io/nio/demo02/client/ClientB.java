package com.learnjava.io.nio.demo02.client;


import java.io.IOException;

public class ClientB {

    public static void main(String[] args)
            throws IOException {
        new NioClient().start("ClientB");
    }

}
