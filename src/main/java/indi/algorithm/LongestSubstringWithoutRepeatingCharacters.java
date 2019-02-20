package indi.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * @author DragonBoom@2019.02.19
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
    
    @Test
    void go() {
        int solution1 = solution1("abcdefgefabcdefghijklmnf");
        System.out.println(solution1);
    }

    /**
     * 思路：需要走一步算一步
     * <p>
     * 即每遍历一个数，就要更新其前面所有有效的数
     * 
     * O(nlogn)
     */
    public int solution1(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int last = 0;
        int[] store = new int[len];
        store[0] = 1;
        for (int i = 1; i < len; i++) {
            store[i] = 1;
            char c = s.charAt(i);
            for (int j = i - 1; j >= last; j--) {
                if (s.charAt(j) != c) {
                    store[j]++;
                } else {
                    last = j;
                    break;
                }
            }
        }
        // find max
        int result = 1;
        for (int i : store) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }
}
