package com.guo.study.stack;


import com.guo.study.array.MyArrayList;

//ջ
public class MyStack extends MyArrayList {
	//int size = 0;
	//进栈：把新添加的数据压入栈顶
	public void push(Object ele ){
		this.add(ele);
		//size ++;
	}
	//出栈，移除栈顶的元素，
	public void pop(){
		int index = super.size-1;
		this.delete(index);
	}
	//获取栈顶的元素
	public Object search(){
		int index = super.size-1;
		 return super.getNum(index);
		
	}
	
public static void main(String[] args) {
 MyStack s = new MyStack();
 s.init(5);
 s.push("a");
 s.push("b");
 s.push("c");
 s.push("d");
 s.push("e");
 s.push("f");
 s.print();
 s.pop();
 s.print();

Object a = s.search();
System.out.println(a);
}
}
