# java并发编程学习笔记

## 第一章 并发编程的挑战

#### 1.1上下文切换

```java

package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 多线程一定快吗？
 *
 * concurrentcy:2ms,b=-10001
 * serial:0ms,b=-10001,a=50005

 * concurrentcy:9ms,b=-100001
 * serial:4ms,b=-100001,a=500005

 * 只有超过百万才比并行快2ms
 * concurrentcy:6ms,b=-1000001
 * serial:8ms,b=-1000001,a=5000005

 * 千万次运算 q
 * concurrentcy:13ms,b=-10000001
 * serial:20ms,b=-10000001,a=50000005

 * 一亿
 * concurrentcy:67ms,b=-100000001
 * serial:112ms,b=-100000001,a=500000005

 * 当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢，
 * 因为线程有创建和上下文切换的开销
 */

public class ConcurrencyTest {
    private static final long count = 100000001;

    public static void main(String[] args) throws InterruptedException {
        conCurrentcy();
        serial();

    }

    /**
     * 并发
     */
    private static void conCurrentcy() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrentcy:" + time + "ms,b=" + b);
    }

    /**
     * 串行
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}

```
 单核处理器也支持多线程的执行代码，CPU通过给每个线程非陪CPU时间片来实现这个机制，CPU通过时间片分配算法来循环执行任务，
 当前任务执行一个时间片，会切换到下一个时间片，切换前会保存上一个任务的状态，任务从保存到再加载的过程就是一个上下文切换<br>
减少上下文切换的方法有：无锁并发编程、CAS算法、使用最少的线程和使用协程
- 无锁并发编程：多线程竞争时，会引起上下文切换，所以多线程处理数据时，将数据的ID按照Hash算法取模分段，不同的线程处理不同的数据。
- CAS算法：Java的Atomic包使用CAS算法来更新数据，而不需要加锁
- 使用最少线程：避免创建不需要的线程，比如任务少，但是创建了很多线程来处理，这样会造成过多的线程处理等待状态。
- 协程：在单线程中实现多任务的调度，并在单线程里维持多个任务间的切换。
#### 1.2死锁
死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。

```java
package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 以下代码会引起死锁，是线程t1和t2互相等待对方释放锁
 *
 *
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
                   deadLock();
    }

    public static   void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                       synchronized (B) {
                           synchronized (A) {
                               System.out.println("2");
                           }
                       }
            }
        });
        t1.start();t2.start();
    }
}
```
避免死锁的方法：
- 1.避免一个线程同时获取多个锁
- 2.避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源
- 3.尝试使用定时锁，使用lock.tryLock(timeout)来替换使用内部锁机制
- 4.对于数据锁，加锁和解锁必须在同一个数据库连接里，否则会出现解锁失败的情况。

#### 1.3资源限制的挑战

(1)什么是资源限制 <br>
资源限制是指在进行并发编程时，程序的执行速度受限于计算机硬件资源或软件资源，
如带宽的上传和下载速度，硬盘的读写速度和CPU处理的速度。软件资源有数据库连接和Socket连接。

(2)资源限制引发的问题 <br>
在并发编程中，将代码执行速度加快的原则是将代码中串行执行的部分变为并发执行，如果将某段代码并发执行，
因为受限于资源，仍然在串行执行，不仅不会并发执行，反而会更慢，得不偿失。因为增加了上下文切换和资源的调度。

(3) 如何解决资源限制问题 <br>
对于硬件资源，可以考虑使用集群并发执行程序，单机可以增加CPU和内存，或使用SSD。
对于软件资源限制，可以考虑使用资源池将资源复用，
(4) 在资源限制情况下进行并发编程
根据不同的资源限制调整程序的并发度，

## 第二章 Java并发机制的底层实现原理
java代码在编译后会变成字节码，字节码被类加载器加载到JVM中，JVM执行字节码。最终需要转化为汇编指令在CPU上执行，
Java中所使用的并发机制依赖于JVM的实现和CPU的指令。
#### 2.11volatile的应用
在多线程并发编程中synchronized和volatile都扮演着重要的角色，volatile是轻量级的synchronized，它在多处理器开发中
保证了共享变量的“可见性”。可见性是指当一个线程修改一个共享变量，另外一个线程能读到这个修改的变量。

##### 1.volatile的定义于实现原理
Java编程语言允许线程访问共享变量，为了确保共享变量能被准确和一致的更新，线程应该确保通过排他锁单的获取这个变量，
Java语言提过了volatile，在某些情况下比锁更加方便。如果一个字段被声明为volatile，Java线程内存模型确保所有的线程看到这个变量都是一致的<br>

