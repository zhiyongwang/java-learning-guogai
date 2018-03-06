//Ñ¡ÔñÅÅÐòËã·¨
//1¡¢Ã¿Ò»ÌÉ´Ó´ýÅÅÐòµÄÊý¾ÝÔªËØÖÐÈ¡³ö×îÐ¡µÄÒ»¸öÔªËØ£¬
//2¡¢Ë³Ðò·ÅÈëÒÑ¾­ÅÅºÃµÄÊýÁÐµÄ×îºóÃæ£¬Ö±µ½È«²¿µÄÊý¾ÝÔªËØÅÅºÃ¡£
public class SelectSort1 {
	public static void selectSort1(int[] data) {
		int arraylength = data.length;
		for (int i = 0; i < arraylength - 1; i++ ) {
			int minIndex = i;      //Ã¿´Î¼ÙÉèÒ»¸ö×îÐ¡ÖµÏÂ±ê
			for (int j = i + 1; j < arraylength ; j++ ) {
				if (data[minIndex] > data[j]) {
				minIndex = j;
				}
			}
			//ÅÐ¶ÏÐèÒª½»»»µÄÏÂ±êÊÇ·ñÎª×Ô¼º
			if (minIndex != i) {
				 data[minIndex] = data[minIndex] + data[i];
                 data[i] = data[minIndex] - data[i];
                 data[minIndex] = data[minIndex] - data[i];
			}
			
		}
		//Êä³ö½á¹û
		for (int d :data ) {
			System.out.println("ÅÅÐòÖ®ºó£º" + d);
		}
	}

	public static void main(String[] args) {
		int[] data = {3,4,2,6,8,1,9};
		selectSort1(data);
	}
}
