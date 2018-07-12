package indi.sort;

/**
 * 堆排序 O(n logn) 不稳定
 * @author 13124
 *
 */
public class HeapSort {

	public static void sort(int[] toSort) {
		sortHaep(toSort);
	}

	public static void sortHaep(int[] toSort) {
		int i = 0;
		// i 为最后一个非叶子结点位置
		for(i = toSort.length / 2 - 1; i >= 0; i--) {
			adjustHeap(toSort, i, toSort.length);
		}
		for(i = toSort.length - 1; i > 0; i--) {
			// 在堆中取出堆顶（最大值），将其移出堆，放到数组的最右端，之后重新排序
			swap(toSort, 0, i);
			adjustHeap(toSort, 0, i);
		}
	}

	// 调整堆(完全二叉树)，堆中的最大值会被移至堆顶
	public static void adjustHeap(int[] toSort, int i, int length) {
		int temp = toSort[i];
		// 从i的左子节点开始,-每次循环结束后k指向其左子节点(若存在)
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			// 若左子节点小于右子节点，k指向右子节点
			if (k + 1 < length && toSort[k] < toSort[k + 1]) {
				k++;
			}
			// 若子节点大于父节点则將子节点赋值给父节点
			if (toSort[k] > temp) {
				toSort[i] = toSort[k];
				i = k;
			} else {
				break;
			}
		}
		toSort[i] = temp;
	}

	public static void swap(int[] is, int a, int b) {
		is[a] = is[a] ^ is[b];
		is[b] = is[a] ^ is[b];
		is[a] = is[a] ^ is[b];
	}

}
