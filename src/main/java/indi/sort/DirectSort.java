package indi.sort;

public class DirectSort {

	/**
	 * 直接选择排序，选择类排序<br>
	 * 从小到大排序，思路：遍历length-1次，每次将最小值移到未排序序列的最左边<br>
	 * 时间复杂度为O(n^2), 不稳定
	 * @param toSort
	 */
	public static void sort(int[] toSort) {
		int length = toSort.length - 1;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j <= length; j++) {
				if (toSort[i] > toSort[j]) {
					toSort[i] = toSort[i] ^ toSort[j];
					toSort[j] = toSort[i] ^ toSort[j];
					toSort[i] = toSort[i] ^ toSort[j];
				}
			}
		}
	}
}
