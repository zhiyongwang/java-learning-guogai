package com.guo.study.array;

public class MyArrayListDemo {
public static void main(String[] args) {
	//创建数组列表对象
	MyArrayList list = new MyArrayList(); //方法没有static 修饰 
	list.init(5);
	//2):安排5个球员上场:[11,22,33,44,55].
	list.add(11);
	list.add(22);
	list.add(44);
	list.add(55);
	list.add(66);
	list.add(77);
	list.add(88);
	System.out.println(list.getNum(2));
	System.out.println(list.getIndex(44));
	list.set(2,333);		
	list.updata(22,222);
	list.delete(2);



	list.print();		

}
}
