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
- 注意：Daemon属性需要在启动线程之前设置，不能在启动线程之后设置。

Daemon线程被用作完成支持性工作，但是在JVM虚拟机退出时，Daemon线程中的finally块不一定会执行
```java
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

//没有输出结果

```
main线程(非Daemon线程)在启动了线程DaemonRunner之后随着main()方法执行完毕而终止，而此时Java虚拟机中已经没有非Daemon线程来，
虚拟机需要退出，Java虚拟机中所有的Daemon线程都需要立即停止，因此DaemonRunner立即终止，但是DaemonRunner中的finally块并没有执行

注意：在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑！

#### 4.2启动和终止线程
通过调用线程的start()方法进行启动，随着run()方法的执行完毕，线程也随之终止。

##### 4.2.1 构建线程

在运行线程之前需要构建一个线程对象，线程对象在构建的时候需要提供线程所需要有的属性，如线程组、线程优先级、是否是Daemon线程等信息

代码摘自java.lang.Thread中对线程进行初始化部分。

```
 private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc,
                      boolean inheritThreadLocals) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name;
        //当前线程就是该线程的父线程
        Thread parent = currentThread();
        g.addUnstarted();
        this.group = g;
        //将daemon、priority属性设置为父线程的对应属性
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        if (security == null || isCCLOverridden(parent.getClass()))
            this.contextClassLoader = parent.getContextClassLoader();
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        //将父线程的inheritableThreadLocals复制过来
        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals =
                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        /* Stash the specified stack size in case the VM cares */
        this.stackSize = stackSize;

        /* Set thread ID */
        tid = nextThreadID();
    }
```

一个新构造的线程对象是由其parent线程来进行空间分配的，而child线程继承了parent是否为Daemon、优先级、加载资源的contextClassLoader、以及可继承的ThreadLocal，同时还分配一个唯一个标识这个child线程
至此，一个能运行的线程对象就初始化好了，在堆内存中等待着运行。
 
##### 4.2.2 启动线程
线程对象在初始化完成之后，调用start()方法就可以启动这个线程了，线程start()方法包含的意义：当前线程(parent线程)同步告知JVM虚拟机只要线程规划器空闲，应立即启动调用start
()方法的线程。
注意：启动一个新线程，最好给这个线程设置线程名，因为这样在使用jstack分析程序或进行问题排查时，就会给开发人员提供一些提示，自定义的线程最好能够起个名字
##### 4.2.3 理解中断
中断可以理解为线程的一个标识位属性，它表示一个运行中的线程是否被其他线程进行了中断操作，中断操作好比其他线程对该线程打理个招呼，其他线程通过调用该线程的interrupt()方法进行中断操作
线程通过检查自身是否被中断来进行响应。线程通过方法idInterrupted()来进行判断是否被中断，也可以调用静态方法Thread.interrupted()对当前线程的中断标识位进行复位。如果该线程处于终结状态，即使该线程被中断过，在调用该对象的isInterrupted()时依旧会返回false。
从Java API中可以看到许多声明抛出interruptedException的方法，Java虚拟机会先去该线程的中断标识位清除，然后抛出。InterruptedException,此时调用isInterrupted()方法将会返回false；

```java


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

```

```
输出如下：
SleepThread interrupted is false
java.lang.InterruptedException: sleep interrupted
busyThread interrupted is true

```

从结果可以看出，抛出InterruptedException的线程是SleepThread，其中断标识位被清除了，而一直忙碌运作的线程busyThread中断标识位没有被清除

 ##### 4.2.4 过期的suspend()、resume()、和stop()
 
 把CD播放音乐比作一个线程的运行，那么对音乐的暂停，恢复，停止操作对应在线程Thread的API就是suspent()、resume（）、stop()
 这些API是过期的，不建议使用，不建议使用的原因如下：
 
 以suspend()为例，在调用后，线程不会释放已占有的资源(比如锁),而是站有着资源进入睡眠状态，这样容易引发死锁问题，
 同样，stop()方法在终结一个线程不会保证线程的资源正常释放。**通常是没有给予线程完成资源释放的机会**。因此会导致程序可能工作在不确定状下。
 
 #####4.2.5
 中断状态是线程的一个标识位，而中断操作是一种简便的线程间交互方式，而这种交互最适合来取消或停止任务，除了中断以外，还可以利用一个boolean变量来控制是否要停止任务并终止该线程
 
 ```java
package com.da.study01;

import java.util.concurrent.TimeUnit;

/**
 * Created by guo on 2018/1/30.
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();
        //睡眠一秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two  = new Runner();
        countThread = new Thread(two,"CountThrad");
        countThread.start();

        //睡眠1秒main线程对Runner two 进行取消，使得CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {

            on = false;
        }

    }
}

```
```
输出内容如下：
Count i = 438939423
Count i = 472801839

```
示例在执行过程中，main线程通过中断操作和cancel()方法均可以使得CountThread得以终止，
这种通过标识位和中断操作的方式能够使线程在终止时有机会去清理资源，而不是武断地将线程停止。因此这种终止线程的做法显得更加安全和优雅