有volatile变量修饰的共享变量在进行写操作的时候会多出Lock指令。Lock前缀的指令会在多核处理器下做两件事

- 将当前处理器缓存行的数据写回主内存
- 写操作会使其他CPU里的缓存了该地址失效

为了提高处理速度，处理器不直接和内存进行通信，而是先将主内存的数据读取到内部缓存(L1、L2)后在进行操作，但操作完不知道何时写回主内存
如果声明了volatile的变量进行写操作，JVM就会像处理器发送一条Lock前缀的指令，将这个变量在缓存行的数据写回主内存。在了保证各个处理器的
缓存是一致的，就会实现缓存一致性协议。每个处理器通过嗅探在总线上传播的数据来检查自己缓存的数据是否过期了，发现这个数据被修改了，就会将缓存行设置为无效状态，并重新从主内存读取数据到缓存行

#### 2.2synchronized的实现原理与应用
在多线程并发编程中synchronized一直是元老级角色，很多人都会称他为重量级锁，但是随着Java SE1.6进行了各种优化之后，有些情况下她就不那么重要了
Java中的每一个对象都可以作为锁，表现为以下三种形式：
- 对于普通同步方法，琐是当前实例对象
- 对于静态同步方法，锁是当前类的Class对象
- 对于同步方法块，锁是synchronized括号里配置的对象

当一个线程试图访问同步代码块时，它搜先必须得到锁，退出或抛出异常时必须释放锁。从JVM规范中可以看到Synchronized在JVM里的实现原理
JVM基于进入退出Monitor对象来实现方法同步和代码同步，但两者的实现细节不一样。代码同步块是使用monitorenter和monitorexit指令来实现的，而方法同步使用另外一种方式实现，<br>

monitorenter指令实在编译后插入到同步代码块的开始位置，而monitorexit是插入到方法结束处和异常处，JVM保证每个monitorenter必须有对应的monitorexit。任何一个对象都有一个monitor与之关联
当一个monitor被持有后，他就处于锁定状态。线程执行到monitorexit指令时，将会尝试获取对象所对应的monitor所有权，即尝试取得对象的锁。

###### 2.2.1Java对象头
synchronized用的锁是存在Java对象头里的，如果对象是数组类型，即JVM用三个字宽存储对象头，如果对象是非数组类型，则用两个字宽存储对象头，<br>
Java对象头里的Mark Word里默认存储对象的Hashcode，分代年龄和锁的标记位

###### 2.2.2锁的升级与对比
JavaSE1.6为了减少获得和释放锁带来的性能和消耗，引入了“偏向锁”和“轻量级锁”，四种状态级别从低到好依次为：无锁状态，偏向锁、轻量级锁，重量级锁。锁状态可以竞争升级，但不能降级。目的是为了提高获得锁和释放锁的性能

 (1)轻量级锁加锁
 线程在执行同步代码块之前，JVM会现在当前线程的栈帧中创建用于存储锁记录的空间，并将对象头中Mark Word复制到锁记录中，官方成为 Displaced Mark Word。然后线程使用CAS将对象头中的Mark Word替换为指向所记录的指针，如果成功，当前线程获得锁，如果失败，表示其他线程竞争锁，当前线程尝试使用自旋来获得锁。

 (2)轻量级锁解锁
 轻量级解锁时，会使用原子的CAS操作将Displace Mark Word替换回对象头中，如果成功，则表示没有竞争发生。如果失败，表示当前线程存在锁竞争，锁就会膨胀为重量级锁。
 因为自旋会消耗CPU，为了避免无用的自旋，一旦锁升级为重量级锁，就不能恢复到轻量级锁状态

 ######2.2.3 锁的优缺点对比

|锁    |    优点 |缺点| 应用场景|
| :---: |:---:|:---:|:---:|
|偏向锁 | 加锁和解锁不需要额外的消耗，和执行非同步代码方法相比只存在纳米级的差距  |  如果线程存在锁竞争，会带来额外的锁撤销消耗| 适用于只有一个线程访问同步块的场景  |
|轻量级锁|竞争的线程不会阻塞，提高了程序的响应速度|如果始终得不到锁竞争的线程，使用自旋会消耗CPU|追求响应时间，同步块执行速度非常快|
|重量级锁|线程竞争不使用自旋，不会消耗CPU|线程阻塞，响应时间缓慢|追求吞吐量，同步块执行速度较长|

