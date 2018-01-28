package com.guo.demo03;

/**
 * Created by guo on 2018/1/28.
 * 实现线程的另一种方式，接口的实现
 * 实现接口Runnable，重写run()方法    
 */
public class SubRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("run..." + i);
        }

    }
}
