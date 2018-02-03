package com.guo.study.queue;
//˫�����

import com.guo.study.linked.MyLinkedList;

public class MyDeque extends MyLinkedList {

	public Object getFirst(){
		return this.first.ele;
	}
 public Object getLast(){
	 return this.last.ele;
 }
 public void removeFirst(){
	 remove(this.first.ele);
 }
  public void removeLast(){
	  remove(this.last.ele);
  }
 public void addFirst(Object ele){
	 super.addFirst(ele);
 }
  public void addLast(Object ele){
	  super.addLast(ele);
  }
 public static void main(String[] args) {
	MyDeque d = new MyDeque();
	d.addLast("aa");
	d.addLast("bb");
	System.out.println(d);
	d.addFirst("cc");
	System.out.println(d);
	d.addFirst(12345);
	System.out.println(d);
	System.out.println(d.getFirst());
	System.out.println(d.getLast());
	System.out.println(d);
	d.removeLast();
	d.removeFirst();
	System.out.println(d);
}
  
}
