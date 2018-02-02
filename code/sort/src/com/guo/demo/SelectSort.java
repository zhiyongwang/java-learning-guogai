package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 选择排序算法
 * 1.每一趟从待排序的数据元素中取出最小(或最大)的一个元素，
 * 2.顺序放在已排好序的数列最后面，直到全部待排序的数据元素排完。选择排序是不稳定的排序方法
 */
public class SelectSort {
    public static void main(String[] args) {
        //待排序的数列
        int[] date = {43,23,64,24,34,78,32};
        //记录每次比较的最小值下标
        int minIndex = 0;
        int arrayLength = date.length;
        //控制轮数
        for (int i = 0; i < arrayLength -1; i++) {
            minIndex = i;  //每次假设一个最小值下标
            for (int j = i + 1; j < arrayLength; j++) {
                if (date[minIndex] > date[j]) {
                    minIndex = j;
                }
            }
            //判断需要交换的数下标是否为自己
            if (minIndex != i) {
                 date[minIndex] = date[minIndex] + date[i];
                 date[i] = date[minIndex] - date[i];
                 date[minIndex] = date[minIndex] - date[i];
            }
        }
        //输出结果
        for(int d : date){
            System.out.println(d);
        }
        
    }
}
