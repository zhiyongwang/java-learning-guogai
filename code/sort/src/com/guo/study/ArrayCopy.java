package com.guo.study;

/**
 * Created by guo on 2018/2/3.
 * 源：
 * int[] src = {1,3,5,7,9,11}
 * 目标：
 * int[] dest = new int[10]
 * 需求：把src数组中指定几个元素拷贝到dest数组中
 * 参数：
 * src：源，从哪个数组中拷贝数据
 * dest：目标：把数据拷贝到哪一个数组中
 * srcpos：从原数组中哪一个位置开始拷贝
 * destPos：在目标数组开始存放的位置
 * length：拷贝的个数
 */
public class ArrayCopy {
    public static void main(String[] args) {
        int[] src = {1, 3, 5, 7, 9, 11};
        int[] dest = new int[10];
        System.out.print("拷贝之前的数组：");
        print(src);

        //拷贝元素
        arrayCopy(src, 1, dest, 3, 4);

        
        System.out.print("拷贝之后的数组：");
        print(dest);


        System.out.print("系统底层拷贝之后的数组：");
        /**
         * 底层实现的数组拷贝，是一个本地方法
         * void arraycopy(Object src,  int  srcPos,Object dest, int destPos,int length);
         *  src      the source array.
         * srcPos   starting position in the source array.
         * dest     the destination array.
         * destPos  starting position in the destination data.
         * length   the number of array elements to be copied.
         */
        System.arraycopy(src,-1,dest,2,3);
        print(dest);
    }

    /**
     * 参数：
     * src：源，从哪个数组中拷贝数据
     * dest：目标：把数据拷贝到哪一个数组中
     * srcpos：从原数组中哪一个位置开始拷贝
     * destPos：在目标数组开始存放的位置
     * length：拷贝的个数
     */
    static void arrayCopy(int[] src, int srcPos, int[] dest, int destPos, int length) {
        if(srcPos < 0 || destPos < 0 || length < 0) {
            throw  new RuntimeException("出异常了，重新检查");
        }
        for (int index = srcPos; index < srcPos + length; index++) {
            dest[destPos] = src[index];
            destPos++;

        }
    }

    static void print(int[] arr) {
        String str = "[";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i];
            if (i != arr.length - 1) {   //不是最后一个元素
                str = str + ',';
            }
        }
        str = str + "]";
        System.out.println(str);
    }
}
