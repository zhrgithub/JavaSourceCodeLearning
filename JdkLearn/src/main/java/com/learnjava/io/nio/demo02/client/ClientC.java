package com.learnjava.io.nio.demo02.client;


import java.io.IOException;

public class ClientC {

    public static void main(String[] args)
            throws IOException {
        new NioClient().start("ClientC");
    }

}
