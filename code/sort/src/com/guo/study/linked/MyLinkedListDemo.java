package com.guo.study.linked;
//节点的测试类
public class MyLinkedListDemo {
public static void main(String[] args) {
	MyLinkedList list = new MyLinkedList();
	list.addLast("C");
	System.out.println(list);
	list.addLast("D");
	System.out.println(list);
	list.addLast("E");
	list.addLast("E");
	list.addLast("E");
	System.out.println(list);
	list.addFirst("B");
	System.out.println(list);
	list.addFirst("A");
	System.out.println(list);
	list.remove("E");
	System.out.println(list);
	list.remove("D");
	System.out.println(list);
}
}
