package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 冒泡排序算法
 * 1.比较相邻的两个元素，如果第一个比第二大则交换位置，
 * 2.对每一个同样的元素做同样的判断，从开始第一个到结尾巴的最后一个，
 * 3.针对所有的元素重复以上步骤，除了最后一个
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 * 5.相同元素的前后顺序并没有发生变化，所以冒泡排序是一种稳定排序算法
 */
public class BubbleSort {
    public static void main(String[] args) {

        int[] nums = {34, 32, 76, 34, 24};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        for (int n : nums) {
            System.out.println(n);
        }
    }
}
