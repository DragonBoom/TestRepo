package indi.algorithm;

import java.util.ArrayList;

import org.junit.Test;

public class Subset {

	@Test
	public void getSubset() {
		int[] set = { 1, 2, 7, 9, 100, 78 };
		ArrayList<Integer[]> iss = getSubset(set);
		for (Integer[] is: iss) {
			for (Integer i : is) {
				System.out.print(i + " ");
			}
			System.out.println(" ");
		}
		// subset num
		System.out.println("-- " + iss.size() + " --");
	}

	private ArrayList<Integer[]> getSubset(int[] set) {
		ArrayList<Integer[]> iss = new ArrayList<Integer[]>();
		ArrayList<Integer> is = new ArrayList<Integer>();
		int length = set.length;
		int end = (1 << length);
		for (int mark = 0; mark < end; mark++) {
			for (int i = length - 1; i >= 0; i--) {
				// == 1 ?
				if (((1 << i) & mark) != 0) {
					is.add(set[i]);
				}
			}
			Integer[] o = new Integer[is.size()];
			is.toArray(o);
			iss.add(o);
			is.clear();
		}
		return iss;
	}
}
