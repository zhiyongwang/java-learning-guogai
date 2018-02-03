package com.guo.study.array;

public class FootballDemo {

	public static void main(String[] args) {
		PlayerList.init(5);
		PlayerList.add(11);
		PlayerList.add(22);
		PlayerList.add(33);
		PlayerList.add(44);
		PlayerList.add(55);
		PlayerList.add(66);
		PlayerList.add(77);
		PlayerList.add(88);
		System.out.println(PlayerList.getNum(2));
		System.out.println(PlayerList.getIndex(44));
		PlayerList.set(2,333);		
		PlayerList.updata(22,222);
		PlayerList.delete(2);
	
	
	
		PlayerList.print();
}
}