#### 4.3线程间的通信
线程开始运行，拥有自己的栈空间，就如同一个脚本一样，按照既定的代码一步一步的执行，直到终止。但是每个运行中的线程，如果仅仅是孤独的运行，那么没有一点价值，如果多个线程可以相互配合完成工作，这将带来巨大的价值

#####4.3.1volatile和synchronized关键字
Java支持多个线程同时访问一个对象或者对象的成员变量，由于每个线程可以拥有这个变量的拷贝(虽然对象以及成员变量分配的内存是在共享内存中，但是每个线程都可以拥有一份拷贝，这样做的目的是加速程序的执行，这时候现代处理器的一个显著特性)，
所以程序在执行过程中，一个线程看到的变量并不一定是最新的 <br>
- 关键字volatile可以用来修饰字段(成员变量)，就是告知程序任何对访问该变量的访问均需要从主内存中获取，而对他的改变必须同步刷回共享内存，它能保证所有线程对变量访问的可见性。过多的使用volatile是不必要的，因为他会降低程序执行的效率
- 关键字synchronized可以修饰方法，或者以同步代码块的形式来进行使用，它主要确保多个线程在同一时刻，只能有一个线程处于方法或者同步代码块中，它保证了线程对该变量访问的可见性和排它性。


```java
package com.da.study01;

/**
 * Created by guo on 2018/1/30.
 */
public class Synchronized {
    public static void main(String[] args) {
        //对synchronized Class对象进行加锁
        synchronized (Synchronized.class) {
        }
        //静态同步方法，对Synchronized Class对象进行加锁
        m();
    }
    public static synchronized void m() {

    }
}

```
```
在同级目录使用javap -v Synchronized.class 
    
    public static void main(java.lang.String[]);
      descriptor: ([Ljava/lang/String;)V
      //方法修饰符，表示 public static
      flags: ACC_PUBLIC, ACC_STATIC
      Code:
        stack=2, locals=3, args_size=1
           0: ldc           #2                  // class com/da/study01/Synchronized
           2: dup
           3: astore_1
           4: monitorenter                      //监视器进入，获取锁
           5: aload_1
           6: monitorexit                       //监视器退出，释放锁
           7: goto          15
          8: return
       
    public static synchronized void m();
      descriptor: ()V
      //方法洗师傅，表示public static synchronized void m()
      flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
      Code:
        stack=0, locals=0, args_size=0
           0: return
           
```
对于同步代码块的实现使用了monitorenter和monitorexit指令，而同步方法则是依靠方法修饰符上的ACC_SYNCHRONIZED来完成的
无论采用哪一种，其本质是对一个对象监视器(monitor)进行获取，而这个获取是排他的，也就是同一时刻只能有一个线程获取到由synchronized所保护的监视器<br>
任意一个对象都拥有自己的监视器，当这个对象由同步代码块或者这个对象的同步方法调用时，执行的方法必须先获取到该对象的监视器才能进入到同步代码块。
而没有获取到监视器的线程将会被阻塞在同步块和同步方法的入口处，进入BLOCKED状态

##### 4.3.2 等待/通知机制
一个线程修改了一个对象的值，而另一个线程感知到了变化，然后进行相应的操作。整个过程开始于一个线程，而最终执行又是另一个线程。前者是生产者，后者是消费者，<br>
等待/通知相关的方法是任意对象都具备的，因为这些方法被定义在所有对象的超类java.lang.Object上，有以下方法：

- notify()通知一个在对象上等待的线程，使其从wait()方法返回，而返回的前提是该线程获得了对象的锁
- notifyAll() 通知所有等待在该对象上的线程
- wait() 调用该方法进入WAITING状态，只有等待另外的线程通知或被中断才会返回。**调用wait()方法，会释放掉锁**
- wait(long) 超时等待一段时间，这里的参数是毫秒，如果没有通知就超时返回
- wait(long int) 对于超时时间更加细粒度的控制，可以达到纳秒级

等待/通知机制，是指一个线程A调用了对象O的wait()方法，进入等待状态，而另外一个线程B调用了O的notify()或notifyAll()方法，线程A收到通知后从对象O的wait()方法返回
进而执行后续的操作。上述两个线程通过对象Olai完成交互，而对象上的wait()和notify()/notifyAll()的关系就如同开关信号一样，用来完成等待方和通知方之间的关系

