package indi.algorithm.sort;

public class QuickSort {

	/**
	 * 从小到大排序，思路：递归实现，按一定规范获取指定值，将所有比指定值小的数移到其左边，所有比指定数值大的数移到右边，
	 * 再将该数值的左、右两边单独取出，重复该操作，最终完成排序<br>
	 * 中枢元素? <br>
	 * 快速排序是不稳定排序！<br>
	 * 平均时间复杂度 O(nlogn), 最优时间复杂度O(n), 最差时间复杂度O(n^2)(数组有序时需要遍历 n(n-1)/2次), 空间复杂度O(logn)
	 * 
	 * @param toSort
	 */
	public static void sort(int[] toSort) {
		sortAll(toSort, 0, toSort.length - 1);
	}

	/**
	 * 递归函数
	 * 
	 * @author DragonBoom
	 * @since 2020.07.01
	 * @param toSort 递归数组
	 * @param low 递归处理的起点下标
	 * @param high 递归处理的终点下标
	 */
	private static void sortAll(int[] toSort, int low, int high) {
	    // 根据中值进行排序，使得所有比中值大的数移动到中值右边，所有比中值小的数移动到中值左边
		int value = sortForValue(toSort, low, high);// 中值下标
		// 判断是否结束递归
		if (high - low == 1) {
		    // 只处理两个元素，按照中值排序后，就表示该段已完成排序，结束递归
			return;
		}
		
		// 处理中值的左半区
		value -= 1;// 中值下标-1即左半区最大元素的下标
		if (value > low) {
		    // 继续递归处理左半区
			sortAll(toSort, low, value);
		}
		// 处理中值的右半区
		value += 2;// + 2是因为之前已经-1，取中值右半区最小元素下标
		if (value < high) {
			sortAll(toSort, value, high);
		}
	}

	/**
	 * 从数组中取一个中值，将所有比中值大的数移动到中值右边，所有比中值小的数移动到左边
	 * 
	 * <p>本方法的复杂度为O(lg(hight - low))~=O(log_2{n})
	 * 
	 * @return 返回选定值排序后的位置
	 */
	private static int sortForValue(int[] toSort, int low, int high) {
	    // 获取中值的下标
		int temp = selectValue(low, high);
		// 取出中值
		int tempValue = toSort[temp];
		/**
		 * 將下标为low的值保存到下标为temp的位置，使得空出下标low用于交换，每次循环后將交换位依low->high->low的顺序变换，
		 * 即每次循环结束后，交换位仍会回到下标为low的位置(期间low或high具体的值可能变化)
		 */
		toSort[temp] = toSort[low];
		while (low < high) {
		    // 从右往左，找到比中值小的数
			while (low < high && toSort[high] >= tempValue) {
				high--;
			}
			// 将其移动到最左边的空位，使得中值右边出现空位（即一定是中值左边）
			// 最好的情况，low = high，即中值右边所有元素都比它大
			toSort[low] = toSort[high];

			while (low < high && toSort[low] <= tempValue) {
				low++;
			}

			toSort[high] = toSort[low];// low = high
		}
		/**
		 * 用一开始已经取出来的交换位的值填补交换位，最后返回交换位的位置
		 */
		toSort[low] = tempValue;
		return low;
	}

	/**
	 * 确定排序时的指定的中间值的序号（常用第一个数）
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
