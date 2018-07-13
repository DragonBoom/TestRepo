package indi.algorithm.sort;

public class DirectInsertionSort {

	/**
	 * 思路：將第i(i > 2)个数插入到排序好的前i-1个数中<br>
	 * 从小到大排序: 將要插入的数与其左边数从右往左的挨个比较，將所有比要插入的数大的值右移一位，再將该值插入到空出来的位置<br>
	 * 时间复杂度: O(n^2), 最优时间复杂度O(n)(有序且同序序列), 最差时间复杂度O(n^2), 空间复杂度O(1)
	 * 
	 */
	public static void sort(int[] toSort) {
		int length = toSort.length;
		if (length < 2)
			return;
		for (int i = 1; i < length; i++) {
			int temp = toSort[i];
			int j = i - 1;
			if (toSort[j] <= temp)
				continue;
			while (j >= 0) {
				if (toSort[j] >= temp) {
					toSort[j + 1] = toSort[j];
					if (j == 0) {
						toSort[j] = temp;
						break;
					}
					j--;
				} else {
					toSort[j + 1] = temp;
					break;
				}
			}

		}
	}
}
