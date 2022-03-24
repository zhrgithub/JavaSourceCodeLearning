package com.learnjava.concurent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhr_java@163.com
 * @date 2022/3/24 16:34
 */
public class CountDownLatchDemo {
  public static void main(String[] args) throws InterruptedException {
    //
    Integer N = 5;
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch doneSignal = new CountDownLatch(N);

    for (int i = 0; i < N; ++i) { // create and start threads
      new Thread(new Worker(startSignal, doneSignal)).start();
    }

    doSomethingElse(); // don't let run yet
    startSignal.countDown(); // let all threads proceed
    doSomethingElse();
    doneSignal.await(); // wait for all to finish
  }
  private static void doSomethingElse(){
    System.out.println("don't let run yet");
  }

}
