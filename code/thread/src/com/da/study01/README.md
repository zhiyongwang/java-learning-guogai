# java并发编程学习笔记

## 第四章Java并发编程基础
Java自从诞生开始就明智的选择了内置对多线程的支持，这使得Java语言相比同一时期的其他语言更具有明显的优势。
线程作为操作系统调度的最小单元，多个线程可以同时执行，这将显著提升性能。
在多个处理器中表现更加优异，但是，过多的创建线程和对线程的管理不当也容易造成问题

####4.1线程简介

##### 什么是线程
现在操作系统在运行一个程序时，会为其创建一个进程，启动一个Java程序，操作系统就会创建一个Java进程，也叫轻量级进程。
在一个进程中可以创建多个线程，这些线程有个自的计数器，堆栈和局部变量表等属性。
并且可以访问共享的内存变量。处理器在高速的切换，让使用者感觉到这些线程在同时执行 <p>
一个Java线程从main()方法开始执行，然后按照既定的代码逻辑执行，看似没有其他线程参与，但实际上Java程序天生就是多线程程序。

```java
package com.da.study01;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by guo on 2018/1/29.
 * 使用JMX来查看一个Java程序包含那些线程
 */
public class MultiThrad {
    public static void main(String[] args) {
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchroniized信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息，仅打印线程id和线程名字
        for(ThreadInfo threandInfo : threadInfos) {
            System.out.println("[" + threandInfo.getThreadId() + "]" + threandInfo.getThreadName());
        }
    }
}

输出如下：
[6]Monitor Ctrl-Break   //监控Ctrl-Break 中断信号
[5]Attach Listener      //负责接收到外部的命令
[4]Signal Dispatcher    //分发处理发送非JVM信号的线程
[3]Finalizer            // 调用对象finalize方法的线程
[2]Reference Handler    //清楚Referencede的线程
[1]main                  //main线程，用户程序入口

```
可以看到，一个线程的运行不仅仅是main()方法的运行，而是main()线程和多个线程同时运行。

##### 4.1.2为什么要使用多线程
执行一个简单的“Hello world”，却启动了那么多无关的线程，是不是把问题复杂化了？使用多线程的原因主要有以下几点：

(1)更多的处理核心
随着处理器上的核心数量越来越多，以及超线程技术的广泛应用，处理器性能的提升方式，也从更好的主频向更多的核心发展。
线程是大多数操作系统调度的基本单元，一个线程作为一个进程来运行，程序运行过程中能够创建多个线程，而一个线程在一个时刻只能使用一颗处理器核心，
那么在多个处理器核心加入也无法显著提高该程序的执行效率。，如果使用多线程技术，将计算逻辑分配到多个处理器核心上，就会显著减少程序的处理时间，并且随着更多的处理核心的加入而变得更有效率

(2) 更快的响应时间
可以使用多线程技术，即将数据一致性不强的操作派发给其他线程执行处理(也可以是消息队列)，如生成订单。发送邮件。这样的好处是响应用户请求的线程能够尽快的处理完成，缩短了响应时间，提升了用户体验

(3)更好的编程模型
Java为多线程编程提供了良好、考究并且一致的编程模型，使开发人员能够更加专注于问题的解决，即为所遇到的问题建立合适的模型，而不是绞尽脑汁地考录如何将其多线程化
一旦开发人员建立了模型，稍微修改总是能够方便地映射到Java提供的多线程编程模型上

#####4.1.3线程的优先级
现代操作系统基本采用分时的形式调度运行的程序，操作系统会分出一个个时间片，线程会分配到若干时间片，当线程的时间片用完了就会发生线程调度，并等待下次分配，线程分配的多少也就决定了线程使用处理器资源的多少，而线程优先级就是决定线程需要多或者少分配一些处理器资源的属性
 在Java程序中，通过一个整型成员变量priority来控制优先级，优先级范围1～10.在线程构建的时候可以通过setPriority(int)方法来修改优先级，默认是5，优先级高的线程分配时间片的数量要多于优先级低的线程。
 设置线程优先级时，针对频繁阻塞(休眠或IO操作)的线程需要设置较高的优先级，而偏重计算(需要较多CPU时间)的线程则需要较低的优先级。在不同的JVM以及操作系统上，线程规则会存在差异，有些操作系统甚至会忽略对线程优先级的设定。
 
 ```java
package com.da.study01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by guo on 2018/1/29.
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);  //睡眠10秒
        notEnd = false;
        for (Job job : jobs) {
            System.out.println("job Priority:" + job.priority + ", count:" + job.jobCount);
        }
    }

    static class Job implements Runnable {
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();  //给其他线程让出CPU时间片
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }

        }
    }
}

输出如下：
job Priority:1, count:5771305
job Priority:1, count:1406571
job Priority:1, count:12575189
job Priority:1, count:20431518
job Priority:1, count:6712885
job Priority:10, count:12182563
job Priority:10, count:11674916
job Priority:10, count:10038966
job Priority:10, count:12048419
job Priority:10, count:12182434

从输出可以看到线程优先级没有生效，优先级1和10的结果非常相似，没有明显的差距
这表示程序正确性不能依赖线程的优先级高低

```
线程优先级不能作为程序正确性的依赖，因为操作系统可以完全不理会Java线程对于优先级的设定。

