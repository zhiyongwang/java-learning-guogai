## 第五章Java中的锁
主要内容包括两方面：**使用**，通过实例演示这些组件之间的使用方法以及详细介绍与锁有关的API；**实现**,通过分析源码来刨析实现细节，因为理解实现的细节方能更加得心应手且正确的使用这些组件

####5.1 LOCK接口
琐是用来控制多个线程访问共享资源的方式，一般来说，一个锁能够防止多个线程同时访问共享资源(但是有些锁可以允许多个线程并发的访问共享资源，比如读写锁)。在Lock接口出现之前，
Java程序员是通过synchronized关键字实现锁的功能，而Java SE 5之后，并发包中新增了Lock接口(以及相关实现类)用来实现锁的功能，它提供了与synchronized关键字类似的同步功能，只是在使用的时候需要显示的获取锁和释放锁，
虽然它缺少了(通过synchronized块或者方法所提供的)隐式获取锁的便捷性，但是却拥有了锁获取与释放的可操作性、可中断性的和获取锁以及超时获取锁等多种synchronized关键字所不具备的同步特性。<br>
使用synchronized关键字将会隐式地获取锁，但是它将锁的获取，和释放固化了，也就是先获取在释放。当然，这种方式简化了同步的管理，可是扩展性没有显示的锁获取和释放来的好
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
} finally {
    lock.unlock();   //在finally块中释放锁，目的是保证获取到锁之后，最终能够被释放
    }
    
```
**不要将获取锁的过程写在try块中，因为如果在获取锁时发生了异常，异常抛出的同时，也会导致锁无故的释放。**

Lock接口提供的synchronized关键字所不具备的主要特性如下所示：

|特性|描述|
|:----:|:-----:|
|尝试非阻塞的获取锁|当前线程会尝试获取锁，如果这一时刻锁没有被其他线程获取到，则成功获取并持有锁|
|能被中断地获取锁|与synchronized不同，获取到所得线程能够响应中断，当获取到锁的线程被中断时，中断异常会抛出，同时锁会被释放|
|超时获取锁| 在指定的截至时间之前获取锁，如果截至时间到了仍没获取锁，则返回|

Lock是一个接口，它定义了锁获取和释放的基本操作，Lock的API如下

|方法名称|描述|
|:----:|:-----:|
|void lock| 获取锁，调用该方法当前线程会获取，当获取锁之后从该方法返回|
| void lockInteruptibly|可中断地获取锁，和lock方法不同之处在于该方法会响应中断，即在锁的获取中可以中断当前线程|
|boolean trylock|尝试非阻塞的获取锁，调用该方法后会被立刻返回，如果能够获取则返回true，则返回false|
|boolean tyrlock(long time，TimeUnit time)| 超时的获取锁，分三种情况 1，当前线程在超时时间内获取了锁，2.当前线程在超市内被中断，3超时时间结束返回false|
|void unlock|释放锁|
|Condition new Condition()| 获取等待通知组件，该组件和当前的锁绑定，当前线程只有获得了锁，才能调用该组件的wait()方法，而调用后，当前线程释放锁|

AbStractQueuedSynchronized以及常用的Lock接口的实现ReentrantLock。Lock接口基本都是通过**聚合了一个同步器的子类**来完成线程访问控制的

#### 5.2队列同步器
队列同步器AbstractQueuedSynchronizer，是用来构建锁或者其他同步组件的基础框架，它使用了一个int成员变量表示同步状态，
通过内置的FIFO队列来完成资源获取线程的排队工作，并发包的作者（Doug Lea）期望它能够成为实现大部分同步需求的基础。<br>
同步器既可以支持独占式地获取同步状态，也可以支持共享式地获取同步状态，这样就可以方便实现不同类型的同步组件（ReentrantLock、ReentrantReadWriteLock和CountDownLatch等）                               

#####5.2.1　队列同步器的接口与示例
同步器的设计是基于模板方法模式的，也就是说，使用者需要继承同步器并重写指定的方法，随后将同步器组合在自定义同步组件的实现中，并调用同步器提供的模板方法，而这些模板方法将会调用使用者重写的方法。<br>
重写同步器指定的方法时，需要使用同步器提供的如下3个方法来访问或修改同步状态:

- getState() 获取当前的同步状态
- setState(int newState) 设置当前的同步状态
- compareAndSetState(int expect,int update)：使用CAS设置当前状态，该方法能够保证状态设置的原子性。

#### 5.3重入锁
重入锁ReentrantLock，顾名思义，就是支持重进入的锁，它表示该锁能够支持一个线程对资源的重复加锁。除此之外，该锁的还支持获取锁时的公平和非公平性选择。<br>
ReentrantLock虽然没能像synchronized关键字一样支持隐式的重进入，但是在调用lock()方法时，已经获取到锁的线程，能够再次调用lock()方法获取锁而不被阻塞。
####5.4　读写锁
之前提到锁（如Mutex和ReentrantLock）基本都是排他锁，这些锁在同一时刻只允许一个线程进行访问，而读写锁在同一时刻可以允许多个读线程访问，但是在写线程访问时，
所有的读线程和其他写线程均被阻塞。读写锁维护了一对锁，一个读锁和一个写锁，通过分离读锁和写锁，使得并发性相比一般的排他锁有了很大提升。<br>
 除了保证写操作对读操作的可见性以及并发性的提升之外，读写锁能够简化读写交互场
景的编程方式。假设在程序中定义一个共享的用作缓存数据结构，它大部分时间提供读服务（例如查询和搜索），而写操作占有的时间很少，但是写操作完成之后的更新需要对后续的读服务可见。
####5.6　Condition接口
任意一个Java对象，都拥有一组监视器方法（定义在java.lang.Object上），主要包括wait()、
wait(long timeout)、notify()以及notifyAll()方法，这些方法与synchronized同步关键字配合，可以实现等待/通知模式。Condition接口也提供了类似Object的监视器方法，与Lock配合可以实现等待/通知模式，但是这两者在使用方式以及功能特性上还是有差别的。
##### 5.6.1　Condition接口与示例

Condition定义了等待/通知两种类型的方法，当前线程调用这些方法时，需要提前获取到
Condition对象关联的锁。Condition对象是由Lock对象（调用Lock对象的newCondition()方法）创建出来的，换句话说，Condition是依赖Lock对象的。
```java
Lock lock = new ReentrantLock();
Condition condition = lock.newCondition();
public void conditionWait() throws InterruptedException {
    lock.lock();
    try {
        condition.await();
    } finally {
        lock.unlock();
    }
}
public void conditionSignal() throws InterruptedException {
    lock.lock();
    try {
         condition.signal();
    } finally {
         lock.unlock();
    }
}

