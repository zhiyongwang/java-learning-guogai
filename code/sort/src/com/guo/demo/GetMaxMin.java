package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 求最大值和最小值算法
 */
public class GetMaxMin {
    public static void main(String[] args) {
        int[] num = {45, 23, 25, 67, 42};

        System.out.println("最大值为：" + getMax(num));
        System.out.println("最大值为：" + getMax1(num));
        System.out.println("最小值为：" + getMin(num));
    }

    public static int getMax(int[] num) {
        int max = num[0];
        int len = num.length;
        for (int i = 1; i < len; i++) {
            if (num[i] > max) {
                //第一种通过临时变量来完成交换
                int temp = num[i];
                num[i] = max;
                max = temp;
            }
        }
        return max;
    }

    public static int getMax1(int[] num) {
        int max = num[0];
        int len = num.length;
        for (int i = 1; i < len; i++) {
            if (num[i] > max) {
                /**
                 * 第二种，通过异或运算实现变量的交换
                 * 此算法能够实现是由异或运算的特点决定的，通过异或运算能够使数据中的某些位翻转，其他位不变。
                 * 这就意味着任意一个数与任意一个给定的值连续异或两次，值不变。
                 */
                num[i] = num[i]^max;
                max = num[i]^max;
                num[i] = num[i] ^ max;
            }
        }
        return max;
    }

    public static int getMin(int[] num) {
        int min = num[0];
        int len = num.length;
        for (int i = 0; i < len; i++) {
            if (num[i] < min) {
                num[i] = num[i] + min;
                min = num[i] - min;
                num[i] = num[i] - min;
            }
        }
        return min;
    }

}
