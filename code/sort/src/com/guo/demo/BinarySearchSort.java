package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 二分查找法(折半查找)：前提是在已经排好序的数组中，通过将待查找的元素
 * 与中间索引值对应的元素进行比较，若大于中间索引值对应的元素，去右半边查找，
 * 否则，去左边查找。依次类推。直到找到位置；找不到返回一个负数
 */
public class BinarySearchSort {
    public static void main(String[] args) {
        //必须保证数列是有序的
        int[] nums = {12, 32, 55, 67, 87, 98};
        int i = binarySearch(nums, 87);
        System.out.println("查找数的下标为：" + i);   //输出下标为4
    }

    /**
     * 二分查找算法
     *
     * @param nums
     * @param key
     * @return
     */
    public static int binarySearch(int[] nums, int key) {
        int start = 0;             //开始下标
        int end = nums.length - 1; //结束下标
        while (start <= end) {
            int middle = (start + end) / 2;
            // 如果查找的key比中间的大，则去掉左边的值
            if (key > nums[middle]) {
                start = middle + 1;
             //如果查找的key比中间的小，则去掉右边的。
            } else if (key < nums[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        //找不到则返回-1
        return -1;
    }
}
