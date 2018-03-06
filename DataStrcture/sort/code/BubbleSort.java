//冒泡排序
public class BubbleSort {
	static void sort(int[] data) {
		int len = data.length;
		for (int i = 0; i < len - 1; i++ ) {
			for (int j = 0 ; j < len - 1 - i; j++ ) {
				if (data[j] > data[j + 1]) {
					swap(data,j,j+1);
				}
			}
		}

	}
	static void swap(int[] data,int i,int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public static void main(String[] args) {
		int[] data = {4,2,6,8,2,1,0};
		sort(data);
		for(int d : data) {
			System.out.print(" ->" + d);
		}
	}
}