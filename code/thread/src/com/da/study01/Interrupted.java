package com.da.study01;

import java.util.concurrent.TimeUnit;

/**
 * Created by guo on 2018/1/30.
 * 中断标识位
 */
public class Interrupted {
    public static void main(String[] args) throws Exception {
        //SleepThrea不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepRunner");
        sleepThread.setDaemon(true);
        //busy不行的运行
        Thread busyThread = new Thread(new BusyRunner(),"BusyRunner");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //休眠5秒后让sleepThread和busyThread充分运行
        SleepUtil.second(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());
        //防止sleepThread和busyThread立即退出
        SleepUtil.second(2);
    }
    //一直在睡眠状态
    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtil.second(10);
            }
        }
    }
    //y一直在运行
    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true){
            }
        }
    }
}