```
一般都会将Condition对象作为成员变量。当调用await()方法后，当前线程会释放锁并在此等待，而其他线程调用Condition对象的signal()方法，通知当前线程后，当前线程才从await()方法返回，并且在返回前已经获取了锁。

##第6章　Java并发容器和框架

####6.1ConcurrentHashMap的实现原理与使用
ConcurrentHashMap是线程安全且高效的HashMap。
##### 6.1.1　为什么要使用ConcurrentHashMap
在并发编程中使用HashMap可能导致程序死循环。而使用线程安全的HashTable效率又非常低下，基于以上两个原因，便有了ConcurrentHashMap的登场机会

(1)线程不安全的HashMap
在多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。例如，执行以下代码会引起死循环。<br>
HashMap在并发执行put操作时会引起死循环，是因为多线程会导致HashMap的Entry链表形成环形数据结构，一旦形成环形数据结构，Entry的next节点永远不为空，就会产生死循环获取Entry。
(2)效率低下的Hashtable
HashTable容器使用synchronized来保证线程安全，但在线程竞争激烈的情况下HashTable的效率非常低下。因为当一个线程访问HashTable的同步方法，其他线程也访问HashTable的同
步方法时，会进入阻塞或轮询状态。如线程1使用put进行元素添加，线程2不但不能使用put方法添加元素，也不能使用get方法来获取元素，所以竞争越激烈效率越低。
(3)ConcurrentHashMap的锁分段技术可有效提升并发访问率
 HashTable容器在竞争激烈的并发环境下表现出效率低下的原因是所有访问HashTable的
线程都必须竞争同一把锁，假如容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争，从而可以有效提高并
发访问效率，这就是ConcurrentHashMap所使用的锁分段技术。首先将数据分成一段一段地存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
#####6.2　ConcurrentLinkedQueue
在并发编程中，有时候需要使用线程安全的队列。如果要实现一个线程安全的队列有两种方式：一种是使用阻塞算法，另一种是使用非阻塞算法。使用阻塞算法的队列可以用一个锁（入队和出队用同一把锁）或两个锁（入队和出队用不同的锁）等方式来实现。非阻塞的实现方式则可以使用循环CAS的方式来实现。<br>
ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元素时，它会返回队列头部的元素。它采用了“wait-free”算法（即CAS算法）来实现，该算法在
Michael&Scott算法上进行了一些修改。
####6.3　Java中的阻塞队列

##### 6.3.1什么是阻塞队列
阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这两个附加的操作支持阻塞的插入和移除方法。
- 1.支持阻塞的插入方法：意思是当队列满时，队列会阻塞插入元素的线程，直到队列不满。
- 2.支持阻塞的移除方法：意思是在队列为空时，获取元素的线程会等待队列变为非空 
####6.4　Fork/Join框架
#####6.4.1　什么是Fork/Join框架
Fork/Join框架是Java 7提供的一个用于并行执行任务的框架，是一个把大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架











