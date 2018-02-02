package com.guo.demo;

/**
 * Created by guo on 2018/2/2
 * 插入算法.
 * 1,从后向前找到合适的位置插入
 * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排好的子序列的合适位置
 * 直到全部插入为止
 */
public class InsertSort {
    public static void main(String[] args) {
        //待排序的数列
        int[] nums = {43, 23, 64, 24, 34, 78, 32};
        //控制比较的轮数
        for (int i = 1; i < nums.length; i++) {
            //记录操作数
            int temp = nums[i];
            int j = 0;
            for (j = i - 1; j >= 0; j--) {
                //后一个和前一个比较，如果前面的大，则把前面的赋值到后面。
                if (nums[j] > temp) {
                    nums[j+1] = nums[j];
                } else {
                    break;
                }
            }
            if (nums[j + 1] != temp) {
                nums[j + 1] = temp;
            }
        }
        //输出结果
        for(int n : nums) {
            System.out.println(n);
        }
    }
}
