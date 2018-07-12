package indi.dynamicProgramming;

import org.junit.Test;

/**
 * 动态规划的01背包问题
 * 
 * @author 13124
 *
 */
public class The01BackpackQuestion {

	@Test
	public void mainTest() {
		int[] weight = { 1, 2, 5, 7, 11, 6, 4 };
		int[] value = { 4, 7, 2, 9, 7, 5, 9 };
		int capacity = 10;
		int result = getResult(weight, value, capacity);
		System.out.println(result);
	}

	private int getResult(int[] w, int[] v, int capacity) {
		int length = w.length;
		int[][] max = new int[length][capacity + 1];
		// max[0][1]~max[0][capacity]均为0 (x)
		int n = 1;
		while (n <= capacity) {
			if (n >= w[0])
				max[0][n] = v[0];
			System.out.println(max[0][n]);
			n++;			
		}
		for (int i = 1; i < length; i++) {
			for (int j = 1; j <= capacity; j++) {
				// 获取当可用容量为j时，在前i-1个数已经取出最优解的前提下判断能否插入第i个数，若可以插入，再判断插入第i个数是否为最优解，若是最优解则插入
				if (j < w[i]) {
					max[i][j] = max[i - 1][j];
				} else {
					// 插入后的情况的值
					int after = max[i - 1][j - w[i]] + v[i];
					// 插入前的值
					int before = max[i - 1][j];
					max[i][j] = after > before ? after : before;
				}
				// 当插入了i + 1个数，且可用容量为j时的最优解
				System.out.println(max[i][j]);
			}
		}
		return max[length - 1][capacity];
	}
}
