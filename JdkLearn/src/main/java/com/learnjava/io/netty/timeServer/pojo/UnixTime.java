package com.learnjava.io.netty.timeServer.pojo;

import java.util.Date;

/**
 * @author zhr_java@163.com
 * @date 2022/3/8 12:29
 */
public class UnixTime {
  private final long value;

  public UnixTime() {
    this(System.currentTimeMillis() / 1000L + 2208988800L);
  }

  public UnixTime(long value) {
    this.value = value;
  }

  public long value() {
    return value;
  }

  @Override
  public String toString() {
    return new Date((value() - 2208988800L) * 1000L).toString();
  }
}
