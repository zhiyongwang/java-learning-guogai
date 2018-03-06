//÷±Ω”≤Â»Î≈≈–Ú
public class InsertSort {
	public static void insertSort(int[] data) {
		int arrayLength = data.length;
		for (int i = 1; i < arrayLength; i++) {
			int tmp = data[i];
			
			if (data[i] < data[i -1]) {
				int j = i - 1;
				for (;j >=0 && data[j] > tmp; j-- ) {
					data[j + 1] = data[j];
				}
				data[j + 1] = tmp;
			}
			
		}
	}
	public static void main(String[] args) {
		int[] data = {5,2,6,9};
		insertSort(data);
		for (int d :data ) {
			System.out.print(" " + d);
			
		}
	}
}