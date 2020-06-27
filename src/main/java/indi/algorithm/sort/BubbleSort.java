package indi.algorithm.sort;

public class BubbleSort {

	/**
	 * 冒泡排序<br>
	 * 从小到大排序，思路：两两比较，每次將最小或最大元素移动到整个序列的一端<br>
	 * 平均(最差)时间复杂度：O(n^2) , 最优时间复杂度: O(n), 空间复杂度: O(n), 辅助空间 O(1)， 冒泡排序是稳定的
	 */
	public static void sort(int[] toSort) {
		int length = toSort.length - 1;
		boolean didSwap = false;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i; j++) {
				int n = j+1;
				if (toSort[j] > toSort[n]) {
					toSort[j] = toSort[j] ^ toSort[n];
					toSort[n] = toSort[j] ^ toSort[n];
					toSort[j] = toSort[j] ^ toSort[n];
					didSwap = true;
				}
			}
			if (!didSwap) {
				return;
			}
		}
	}
}
