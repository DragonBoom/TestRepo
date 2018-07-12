package indi.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

/**
 * 稳定的排序： 排序前2个相等的数其在序列的前后位置顺序和排序后它们两个的前后位置顺序相同。
 * 空间复杂度：是对一个算法在运行过程中临时占用存储空间大小的量度。
 * 
 * @author 13124
 *
 */
public class TestMain {
	static final org.slf4j.Logger Logger = LoggerFactory.getLogger(TestMain.class);
	List<int[]> toSorts = new ArrayList<int[]>();
	List<int[]> toSortsBackup = new ArrayList<int[]>();
	List<Long> times = new ArrayList<Long>();
	static final int LENGTH = 15;
	static final int BOUND = Integer.MAX_VALUE;

	@BeforeEach
	void before() {
		Random r = new Random();
		r.setSeed(r.nextLong());
		Random r2 = new Random();
		r2.setSeed(r.nextLong());
		// 随机数数组
		int[] toSort1 = new int[LENGTH];
		int i = 0;
		while (i < toSort1.length) {
			int random = r.nextInt(BOUND) + r2.nextInt(BOUND);
			toSort1[i] = random;
			i++;
		}
		toSorts.add(toSort1);
		// 有序数组 (包含负数!!)
		int[] toSort2 = new int[LENGTH];
		i = 0;
		int j = 0;
		int plus = 10;
		while (i < toSort2.length) {
			toSort2[i] = j;
			j += plus;
			plus += plus;
			i++;
		}
		toSorts.add(toSort2);
		// 所有元素相同的数组
		int[] toSort3 = new int[LENGTH];
		i = 0;
		j = r.nextInt(BOUND);
		while (i < toSort3.length) {
			toSort3[i] = j;
			i++;
		}
		toSorts.add(toSort3);
		// 除了最后一个元素外有序的数组
		int[] toSort4 = toSort3.clone();
		toSort4[toSort4.length - 1] = 0;
		toSorts.add(toSort4);

		copyList(toSorts, toSortsBackup);
	}

	@Test
	public void AtestCheckSort() {
		int[] i1 = { 1, 2, 3, 4, 5 };
		int[] i2 = { 1, 2, 3, 4, 5, 7, 2, 4, 9 };
		int[] i3 = { 2, 2, 3, 4, 5, 7, 7, 7, 7 };
		int[] i4 = { 2, 2, 2, 2, 2, 2, 2, 2, 7 };
		Assertions.assertNotEquals(checkSort(i1), false);
		Assertions.assertNotEquals(checkSort(i2), true);
		Assertions.assertNotEquals(checkSort(i3), false);
		Assertions.assertNotEquals(checkSort(i4), true);
		Logger.info("-----this part is sort test-----");
	}

	@Test
	public void AtestEqualSort() {
		int[] i1 = { 1, 2, 3, 4, 5 };
		int[] i2 = { 1, 2, 3, 4, 5, 7, 2, 4, 9 };
		int[] i3 = { 2, 2, 3, 4, 5, 7, 7, 7, 7 };
		int[] i4 = { 2, 2, 2, 2, 2, 2, 2, 2, 7 };
		int[] i5 = { 1, 2, 3, 5, 4 };
		Assertions.assertNotEquals(checkEqual(i1, i2), true);
		Assertions.assertNotEquals(checkEqual(i2, i2), false);
		Assertions.assertNotEquals(checkEqual(i3, i4), true);
		Assertions.assertNotEquals(checkEqual(i1, i5), false);
		Logger.info("-----this part is equal test-----");
	}

	@Test
	public void BquickSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			QuickSort.sort(toSort);
			times.add(System.currentTimeMillis());
		}
		Logger.info(QuickSort.class.getName());
	}

	@Test
	public void CbubbleSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			BubbleSort.sort(toSort);
			times.add(System.currentTimeMillis());
		}
		Logger.info(BubbleSort.class.getName());
	}

	@Test
	public void GmergerSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			MergeSort.sort(toSort);
			times.add(System.currentTimeMillis());
		}
		Logger.info(MergeSort.class.getName());
	}

	@Test
	public void EdirectSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			DirectSort.sort(toSort);
			times.add(System.currentTimeMillis());
		}
		Logger.info(DirectSort.class.getName());
	}

	@Test
	public void FdirectInsertionSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			DirectInsertionSort.sort(toSort);
			times.add(System.currentTimeMillis());
			// show(toSort);
		}
		Logger.info(DirectInsertionSort.class.getName());

	}

	@Test
	public void FheapSort() {
		for (int[] toSort : toSorts) {
			times.add(System.currentTimeMillis());
			HeapSort.sort(toSort);
			times.add(System.currentTimeMillis());
		}
		Logger.info(HeapSort.class.getName());
	}

	@AfterEach
	void after() {
		Iterator<Long> i1 = times.iterator();
		Iterator<int[]> i2 = toSorts.iterator();
		Iterator<int[]> i3 = toSortsBackup.iterator();
		while (i1.hasNext()) {
			int[] is = i2.next();
			int[] isBackup = i3.next();
			boolean checkSort = checkSort(is);
			boolean checkEqual = checkEqual(is, isBackup);
			Assertions.assertNotEquals(checkSort, false);
			Assertions.assertNotEquals(checkEqual, false);
			long t1 = i1.next();
			long t2 = i1.next();
			long time = t2 - t1;
			Logger.info("use {} ms", time);
		}
		Logger.info("-----this part complete-----");
	}

	public boolean checkSort(int[] is) {
		int n = 0;
		if (is[n] == is[n + 1]) {
			n++;
			if (n + 1 == is.length - 1) {
				return true;
			}
		}
		boolean stb = false;
		if (is[n] < is[n + 1]) {
			stb = true;
		}
		int temp = is[0];
		for (int i : is) {
			if (stb) {
				if (i < temp) {
					return false;
				}
			} else {
				if (i > temp) {
					return false;
				}
			}
			temp = i;
		}
		return true;
	}

	public static boolean checkEqual(int[] is, int[] is2) {
		if (is.length != is2.length)
			return false;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : is) {
			Integer temp = map.get(i);
			if (temp == null) {
				map.put(i, 0);
			} else
				map.put(i, temp + 1);
		}
		for (int i : is2) {
			Integer temp = map.get(i);
			if (temp == null) {
				return false;
			}
			if (temp == 0) {
				map.remove(i);
			} else {
				map.put(i, temp - 1);
			}
		}
		return true;
	}

	public static void show(int[] is) {
		for (int i : is) {
			Logger.info("{}", i);
		}
		Logger.info("--complete--");
	}

	public static <T> void copyList(List<int[]> src, List<int[]> dest) {
		for (int[] is : src) {
			dest.add(is.clone());
		}
	}
}
