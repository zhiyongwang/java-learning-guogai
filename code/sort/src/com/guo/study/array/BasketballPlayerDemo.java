package com.guo.study.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//1):初始容量为5的线性列表,准备用来存储场上的5个球衣号码.   num 表示球衣号码
//2):安排5个球员上场:[11,22,33,44,55].
//3):查询指定位置的球员的球衣号码是多少.查询索引位置为2的球衣号码是:33.
//4):根据球衣号码查询该球员在场上的索引位置. 44球衣号的球员在场上的索引位置是:3.
//5):替换场上索引位置为2的球员,替换之后该位置的球衣编号为333. 333把33替换了.
//5):替换场上索引位置为2的球员,替换之后该位置的球衣编号为333. 333把33替换了.
//6):替换球衣号码为22的球员,替换之后为222.
//7):把场上索引位置为2的球衣罚下场(注意:罚下,没有补位.).
//8):按照球员在场上的位置,打印出球衣号码,打印风格:[11,22,33,44,55].
public class BasketballPlayerDemo {
	// 用来存储球员的球衣号码
	private static Integer[] players = null;
	// 设置球场上的球员个数
	private static int size = 0;
	
//1):初始容量为5的线性列表,准备用来存储场上的5个球衣号码.  初始化
	private static void init(int initial){  //代码的增强
		if(initial <0){
			throw new IllegalArgumentException("容量不可以为负");
		}
		 players = new Integer[initial];
	}
//2):安排5个球员上场:[11,22,33,44,55].
	private static void add(Integer num){
		//判断扩容
		if( size == players.length){
	Integer[] temp =	Arrays.copyOf(players, players.length*2);
	players = temp ;
		}
		players[size] = num ;
		size++;
	}
//3):查询指定位置的球员的球衣号码是多少.查询索引位置为2的球衣号码是:33.
	private  static Integer getNum(int index){
		if(index <0 || index > size){  //代码的增强
			throw new IllegalArgumentException("索引越界啦");
		}
		return players[index];
	}
//4):根据球衣号码查询该球员在场上的索引位置. 44球衣号的球员在场上的索引位置是:3.
	// 查询索引位置要一个一个元素查询
	private  static int  getIndex(Integer num ){
		for (int index = 0; index < size; index++) {
		if (players[index].equals(num)) {
			return index;
		}	else{
			System.out.println("没有这位球员");
		}	
	}
		return -1; //注意此刻return位置,在for循环外面, 一直判断完成,没有符合的index ,则返回 -1 	
}
//5):替换场上索引位置为2的球员,替换之后该位置的球衣编号为333.   333把33替换了.
	private static  void set(int index ,Integer newNum){
		if(index <0 || index > size){  //代码的增强
			throw new IllegalArgumentException("索引越界啦");
		}
	players[index] = newNum ;
	}
//6):替换球衣号码为22的球员,替换之后为222.
	private static void updata(Integer oldNum , Integer newNum){
		int index =getIndex(oldNum);  // 直接调用获取索引地址的方法,判断是否存在
		if(index > 0){
			set(index , newNum);  //如果存在 ,调用  5 的方法
		}

	}
//7):把场上索引位置为2的球衣罚下场(注意:罚下,没有补位.).
	private static void delete(int index){
		if(index <0 || index > size){  //代码的增强
			throw new IllegalArgumentException("索引越界啦");
		}
		for (int i = index; i < size-1; i++) { //删除一个对象之后,将索引位置为index后面的元素往前移动一个位置
			players[i] = players[i+1]; // 后一位球衣号往前挪一个位置
		}
			players[size-1] = null;  //将最后一个对象赋值null
		size -- ;
	}
	
//8):按照球员在场上的位置,打印出球衣号码,打印风格:[11,22,33,44,55].
	private static void print(){
		if (players == null ) {
			System.out.println("null");
			return ;
		}
		if (size == 0) {
			System.out.println("[]"); 
			return;
		}
		
		StringBuilder sb =new StringBuilder();//此处没有定义长度的话默认值为16
		sb.append("[");
		for (int index = 0; index < size; index++) {
			sb.append(players[index]);
			if(index != (size-1)){//判断是不是最后一个元素
				sb.append(",");
			}else{
				sb.append("]");
			}
		}
		System.out.println(sb.toString());
	}	
	
	public static void main(String[] args) {
		//1):初始容量为5的线性列表,准备用来存储场上的5个球衣号码.   num 表示球衣号码
		init(5);
		//2):安排5个球员上场:[11,22,33,44,55].
		add(11);
		add(22);
		add(33);
		add(44);
		add(55);
		add(66);
		add(77);
		add(88);
		//3):查询指定位置的球员的球衣号码是多少.查询索引位置为2的球衣号码是:33.
		System.out.println(getNum(2));
		//4):根据球衣号码查询该球员在场上的索引位置. 44球衣号的球员在场上的索引位置是:3.
		System.out.println(getIndex(44));
		//5):替换场上索引位置为2的球员,替换之后该位置的球衣编号为333. 333把33替换了.
		set(2,333);		
		//6):替换球衣号码为22的球员,替换之后为222.
		updata(22,222);
		//7):把场上索引位置为2的球衣罚下场(注意:罚下,没有补位.).[11,222,44,55]
	delete(2);
		//8):按照球员在场上的位置,打印出球衣号码,打印风格:[11,22,33,44,55].	
	
	
	
		print();		
	
	
	
	}
	
	
	
	
	
	
}
