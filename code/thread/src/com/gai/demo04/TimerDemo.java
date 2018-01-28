package com.gai.demo04;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
class PrintDemo extends TimerTask {
    public void run() {
        //在五秒之后打印开始打印此内容
        System.out.println("越努力，越幸运");
    }
}
public class TimerDemo{
    public static void main(String[] args) {
        System.out.println("开始");
        for (int i = 0; i < 10; i++){
            //此处线程必须设置为前台线程，否则看不到效果。
            new Timer().schedule(new PrintDemo(),5000);
        }
        System.out.println("结束");

    }
}
