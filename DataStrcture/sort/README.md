## 常见算法分析及实现

1、数据结构

编程的本质就是对数据(信息以数据的形式而存在)的处理，实际编程中不得不处理大量数据，因此实际动手编程之前必须先分析处理这些数据，处理数据之间存在的关系。

现实的数据元素之间有个错综复杂的逻辑关系，需要采用合适的物理结构来存储这些数据，并以此为基础对这些数据进行相应的操作。同时还要分析这些数据结构在时间和空间上的开销。**这种专门研究应用程序中的数据之间的逻辑关系，存储方式及其操作的学问就是数据结构。**

数据元素之间存在的关联关系被称为数据的**逻辑结构**,归纳起来，大致有如下四种基本的逻辑结构：

- 集合：数据元素之间只有"同属于一个集合"的关系
- 线性关系：数据元素之间存在一个对一个的关系
- 树形结构：数据元素之间存在一个对多个的关系
- 图状结构或网状结构：数据元素之间存在多个对多个的关系。

脑补图：

![](https://i.imgur.com/m3Ulddj.jpg)

图片>代码>文字,个人理解，能用图片说明问题的就不要用代码，同理，尽量用代码+文字解释问题的本质。

同一种的逻辑结构，在底层通常有两种物理存储结构：
- 顺序存储结构，如一维数组
- 非顺序存储结构，如链式存储结构(链表)、散列

顺序结构适合读操作(为啥呢？因为有索引啊)，链表存储适合写操作(为啥呢？断开，加上节点就完成，不需要底层复制啊)

算法的设计取决于逻辑结构：算法的实现依赖于存储结构。对象的设计取决于类结构，(...)

什么是数据结果呢？数据结构归纳起来所要研究的问题就三方面：

- 数据元素之间的客观联系(**逻辑结构**)
- 数据在计算机内部的存储方式(**存储结构**)
- 针对数据实施的有效的操作和处理(**算法**)

对象之间的关系(对现实的抽象，继承？组合?)，存储在内存中哪里，堆上啊，怎么存？存在数组里？hash表里？怎么处理的啊？增删改查啊，排序那，加密解密啊，

----

### Stack
对于普通的线性表而言，它的作用是一个容器，用于装具有相似结果的数据。
- 分为顺序存储机构和链式存储结构
- 可以进行插入、删除和排序的操作
- 如果线性表之允许在线性表的某端添加、删除元素，这时就演变为：栈和队列。(先进后出(弹夹)，先进先出(火车站排队))

以下图片来自维基百科(百X百科就别看了)

![](https://i.imgur.com/BGzGOlT.jpg)

原谅没放恐怖的，来自Google(百X就别用了)

![](https://i.imgur.com/myNRbXk.jpg)

栈(Stack),是一种特殊的线性表，只能在固定的一端(线性表的尾端)进行插入、删除操作。

- 允许进行插入、删除操作的一端为栈顶(top),另一端，你猜？(bottom)
   - 进栈：将一个元素插入栈的过程，栈的长度+1，(压入子弹)
   - 出栈：删除一个元素的过程，栈的长度-1.(弹出发射...)
- 先进后出，或者说后进先出。
- 常用操作：初始化，(随着栈帧的移除，方法在执行。可能出现[https://stackoverflow.com/](https://stackoverflow.com/)),++i,i++,
- 在Java中继承关系，Stack继承自Vector，List，(abstractList?)

需求：
请编写代码实现Stack类，该类能够实现后进先出的堆栈功能，要求实现的方法包括：
- Stack(int) 实例化指定深度的栈
- boolean push(E item) 像栈顶压入对象，成功返回true，栈已满返回false
- E pop() 从栈顶移除对象并返回，为空则返回null
- E peek() 查看并返回栈顶的对象，为空返回null
- int size() 返回栈中当前元素数量
- int depth() 返回当前堆栈深度
-
**万恶的字符编码，无比的郁闷**以下所有代码参考网络，在Sublime中编写。

基于单列表实现：
```java
class Node<E> {
    Node<E> next = null;
    E data;
    public Node(E data) {
        this.data = data;
    }
}

//采用单链表实现栈
public class MyStack<E> {
    int depth;   //栈的深度

    public MyStack(int i) {
        this.depth = i;
    }

    Node<E> top = null;

    //将元素压入栈中
    public boolean push(E data) {
        if(size() < depth) {
        Node<E> newNode = new Node<E>(data);
        newNode.next = top;
        top = newNode;
        return true;
        }
        return false;
    }

    //读取栈中的头节点，不删除头节点

    public E peek() {
        if(top ==null) {
            return null;
        }
        return top.data;
    }

    //获取栈中的头节点，并删除头节点
    public E pop() {
        if(top ==null) {
            return null;
        }
        Node<E> tmp = top;
        top = top.next;
        return tmp.data;
    }
    //栈的元素个数

    public int size() {
        int len = 0;
        Node tmeNode = top;
        while(tmeNode != null) {
            tmeNode = tmeNode.next;
            len++;
        }
        return len;
    }

    //当前栈的深度
    public int depth() {
        return this.depth;
    }
    public static void main(String[] args) {
      MyStack stack = new MyStack(2);
      System.out.println(stack.push(1));
      System.out.println(stack.push(2));
      System.out.println(stack.push(3));
      System.out.println("栈的元素个数： " +stack.size());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println("栈的元素个数： " + stack.depth());
    }
}
---------------------------此代码来自《Java编程思想》----------------------------------
import java.util.LinkedList;

public class Stack<T> {
  private LinkedList<T> storage = new LinkedList<T>();
  public void push(T v) { storage.addFirst(v); }
  public T peek() { return storage.getFirst(); }
  public T pop() { return storage.removeFirst(); }
  public boolean empty() { return storage.isEmpty(); }
  public String toString() { return storage.toString(); }
}
```
在来看看大佬的另一种实现，简单明了啊。
```java
public class LinkedStack<T> {

	private static class Node<U> {
		U item;
		Node<U> next;
		Node() {
			item = null;
			next =null;
		}
		Node(U item,Node<U> next) {
			this.item = item;
			this.next = next;
		}
		boolean end() {
			return item == null && next == null;
		}
	}

	private Node<T> top = new Node<T>();

	public void push(T item) {
		top = new Node<T>(item,top);
	}

	public T pop() {
		T result = top.item;
		if (!top.end()) {
			top = top.next;
		}
		return result;
	}

	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<String>();
		for (String s : "Phasers on stun!".split(" ") )
			lss.push(s);

		String s;
		while((s = lss.pop()) != null)
			System.out.println(s);
	}
}
输出如下：
I:\Java\note\sort\code>java LinkedStack
stun!
on
Phasers
```

### Queue

队列(queue),也是一种特殊的线性表，使用固定的一端来插入数据，另一端用于删除数据

- 先进先出，就像火车站排队买票一样！！！，整个队伍向前面移动。
- 分为顺序队列结构和链式队列结构
- 从JDK 5 开始，Java集合框架提供了Queue接口，实现该接口的类可以当成队列使用，如LinkedBlockingQueue，PriorityBlockingQueue。
- 可以通过轮询和等待-通知机制实现阻塞队列。

具体Queue实现：

```java
import java.util.*;

public class SimpleQueue<T> implements Iterable<T> {
	private LinkedList<T> storage = new LinkedList<T>();
	public void add(T t){
		storage.offer(t);
	}
	public T get() {
		return storage.poll();
	}
	public Iterator<T> iterator() {
		return storage.iterator();
	}

	public static void main(String[] args) {
		SimpleQueue queue = new SimpleQueue();
		queue.add(8);
		System.out.println(queue.get());
	}
}
```

我们在来看看用Stack如何实现Queue，非常不错，《Java编程思想》
```java
import java.util.Stack;

 public class MyQueue{
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> stackTmp = new Stack<Integer>();

	//Push element X to the back of queue
	public void push(int x) {
		stack.push(x);
	}

	//Removes the element form in front of queue
	public void pop() {
		if (stackTmp.isEmpty()) {
			while (!stack.isEmpty()) {
				int tmp = stack.peek();
				stackTmp.push(tmp);
				stack.pop();
			}
		}
		else {
			stackTmp.pop();
		}
	}

	//Get the front element
	public int peek() {
		if (!stackTmp.isEmpty()) {
			int tmp = stack.peek();
			stackTmp.push(tmp);
		}
		return stackTmp.peek();
	}

	//Return whether the queueis empty
	public boolean empty() {
		if (stackTmp.isEmpty() && stack.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		queue.push(8);
		System.out.println(queue.empty());     //false
	}
}
```
### Tree
树，也是一种数据结构，非线性的，这种结构内的元素存在一对多的关系。

- 树，尤其是二叉树应用很广泛，排序二叉树，平衡二叉树，红黑树。
- 二叉树，在普通的树的基础上，让一颗树中每个节点最多只能包含两个子节点，且严格区分左子节点和右子节点(位置不能变化)
  -遍历二叉树，考虑深读，优先遍历。(先序遍历、中序遍历、后续遍历)和广度优先遍历。

- 哈夫曼树，一种带权路径最短的二叉树，在信息检索中非常有用
- 哈夫曼编码，假设需要对一个字符串如“abcabcabc”进行编码，将它转化为唯一的二进制码，同时要求转换出来的二进制码的长度最小。可以采用哈夫曼树来解决报文编码问题
- 排序二叉树：一种特殊的二叉树，可以非常方便的对树中的所有节点进行排序和检索。


二叉树，这里采用递归和内部类的思想。
```java
public class BinaryTree {
	private Node root;

	//添加节点
	public void add(int data) {
		if (root ==null) {
			root = new Node(data);
		}else {
			root.addNode(data);
		}
	}
	//打印节点
	public void print() {
		root.printNode();
	}

	private class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}

		public void addNode(int data) {
			//核心思想就是进来先个当前节点比，如果如果小于则在左边添加，如果左边没子节点，则创建，如果有添加
			if (this.data > data) {
				if (this.left == null) {
					this.left = new Node(data);
				}else {
					this.addNode(data);    //这里应该是采用递归。
				}
			}else {
				if (this.right == null) {
					this.right = new Node(data);
				}else {
					this.right.addNode(data);
				}
			}
		}

		//中序遍历
		public void printNode() {
			if (this.left != null) {
				this.left.printNode();
			}
			System.out.println(this.data + "->");
			if (this.right !=null) {
				this.right.printNode();
			}
		}
	}
}
------------------------测试-----------------------------------------------
public static void main(String[] args) {

  BinaryTree bt = new BinaryTree();
  // 8、3、10、1、6、14、4、7、13
  bt.add(8);bt.add(3);bt.add(10);
  bt.add(1);bt.add(6);bt.add(14);
  bt.add(4);bt.add(7);bt.add(13);
  bt.print();
}
输出：
1->3->4->6->7->8->10->13->14->
```

### LinkedList

ArrayList因为乱码，写了一半，无奈啊，完全坑我，其思想就是根据索引，涉及到扩容，判断越界了么，。这里先不管了。直接看LinkedList。

```java
public class MyLinkedList {
	protected Node first;	// 链表的第一个节点
	protected Node last;	// 链表的最后一个节点
	private int size;	// 节点的数量

	// 链表中的每一个节点
	public class Node {
		public Node(Object ele) {
			this.ele = ele;
		}

		Node prev;				// 上一个节点对象
		Node next;				// 下一个节点对象
		public Object ele; // 当前节点中存储的数据
	}

	public void addFirst(Object ele) {
		Node node = new Node(ele);      //需要保存的节点对象
		//进来一个节点，如果为空的话，它可定时第一个，也是最后一个
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			node.next = this.first;				// 把之前第一个作为新增节点的下一个节点，(进来一个，当前的只能当老二了。)
			this.first.prev = node;				// 把新增节点作为之前第一个节点的上一个节点
			this.first = node;					// 把新增的节点作为第一个节点
		}
		size++;
  }
     //这里很重要，别忘记
	public void addLast(Object ele) {
		Node node = new Node(ele);
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			this.last.next = node;			// 新增节点作为之前最后一个节点的下一个节点(因为是加在后面，所以当前节点的下一个才是 新增节点)
			node.prev = this.last;			// 之前最后一个节点作为新增节点的上一个节点
			this.last = node;				// 把新增的节点作为最后一个节点
		}
	}
	//原谅我复制了
	public void remove(Object ele) {
		// 找到被删除的节点
		Node current = this.first;// 确定为第一个节点,从头到尾开始找
		for (int i = 0; i < size; i++) {
			if (!current.ele.equals(ele)) {// 当前为true !true 为false ,说明找到当前ele,输出
				if (current.next == null) { // 续上: 如果false取反为true, 判断是否最后一个,
					return;
				}
				current = current.next;
			}
		}
		//删除节点
		if(current==first){
			this.first = current.next; //当前的下一个作为第一个
			this.first.prev = null; //当前下一个对上一个的引用设置为null

		}else if(current==last){
			this.last = current.prev;
			this.last.next = null;
		}else{
			//把删除当前节点的下一个节点作为删除节点的上一个节点的next
			current.prev.next =current.next;
			//把删除节点的上一个节点作为删除节点下一个节点的prev
			current.next.prev = current.prev;

		}
		size--;
		//System.out.println("current =" + current.ele);
	}

	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder(size * 2 + 1);
		Node current = this.first;// 第一个节点
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(current.ele);
			if (i != size - 1) {
				sb.append(",");
			} else {
				sb.append("]");
			}
			current = current.next; // 获取自己的下一个节点
		}
		return sb.toString();
	}
}
```

这个双向列表有点难理解，还是看图吧，

线性链表：
![](https://i.imgur.com/49BOJ42.jpg)

双向链表：
![](https://i.imgur.com/nDimFWH.jpg)


先到这里吧，gogogo，就会是留给有准备的人，




































































































-