####2.3原子操作的实现原理
原子(Atomic)本意为“不能被进一步分割的最小粒子”，而原子操作(Atomic Operacion) 意为“不可被中断的一个或一系列操作”。在多处理器上实现原子操作就变得更加复杂。

#####1.术语定义
|术语名称|英文|解释|
|:----:|:----:|:----:|
|缓存行|Cache Line|缓存的最小操作单元|
|比较并交换|Compare and Swap|CAS操作需要两个数值，一个旧值(期望操作前的值)和一个新值，在操作前需要先看旧值有没发生变化，如果没有变化，才替换成新值，发生了变化则不交换|
|CPU流水线|CPU pipeline| CPU流水线的工作方式就像工厂里的装配流水线，|

##### 2.处理器如何实现原子操作
32位IA-32处理器使用基于对缓存加锁或总线加锁的方式来实现多处理器之间的原子操作。首先处理器会自动保证基本的内存操作的原子性，处理器保证从系统主内存中读取或写入一个字节是原子性的。当处理器读取一个字节时，其他处理器不能访问这个字节的内存地址。

(1)使用总线锁保证原子性 <br>
第一个机制是通过总线锁来保证原子性的，如果多个处理器同时对共享变量进行读改写操作(i++就是经典的读改写操作)，那么共享变量就会被多个处理器同时进行操作，这样读写就不是原子的，操作完之后共享变量的值会和期待的值不一样。
多个处理器同时从各自己的缓存中读取变量i，分别进行加i操作，然后写回主内存。
所谓总线锁就是使用处理器提供的一个Lock#信号，当一个处理器在总线上输出此信号时，其他处理器的请求将被阻塞住那么该处理器可以独占共享变量

(2)使用缓存锁保证原子性<br>
在同一时刻，我们只需确保对某个地址的操作是原子性即可，但总线锁定把CPU和内存之间的通信锁住了，这使得锁定期间，其他处理器不恩那个操作其他内存地址的数据
所以总线锁开销比较大，目前处理器在某些场合使用缓存锁代替总线锁定来进行优化。频繁使用的内存会缓存在处理器的L1、L2高速缓存内，那么原子操作就可以直接在处理器内部的缓存内进行，并不需要申明总线锁。

有两种情况下处理器不会使用缓存锁定，

- 当操作的数据不能被缓存到处理器内部， 或操作的数跨多个缓存行(Cache Line) 时，则处理器会调用总线锁定
- 有些处理器不支持缓存锁定，对于Intel486和Pentium处理器，就算锁定的内存区域在处理器的缓存行中也会调用总线锁定。

##### 3.Java如何实现原子操作
在Java中可以通过锁和循环CAS的方式来实现原子操作

(1)使用循环CAS来实现原子操作
JVM中的CAS操作正是利用了处理器提供的CMPXCHG指令来是实现的自旋CAS实现的基本思路就是循环进行CAS操作直到成功为止。
```java
package com.da.study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by guo on 2018/1/29.
 * 实现一个基于CAS线程安全的计数器方法safeCoutn和一个非线程安全的计数器
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        //等所有的线程执行完成
        for(Thread t :ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() -start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (; ; ) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    public void count() {
        i++;
    }
}

```
从JDK1.5开始，JDK的并发包中提供了一些类来支持原子操作，如AtomicBoolean(用原子方式更新bloolean)、AtomicInteger(用原子操作来更新int值)，这些类还提供了有用的工具方法，比如原子的方式将当前值自增1和自减1操作。

(2)CAS实现原子操作的三大问题

- ABA 因为CAS需要在操作的时候检查值有没有发生变化，如果没有则更新，但如果有一个值原来是A，变成了B,又变成立A，CAS在检查的时候没有变化，实际上变化了，ABA问题解决的思路就是使用版本号，在变量前追加版本号，每次变量更新的时候把版本加1，那么A，B，A就变成了1A,2B,3A。

- 循环时间开销大，自旋CAS如果长时间不成功，会给CPU带来非常大的开销。如果JVM能支持处理器提供的pause指令，那么效率会有一定的提升。pause指令有两个作用：它可以延迟流水线执行指令，使CPU不会消耗过多的执行资源，避免在退出循环的时候因内存顺序冲突而引起CPU流水线被情况，从而提高CPU的执行效率

- 只能保证一个共享变量的原子操作，当对一个共享变量进行操作的时，我们使用循环CAS的方式来保证原子操作，但是对多个共享变量操作时，循环CAS无法保证原子操作，这个时候就可以用锁，还有一个办法就是把多个共享变量合并成一个共享变量来操作。

