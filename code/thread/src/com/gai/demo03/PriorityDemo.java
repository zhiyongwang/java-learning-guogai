package com.gai.demo03;
/**
 * 线程优先级
 * MAX_PRIORITY=10,最高优先级
 * MIN_PRIORITY=1,最低优先级
 * NORM_PRIORITY=5,默认优先级
 * 优先级的高低只和线程获得执行机会的次数多少有关,
 * 并非线程优先级越高的就一定先执行，哪个线程的先运行取决于CPU的调度。
 */
class PriorityThread extends Thread{
    public PriorityThread(String name){
        super(name);//调用父类的构造器
    }
    public void run(){
        for (int i = 0; i < 111; i++) {
            System.out.println(super.getName() +  "-" + i);
        }
    }
}
public class PriorityDemo {
    public static void main(String[] args) {
        //设置当前线程的优先级
        //Thread.currentThread().setPriority(8);

        //获取当前的线程的优先级级别
        //System.out.println(Thread.currentThread().getPriority());
        PriorityThread max = new PriorityThread("高优先级");
        max.setPriority(Thread.MAX_PRIORITY);
        PriorityThread min = new PriorityThread("低优先级");
        min.setPriority(Thread.MIN_PRIORITY);
        min.start();
        max.start();
    }
}
