package com.learnjava.concurent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhr_java@163.com
 * @date 2022/3/24 16:35
 */
public class Worker implements Runnable {
  private final CountDownLatch startSignal;
  private final CountDownLatch doneSignal;

  Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
    this.startSignal = startSignal;
    this.doneSignal = doneSignal;
  }

  @Override
  public void run() {
    try {
      startSignal.await();
      doWork();
      doneSignal.countDown();
    } catch (InterruptedException ex) {
    } // return;
  }

  void doWork() {
    System.out.println("worker done !!!");
  }
}
