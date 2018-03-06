//折半插入排序
public class BinaryInsertSort{
	public static void binaryInsertSort(int[] data) {
		int len = data.length;
		for (int i = 1; i < len; i++) {
			int tmp = data[i];
			int low = 0;
			int high = i - 1;
			while(low <= high) {
				int mid = (low + high) / 2;
				if (tmp > data[mid]) {
					low = mid + 1;
				}
				else {
					high = mid -1;
				}

			}for (int j = i;j > low ; j-- ) {
				data[j] = data[j - 1 ];
			}
			data[low] = tmp;
		}
	}

	public static void main(String[] args) {
		int[] data = {5,2,7,3,9};
		binaryInsertSort(data);
		for (int d : data) {
			System.out.print(" " + d);
		}
	}
}