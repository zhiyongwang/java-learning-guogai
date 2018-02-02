package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 递归算法是一种直接或间接地调用自身算法的过程
 * 在计算机编写程序中，给递归算法对解决一大类问题是十分有效的，
 * 它往往使算法的描述简洁而且易于理解。
 */
public class Factorial {
    public static void main(String[] args) {
        long result = factorial(34);    //4926277576697053184
       System.out.println(result);
        
        long result1 = factorial2(34);    //4926277576697053184
        System.out.println(result1);

    }

    /**
     * 1.递归必须要有出口
     * 2.递归内存消耗大，容易发生内存溢出。
     * 3.层次调用越多，内存占用越大
     * @param n
     * @return
     */
    public static long factorial(long n) {
        if (n == 1) {
            return 1;
        } else {
            long recurse = factorial(n - 1);
            long result = n * recurse;
            return result;
        }
    }
    public static long factorial2(long n) {
        long result = n;
        long i = n - 1;
        do{
             result = result * i;
                 i--;
        }   while (i > 1);
            return result;
    }
}



