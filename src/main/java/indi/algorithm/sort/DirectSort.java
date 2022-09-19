package indi.algorithm.sort;

public class DirectSort {

	/**
	 * 直接选择排序，选择类排序<br>
	 * 从小到大排序，思路：每次遍历剩余未排序元素，將最小或最大元素移动到整个序列的一端<br>
	 * 时间复杂度为O(n^2), 不稳定
	 * 
	 * <p>不稳定 例子：220 
	 * 
	 * <p>需要注意与冒泡排序的区别
	 * 
	 * @param toSort
	 */
	public static void sort(int[] toSort) {
		int length = toSort.length - 1;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j <= length; j++) {
			    // 若有元素小于toSort[i]，则将其与toSort[i]互换位置
				if (toSort[j] < toSort[i]) {
					toSort[i] = toSort[i] ^ toSort[j];
					toSort[j] = toSort[i] ^ toSort[j];
					toSort[i] = toSort[i] ^ toSort[j];
				}
			}
		}
	}
}
