package com.gai.demo01;

/**
 * 采用同步方法，解决共享数据的问题
 * 好处：代码简洁
 * 将共享数据抽取到一个方法中
 * 在方法的申明上加上同步关键字
 * 方法同步中的锁是本类对象引用this
 * 静态方法锁住的是本类.class
 */
public class Tickets implements Runnable {

    //定义出售的票源
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            payTicket();

        }
    }

    public synchronized void payTicket() {
        //线程共享数据，为了安全，加入同步代码块。

        //对票数判断，如果大于0，可以出售，变量进行--操作
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出售第：" + ticket-- + "票");
        }
    }
}
