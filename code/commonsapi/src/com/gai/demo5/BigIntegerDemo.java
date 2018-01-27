package com.gai.demo5;

import java.math.BigInteger;

/**
 * Created by guo on 2018/1/27.
 * BigInteger类四则运算
 */
public class BigIntegerDemo {
    public static void main(String[] args) {
        function_1();
    }
    /**
	 * BigInteger对象的四则运算
	 * 调用方法计算,计算结果也只能是BigInteger对象
	 */
    public static void function_1(){
        BigInteger b1 = new BigInteger("5665464516451051581613661405146");
        BigInteger b2 = new BigInteger("965855861461465516451051581613661405146");

        //计算 b1+b2对象的和,调用方法 add
        BigInteger bigAdd = b1.add(b2);//965855867126930032902103163227322810292
        System.out.println(bigAdd);

        //计算b1-b2对象的差,调用方法subtract
        BigInteger bigSub = b1.subtract(b2);
        System.out.println(bigSub);

        //计算b1*b2对象的乘积,调用方法multiply
        BigInteger bigMul = b1.multiply(b2);
        System.out.println(bigMul);

        //计算b2/b1对象商,调用方法divied
        BigInteger bigDiv = b2.divide(b1);
        System.out.println(bigDiv);
    }
}
