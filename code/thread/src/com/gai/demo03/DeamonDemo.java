package com.gai.demo03;
//后台线程

class DaemomThread extends Thread{
    public void run(){
        for (int i = 0; i <133; i++) {
            System.out.println(super.getName()+ "  " + i);
            //System.out.println(Thread.currentThread().isDaemon());

        }
    }
}
public class DeamonDemo {
    public static void main(String[] args) {
//判断当前线程是否主线程
        //System.out.println(Thread.currentThread().isDaemon());
        for (int i = 0; i < 33; i++) {
            System.out.println("main " +i);
            if(i == 5){
                DaemomThread dt =new DaemomThread();
                dt.setDaemon(true);//设置为后台线程,并且在调用start之前设置
                dt.start();
            }
            //当前台线程结束之后.后台线程也会相应的自动结束
        }
    }
}
