//¿ìËÙÅÅÐò
public class QuickSort {
	private static void swap(int[] data,int i,int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	private static void subSort(int[] data,int start,int end) {
		if (start < end) {
		 int base = data[end];
 
		 int i = start;
		 int j = end + 1;
 
		 while(true) {
		 	while(i < end && data[++i] <= base) ; 
		 	while(j > start && data[--j] >= base);
		 	if (i > j) {
		 		swap(data,i,j);
		 	}else {
		 		break;
			}
		}
		
			swap(data, start, j);
			subSort(data, start, j - 1);
			subSort(data, j + 1, end); 
		}
		
	}
	public static void quickSort(int[] data) {
		subSort(data,0,data.length - 1);
	}

	public static void main(String[] args) {
		int[] data = {9,5,6,88};
		quickSort(data);
		System.out.print(java.util.Arrays.toString(data));
	}
}