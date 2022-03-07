package com.learnjava.io.aio.demo01;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

public class ReadFromFile {
  public static void main(String[] args) throws Exception {
    Path file = Paths.get("/Users/zhr/Downloads/a.txt");
    AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

    ByteBuffer buffer = ByteBuffer.allocate(100_000);
    // 读取管道中的内容
    Future<Integer> result = channel.read(buffer, 0);

    // 判断是否读取完成，没有完成的话一直是读取中，对CPU内存消耗很大
    while (!result.isDone()) {
      ProfitCalculator.calculateTax();
    }
    // 读取完成，通知：文本总共被读取了多少个字节
    Integer bytesRead = result.get();
    String str = new String(buffer.array(), 0, bytesRead);
    System.out.println("Bytes read [" + bytesRead + "]" + "fileText:" + str);
  }
}

class ProfitCalculator {
  public ProfitCalculator() {
    System.out.println("Profit Calculator completed !!!");
  }

  public static void calculateTax() {
    System.out.println("计算税务完毕");
  }
}
