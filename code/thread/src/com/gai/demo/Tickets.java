package com.gai.demo;

/**
 * 通过线程休眠，出现了安全问题
 * 为了解决问题，java程序为我们提供了同步技术
 * 公式：
 *      synchronized(任意对象) {
 *          线程要操作共享数据
 *
 *      }
 */
public class Tickets implements Runnable {

    //定义出售的票源
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {

            //线程共享数据，为了安全，加入同步代码块。
            synchronized(this) {
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

    }
}
