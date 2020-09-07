package indi.algorithm.sort;

public class DirectInsertionSort {

	/**
	 * 直接插入排序
	 * <p>
	 * 思路：將第i(i > 2)个数插入到排序好的前i-1个数中<br>
	 * 从小到大排序: 將要插入的数与其左边数从右往左的挨个比较，將所有比要插入的数大的值右移一位，再將该值插入到空出来的位置<br>
	 * 时间复杂度: O(n^2), 最优时间复杂度O(n)(有序且同序序列), 最差时间复杂度O(n^2), 空间复杂度O(1)
	 * 
	 */
	public static void sort(int[] toSort) {
		int length = toSort.length;
		if (length < 2)
			return;
		for (int i = 1; i < length; i++) {// i 表示待插入的元素
			int toInsert = toSort[i];// 待操作元素
			int j = i - 1;
			if (toInsert >= toSort[j])// 如果i所在元素，比已排序数字中最大的大，表明已经有序，不需要插入
				continue;
			// 向已排序数字中插入下标为i的元素，即找到比操作元素更小的元素
			while (j >= 0) {
				if (toSort[j] >= toInsert) {
					toSort[j + 1] = toSort[j];// 对于比操作元素更大的元素，右移以腾出插入的空间
					if (j == 0) {
						toSort[j] = toInsert;
						break;
					}
					j--;
				} else {
					toSort[j + 1] = toInsert;
					break;
				}
			}

		}
	}
}