使用wait()、notify()、notifyAll()时需要注意的细节：

- 1.使用wait()、notify()、notifyAll()时需要下对调用对象加锁
- 2.调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放入到对象的等待队列中
- 3.notify()或nitifyAll()方法调用后，等待线程依旧不会从wait()放回，需要调用notify()、notifyAll()的线程释放掉锁之后，等待线程才有机会从wait()放回
- 4.notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
- 5.从wait()方法返回的前提是获得了调用对象的锁)

从上述细节可以看出，**等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时，能够感知到通知线程对变量所作的修改。**

##### 4.3.3等待/通知的经典范式
该范式分为两部分，分别为等待方(消费者)、通知方(生产者) <br>
等待方遵循如下规则：

- 1）获取对象的锁
- 2）如果条件不满足，那么调用对象的wait()方法，被通知后仍然要检查条件
- 3）条件满足则执行相应的逻辑

对应的伪代码如下：

```java
synchronized(对象){
    while(条件不满足) {
        对象.wait();
    }
    对应的逻辑代码
}

```
通知方遵循如下规则：

- 1）获取对象的锁
- 2）改变条件
- 3）通知所有等待在该对象上的线程

对应的伪代码如下：

```java

synchronized(对象){
   改变条件
   对象.notifyAll();
}
```

#####4.3.4管道的输入/输出流
它主要用于线程之间的数据传输，而传输的媒介是内存
管道输入输出流主要包含以下四中具体的实现：PipedOutputStream，PipedInputStream,PipedReader,PipedWriter,前面两种面向字节，而后两种面向字符

 ```java
 package com.da.study01;
 
 import java.io.IOException;
 import java.io.PipedReader;
 import java.io.PipedWriter;
 
 /**
  * Created by guo on 2018/1/31.
  * 作用：用来接收main线程的输入，任何main线程的输入均可以通过PipedWriter,而PrintThread在另一端通过PipedReader读出并打印
  */
 public class Piped {
     public static void main(String[] args) throws IOException {
         PipedWriter out = new PipedWriter();
         PipedReader in = new PipedReader();
         //将输入和输出流进行连接 ，否则在使用中会抛出IOException
         out.connect(in);
         Thread PrintThread = new Thread( new Print(in),"PrintThread");
         PrintThread.start();
         int receive = 0;
         try {
             while ((receive = System.in.read()) != -1) {
                 out.write(receive);
             }
         } finally {
             out.close();
         }
     }
     static class Print implements Runnable {
         private PipedReader in;
         public Print(PipedReader in) {
             this.in = in ;
         }
         @Override
         public void run() {
             int receive = 0;
                 try {
                     while ((receive = in.read()) != -1) {
                         System.out.print((char) receive);
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
         }
     }
 }

 //原样输出
```

对于Piped类型的流，必须先要进行绑定，也就是调用connect()方法，如果没有绑定，则会抛出IOException异常

#####4.3.6ThreadLocal的使用
ThreadLocal,即线程变量，是一个以ThreadLocal对象为键，任意对象位置的存储结构
，这个结构被附带在线程上，也就是说一个线程可以更具一个ThreadLocal对象查询到绑定这个线程上的一个值<br>
可以通过set(T)方法来设置一个值，在当前线程下在通过get()方法获取到原来的值。


#### 线程应用实例

##### 4.4.1等待超时模式
调用一个方法时等待一段时间(时间段)，如果该方法在给定的时间内得到结果，那么将结果立刻返回，反之，超时返回默认结构
```java

//假设超时段是T,那么可以推断出在当前时间now+T之后就会失效
定义如下变量
 等待持续时间：REMAINING =T
 超时时间：FUTURE = NOW + T
 
 这时只需要wait(REMAINING)即可，在wait(REMAINING)返回之后会将执行：
REMAINING=FUTURE–now。如果REMAINING小于等于0，表示已经超时，直接退出，
否则将继续执行wait(REMAINING)。

上述描述等待超时模式的伪代码如下：
 // 对当前对象加锁
 public synchronized Object get(long mills) throws InterruptedException {
         long future = System.currentTimeMillis() + mills;
         long remaining = mills;
         // 当超时大于0并且result返回值不满足要求
         while ((result == null) && remaining > 0) {
         wait(remaining);
          remaining = future - System.currentTimeMillis();
         }
         return result;
 }

```
可以看出，等待超时模式就似乎和在等待/通知范式基础上增加了超时控制，这使得该模式变比原有的范式更具灵活性，
因为即使方法执行时间过长，也不会“永久”阻塞调用者，而是会按照调用者的要求按时返回。






