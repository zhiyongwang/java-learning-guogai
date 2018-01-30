package com.da.study01;

/**
 * Created by guo on 2018/1/29.
 */
public class Daemon {
    public static void main(String[] args) {
    Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
    thread.setDaemon(true);
    thread.start();
    }
    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtil.second(10);
            } finally {
                System.out.println("DaemonThread finally run");  //为啥会不执行呢？？？
            }
        }
    }
}
