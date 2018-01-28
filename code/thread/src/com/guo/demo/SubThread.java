package com.guo.demo;

/**
 * Created by guo on 2018/1/28.
 * 定义子类线程，重写父类方法
 */
public class SubThread extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println( "run ------越努力，越幸运");
        }
    }
}
