import java.util.Arrays.*;
//选择排序,
public class SelectSort{
	public static void selectSort(int[] data) {

		int arrayLength = data.length;
		for (int i = 0; i < arrayLength - 1 ; i++ ) {
			for (int j= i +1; j < arrayLength; j++ ) {
				if (data[i] > data[j]) {
					swap(data,i,j);
				}
			}
		}
		System.out.println("排序后 " + java.util.Arrays.toString(data));

		
	}
	 //第一种通过临时变量来完成交换
	static void swap(int[] data,int i,int j) {
			int tmp = data[i];
			data[i] = data[j];
			data[j] = tmp;
		}


	public static void main(String[] args) {
		int[] data = {3,4,2,6,8,1,9};
		selectSort(data);
	}
}