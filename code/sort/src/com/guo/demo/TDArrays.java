package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 二维数组演示：Java中没有真正的多维数组，多维数组的表示方式是数组中元素还是数组
 * 参加比赛有三个班级各三名学员参赛
 * 记录每个学员的成绩，并计算每个班的平均成绩
 */
public class TDArrays {
    public static void main(String[] args) {
        //定义每个班学员的成绩
        int[][] scores = {{73, 80, 90},{90, 88, 88},{75, 90, 85}};
        //获取总过有几个班
        int classLen = scores.length;
        for (int i = 0; i < classLen; i++) {
             int sum = 0;
             //获取每个班的人数
            int count = scores[i].length;
            for (int j = 0; j < count; j++ ) {
                sum += scores[i][j];
            }
            int avg = sum/count;
            System.out.println("第" + (i + 1) + "班的平均成绩为：" +avg);
        }
    }
}
