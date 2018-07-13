package indi.algorithm.sort;

import java.util.Random;

import org.junit.Test;

public class MergeSort {

	/**
	 * 归并排序：从小到大排序, 思路： 將序列递归地分成小序列，对小序列排序，再將有序小序列归并成有序的长序列，不断重复直至得到完整的有序序列<br>
	 * 时间复杂度：O(nlogn) , 空间复杂度O(1) ~ O(n) 归并排序是稳定的
	 */

	public static void sort(int[] toSort) {
		mergeSort(toSort, 0, toSort.length - 1);
	}

	private static void mergeSort(int[] toSort, int low, int high) {
		int length = high - low + 1;
		if (length < 2)
			return;
		int halfLength = length / 2;
		int low1 = low;
		int high1 = low + halfLength - 1;
		int low2 = high1 + 1;
		int high2 = high;
		if (length > 2) {
			mergeSort(toSort, low1, high1);
			mergeSort(toSort, low2, high2);
		}
		merge(toSort, low1, high1, low2, high2);
	}

	private static void merge(int[] toSort, int low1, int high1, int low2, int high2) {
		if (toSort[high1] <= toSort[low2]) {
			return;
		}
		// 需要注意，两段可能长短不一!!
		int length = high2 - low1 + 1;
		int[] temp = new int[length];
		int i = 0;
		int low1b = low1;
		while (i < length) {
			if (toSort[low1] < toSort[low2]) {
				temp[i] = toSort[low1];
				low1++;
			} else {
				temp[i] = toSort[low2];
				low2++;
			}
			i++;
			if (low1 > high1) {
				// System.arraycopy(toSort, low2, temp, i, length - i);
				break;
			}
			if (low2 > high2) {
				while (low1 <= high1) {
					temp[i] = toSort[low1];
					low1++;
					i++;
				}
				break;
			}
		}
		int j = 0;
		while (j < i) {
			toSort[low1b] = temp[j];
			low1b++;
			j++;
		}
	}

	@Test
	public void t() {
		int[] is = new int[] { 2, 2, 3, 11, 9, 1111, 0, 6 };
		// int[] is2 = new int[] { 1, 3, 3, 6, 2, 4, 8, 0 };
		// int[] is3 = new int[] { 3, 2, 0, 1};
		// int[] is4 = { 2, 2, 3, 11, 0, 6, 9, 1111 };
		// merger(is4, 0, 3, 4, 7);
		Random r = new Random();
		r.setSeed(r.nextLong());
		Random r2 = new Random();
		r2.setSeed(r.nextLong());
		// 随机数数组
		int[] toSort1 = new int[10];
		int i = 0;
		while (i < toSort1.length) {
			int random = r.nextInt(100) + r2.nextInt(200);
			toSort1[i] = random;
			i++;
		}
		long t1 = System.currentTimeMillis();
		sort(is);
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1 + "ms");
		TestMain.show(is);
	}
}