(3) 使用锁机制实现原子操作
锁机制保证了只有获得锁的线程才能够操作锁定的内存区域。JVM内部实现了很多锁机制，有偏向锁、轻量级锁，重量级锁。除了偏向锁，JVM实现锁的方式都用了循环CAS，**即当一个线程进入同步块的时候是使用CAS方式来获得锁，当它退出同步代码块的时候使用循环CAS释放锁。**
# java并发编程学习笔记

## 第三章 Java内存模型
Java线程之间的通信对程序员完全透明，内存可见性问题很容易困扰Java程序员，本章大致分为4部分：

- [Java内存的模型的基础](####3.1.1Java内存模型的基础)
- [Java内存的模型中顺序一致性]()
- [同步原语]()
- [Java内存模型的设计]()

#### 3.1.1Java内存模型的基础
在并发编程中，需要处理两个关键问题：线程之间如何通信及线程之间如何同步。线程之间通信机制有两种共享内存和消息传递

- 在共享内存的并发模型中，线程之间共享程序的公开状态，通过写-读内存中公共状态进行隐式通信
- 在消息传递的并发模型中，线程之间没有公共状态，线程之间必须通过发送消息来显示进行通信

同步是指程序中用于控制不同线程操作发生相对顺序的机制。
- 在共享内存并发模型中，同步是显示进行的。程序员必须显示指定某个方法或某段代码需要在线程之间互斥执行，
- 在消息传递的并发模型中，由于消息的发送必须在消息的接收之前，因此同步是隐式的。

Java并发采用的是共享内存的模型，Java线程之间的通信总是隐式进行的，整个通信对于程序员完全透明，

#####3.1.2Java内存模型的抽象结构
在Java中所有实例域、静态域、和数组元素都存储在堆内存中，堆内存在线程之间共享。局部变量、方法定义参数、异常处理参数不会在线程之间共享，他们不会有内存可见性，也不受内存模型的影响

Java线程之间的通信由Java内存模型控制(JMM),JMM决定一个线程对共享变量的写入何时对立给你一个线程可见，
JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存，每个线程都有一个私有的本地内存，本地内存中存储了该线程以读/写共享变量的副本。<br>

Java内存模型抽象图

![](https://i.imgur.com/OZQCnmZ.png)

如果线程A和线程B之间要通信的话，必须要经历下面两个步骤：

- 线程A把本地内存A中要更新的共享变量刷新到主内存中
- 线程B到主内存中去读取线程A之前已更新过的共享变量

这个两个步骤实际上是线程A向线程B发送消息，而且这个通信过程必须经过主内存，JMM通过控制主内存与每个线程的本地内存之间的交互来为Java程序员提供内存可见性保证

##### 3.1.3从源代码到指令序列的重排序
在执行程序时，为了提高性能，编译器和处理器常常会对指令做重排序

- 编译器优化的重排序。编译器在不改变单线程程序语意的前提下，可以重新安排语句的执行顺序
- 指令级并行的重排序。现在处理器采用了指令级并行技术(ILP)来将多条指令重叠执行。
- 内存系统的重排序。由于处理器使用缓存和读写缓冲区，这使得加载和存储操作看上去可能是在乱序执行。

JMM属于语言级别的内存模型，它确保在不同的编译器和不同的处理器平台之上，通过禁止特定类型的编译器重新排序和处理器重排序为程序员提供一致的内存可见性。

##### 3.2双重检查锁定与延迟初始化
在Java多线程程序中，有时候需要采用延迟初始化来降低初始化类和创建对象的开销。
```java
package com.da.study;
/**
 * Created by guo on 2018/1/29.
 * 基于volatile的双重检查
 */
public class SafeDoubleCheckedLocking {
    private volatile static Instacen instance;
    public static Instacen getInstance() {
        if(instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instacen();
                }
            }
        }
        return instance;
    }
}
class Instacen{
}

```

##### 3.3基于类初始化的解决方案
JVM在类加载初始化阶段，会执行类的初始化，JVM会去获取一个锁，这个锁可以同步访问多个线程对同一个类的初始化
```java
package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 基于类的初始化解决方案
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }
    public static Instacen getInstance() {
        return InstanceHolder.instance;
    }
}
class Instance extends Instacen {

}

```
基于类初始化看似实现代码更简洁，但基于volatile的双重检查锁定的方案还有另外一个额外的优势，除了可以对静态字段实现延迟初始化，还可以对实例字段实现延迟初始化。
字段延迟初始化降低了初始化类或创建实例的开销，但增加了访问被延迟初始化的字段的开销，