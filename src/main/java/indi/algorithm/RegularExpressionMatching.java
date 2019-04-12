package indi.algorithm;

import org.junit.jupiter.api.Test;

/**
 * <a href=
 * "https://leetcode.com/problems/regular-expression-matching/">LeetCode</a>
 * 
 * @author DragonBoom
 *
 */
public class RegularExpressionMatching {

	/**
	 * 
	 * DP - dynamic programming
	 * <p>
	 * a.*dafw = abcddadadadadafw ?
	 * <p>
	 * a.*ab.* - ababababababababa
	 * <p>
	 * a.*ab* - ababa
	 * <p>
	 * a*aab - aaaaaab
	 * <p>
	 * a**b - ab
	 * <p>
	 * a*b - b
	 * <p>
	 * .* 匹配在确保其后元素都匹配到的前提下的尽可能长的元素。。
	 * <p>
	 * 不匹配条件 ?<br>
	 * 记录每个*的匹配次数，从最大开始尝试，一直往回递减? Wrong 每个*的匹配次数可能会影响到下一个*的最佳匹配次数，即每个*的匹配次数都不是相互独立的
	 */
	boolean wrongSolution1(String s, String p) {
		int[][] cache = new int[s.length()][p.length()];
		// init...
		for (int i = 0; i < cache.length; i++) {
			int[] js = cache[i];
			for (int j = 0; j < js.length; j++) {
				cache[i][j] = -1;
			}
		}
		
		char lastMatch = ' ';
		int i = 0;
		int j = 0;
		while (i < s.length() && j < p.length()) {
			if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
				i++;
				j++;
				lastMatch = p.charAt(i);
			} else {
				if (p.charAt(j) == '*') {
					// match longest at first
					if (cache[i][j] == -1) {// means first
						cache[i][j] = 0;
						int oi = i;
						while (i < s.length() && s.charAt(i) == lastMatch) {
							i++;
							cache[oi][j]++;
						}
					} else if (cache[i][j] > 1) {
						cache[i][j]--;
						i += cache[i][j];
					}
					j++;
				} else {
					return false;
				}
				
				if (j + 1 < p.length() && p.charAt(j + 1) == '*') {// match 0 times...
					j += 2;
					// lastMatch notChange...
					continue;
				}
				return false;
			}
			if (i == s.length() && j == p.length()) {
				return true;// true only if both at end...
			} else if (i == s.length() || j == s.length()) {
				// backward
				for (int m = 0; m < cache.length; m++) {
					int[] js = cache[m];
					for (int n = 0; n < js.length; n++) {
						if (cache[m][m] > 0) {
							
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * solution 1: recursion
	 * 
	 * Time Complexity: 递归次数 * 每次递归最大的复杂度
	 */
    boolean isMatch(String s, String p) {
        System.out.println(s + " | " + p);
        
        if (p.length() == 0) {// final case
            return s.isEmpty();
        }
        // if first match
        boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        // ab
        // .*
        // a*
        if (p.length() > 1 && p.charAt(1) == '*') {
            // 匹配1次或0次
            return (firstMatch && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        } else {
            // no * influence
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    @Test
    void run() {
        boolean match = isMatch("aaa", "a*a");
        System.out.println(match);
    }
}
