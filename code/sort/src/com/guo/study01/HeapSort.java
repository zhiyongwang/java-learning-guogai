package com.guo.study01;

/**
 * ������
 * @author shkstart
 * 2013-11-27
 */
public class HeapSort {
	public static void heapSort(DataWrap[] data) {
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		// ѭ������
		for (int i = 0; i < arrayLength - 1; i++) {
			// ����
			builMaxdHeap(data, arrayLength - 1 - i);
			// �����Ѷ������һ��Ԫ��
			swap(data, 0, arrayLength - 1 - i);
			System.out.println(java.util.Arrays.toString(data));
		}
	}

	// ��data�����0��lastIndex���󶥶�
	private static void builMaxdHeap(DataWrap[] data, int lastIndex) {
		// ��lastIndex���ڵ㣨���һ���ڵ㣩�ĸ��ڵ㿪ʼ
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k���浱ǰ�����жϵĽڵ�
			int k = i;
			// �����ǰk�ڵ���ӽڵ����
			while (k * 2 + 1 <= lastIndex) {
				// k�ڵ�����ӽڵ������
				int biggerIndex = 2 * k + 1;
				// ���biggerIndexС��lastIndex����biggerIndex +1
				// ����k�ڵ�����ӽڵ����
				if (biggerIndex < lastIndex) {
					// ������ӽڵ��ֵ�ϴ�
					if (data[biggerIndex].compareTo(data[biggerIndex + 1]) < 0) {
						// biggerIndex���Ǽ�¼�ϴ��ӽڵ������
						biggerIndex++;
					}
				}
				// ���k�ڵ��ֵС����ϴ��ӽڵ��ֵ
				if (data[k].compareTo(data[biggerIndex]) < 0) {
					// ��������
					swap(data, k, biggerIndex);
					// ��biggerIndex����k����ʼwhileѭ������һ��ѭ��
					// ���±�֤k�ڵ��ֵ���������ҽڵ��ֵ
					k = biggerIndex;
				} else {
					break;
				}
			}
		}
	}

	// ����data������i��j������������Ԫ��
	private static void swap(DataWrap[] data, int i, int j) {
		DataWrap temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public static void main(String[] args) {
		DataWrap[] data = { new DataWrap(9, ""), new DataWrap(-16, ""),
				new DataWrap(21, "*"), new DataWrap(23, ""),
				new DataWrap(-30, ""), new DataWrap(-49, ""),
				new DataWrap(21, ""), new DataWrap(30, "*"),
				new DataWrap(30, "")};
		System.out.println("����֮ǰ��\n" + java.util.Arrays.toString(data));
		heapSort(data);
		System.out.println("����֮��\n" + java.util.Arrays.toString(data));
	}
}
