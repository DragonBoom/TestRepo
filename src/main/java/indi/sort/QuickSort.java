package indi.sort;

public class QuickSort {

	/**
	 * 从小到大排序，思路：递归实现，按一定规范获取指定值，将所有比指定值小的数移到其左边，所有比指定数值大的数移到右边，
	 * 再将该数值的左、右两边单独取出，重复该操作，最终完成排序<br>
	 * 中枢元素? <br>
	 * 快速排序是不稳定排序！对从小到大的序列，指定中枢元素？并根据其进行排序后，就有可能改变將其他和中枢元素相同的元素的相对位置<br>
	 * 平均时间复杂度 O(nlogn), 最优时间复杂度O(n), 最差时间复杂度O(n^2)(数组有序时需要遍历 n(n-1)/2次), 空间复杂度O(logn)
	 * 
	 * @param toSort
	 */
	public static void sort(int[] toSort) {
		sortAll(toSort, 0, toSort.length - 1);
	}

	private static void sortAll(int[] toSort, int low, int high) {
		int value = sortForValue(toSort, low, high);
		if (high - low == 1) {
			return;
		}
		value -= 1;
		if (value > low) {
			sortAll(toSort, low, value);
		}
		value += 2;
		if (value < high) {
			sortAll(toSort, value, high);
		}
	}

	/**
	 * 值交换策略该如何选择？<br>
	 * 
	 * @return 返回选定值排序后的位置
	 */
	private static int sortForValue(int[] toSort, int low, int high) {
		int temp = selectValue(low, high);
		int tempValue = toSort[temp];
		/**
		 * 將下标为low的值保存到下标为temp的位置，使得空出下标low用于交换，每次循环后將交换位依low->high->low的顺序变换，
		 * 即每次循环结束后，交换位仍会回到下标为low的位置(期间low或high具体的值可能变化)
		 */
		toSort[temp] = toSort[low];
		while (low < high) {
			while (low < high && toSort[high] >= tempValue) {
				high--;
			}
			toSort[low] = toSort[high];
			while (low < high && toSort[low] <= tempValue) {
				low++;
			}
			toSort[high] = toSort[low];
		}
		/**
		 * 用一开始已经取出来的交换位的值填补交换位，最后返回交换位的位置
		 */
		toSort[low] = tempValue;
		return low;
	}

	/**
	 * 确定排序时的指定的中间值的序号（常用第一个数值）
	 * 
	 * @return
	 */
	private static int selectValue(int low, int high) {
		return low;
	}

	@SuppressWarnings("unused")
	private static void showPart(int[] toSort, int low, int high) {
		int temp = low;
		System.out.println("------------");
		while (temp <= high) {
			System.out.println(toSort[temp]);
			temp++;
		}
		System.out.println("------------");
	}
}