##### 4.1.4 线程的状态
 Java线程在运行生命周期中可能处于6种不同的状态，在给定的一个时刻，线程只能处于一种状态，
 
 - NEW ：初始状态，线程被创建，但是还没有调用start，没有CPU时间片
 - RUNABLE： 运行状态，Java线程将操作系统中就绪和运行两种状态笼统的称为“运行中”
 - BLOCKED： 阻塞状态，表示线程阻塞于锁
 - WAITING：等待状态，表示线程进入等待状态，进入该线程表示当前线程需要等待其他线程做出一些特定动作(通知或中断)
 - TIMEWAITING：超时等待状态，该状态不同于WAITING，它是可以在指定时间内自行返回的
 - TERMINATED：终止状态，表示当前线程执行完毕
 
 ```java
package com.da.study01;

/**
 * Created by guo on 2018/1/29.
 * 线程的状态
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        //使用两个Blocked线程，一个获得锁，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }

    //该线程不断的进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtil.second(100);
            }
        }
    }

    //该线程在Waiting.Class实例上等待
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Blocked.class) {
                    SleepUtil.second(100);
                }
            }
        }
    }
}

```
``
打开终端输入jps查看运行进程
C:\Users\Administrator>jps
3696 Launcher
15652 Jps
16968
19676 ThreadState
C:\Users\Administrator>jstack 19676

//BlockedThread-2线程阻塞在获取Blocked.class示例的锁上，
"BlockedThread-2" #14 prio=5 os_prio=0 tid=0x000000001d494000 nid=0x20bc waiting for monitor entry [0x000000001e76e000]
   java.lang.Thread.State: BLOCKED (on object monitor)
   
//BockedThread-1线程获得了Block.class的锁
"BlockedThread-1" #13 prio=5 os_prio=0 tid=0x000000001d491000 nid=0x3020 waiting on condition [0x000000001e38f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
  
//WaitingThread线程在Waiting实例上等待
"WaitingThread" #12 prio=5 os_prio=0 tid=0x000000001d48c800 nid=0x4498 in Object.wait() [0x000000001dfdf000]
   java.lang.Thread.State: WAITING (on object monitor)
  
//TimeWaiting处于超时等待
"TimeWaitingThread" #11 prio=5 os_prio=0 tid=0x000000001d48b000 nid=0x3aec waiting on condition [0x000000001e5ee000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)

``
线程在自身的生命周期中，并不是固定处于某个状态，而是随着代码的执行在不同的状态之间进行切换，Java的线程状态如下：

![](https://i.imgur.com/qAf3J3F.png)  

线程创建之后，调用start()方法开始运行，当线程执行wait()方法之后，线程进入等待状态。进入等待状态的线程需要其他线程的通知才可能返回到运行状态
而超时等待状态相当于在等待状态的基础上增加了超时限制。当线程调用同步方法时，在没有获得锁的情况下，会进入阻塞状态，线程在执行Runable的run()方法只好将会进入终止状态。



图片和以下内容来自([这里](https://my.oschina.net/mingdongcheng/blog/139263))
``
1. 新建(new)：新创建了一个线程对象。
2. 可运行(runnable)：线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu 的使用权 。
3. 运行(running)：可运行状态(runnable)的线程获得了cpu 时间片（timeslice） ，执行程序代码。
4. 阻塞(block)：阻塞状态是指线程因为某种原因放弃了cpu 使用权，也即让出了cpu timeslice，暂时停止运行。直到线程进入可运行(runnable)状态，才有机会再次获得cpu timeslice 转到运行(running)状态。阻塞的情况分三种： 
(一). 等待阻塞：运行(running)的线程执行o.wait()方法，JVM会把该线程放入等待队列(waitting queue)中。
(二). 同步阻塞：运行(running)的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池(lock pool)中。
(三). 其他阻塞：运行(running)的线程执行Thread.sleep(long ms)或t.join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入可运行(runnable)状态。
5. 死亡(dead)：线程run()、main() 方法执行结束，或者因异常退出了run()方法，则该线程结束生命周期。死亡的线程不可再次复生。
``
阻塞状态是线程阻塞在进入synchronized关键字修饰的方法或同步代码块时的状态，但是阻塞在java.concurrent包中Lock接口的线程状态确实等待状态，因为java.concurent包中Lock接口对于阻塞的实现均使用了LockSupport类中相关的方法

#####4.1.5Daemon线程

Daemon线程是一种支持型线程，它主要作用被用作程序中后台调度以及支持性工作。这意味着，当一个JVM虚拟机中不存在Daemon线程的时候，JVM虚拟机将会退出，可以通过调用Thread.setDaemon(true)将线程设置为Daemon线程
注意：Daemon属性需要在启动线程之前设置，不能在启动线程之后设置。


