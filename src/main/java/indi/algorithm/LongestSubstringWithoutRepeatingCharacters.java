package indi.algorithm;

import java.util.HashSet;

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
    
    /**
     * 思路：每次遍历时，计算到下标i为止，以其前面每个元素j为起点的最大无冲突子集的长度， store[j] 即以j为起点的最大无冲突子集的长度<br>
     * 性质：
     * <ul>
     * <li>若发生了冲突，则下一个无冲突序列必然从冲突的第一个元素的下一位开始</li>
     * </ul>
     * <p>
     * 这种解法性能上比暴力破解强一些，时间复杂度应该是 O(n logn)<br> 
     * runtime: 19 ms 92.67% <br> 
     * memory: 37.2 MB 58.87%
     *
     */
    int solution2(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int last = 0;
        int[] store = new int[len];
        store[0] = 1;
        int result = 1;
        for (int i = 1; i < len; i++) {
            store[i] = 1;// init at i
            for (int j = i - 1; j >= last; j--) {
                if (s.charAt(j) != s.charAt(i)) {
                    store[j]++;// 代表以j为起点的最大无冲突子集+1
                    if (store[j] > result) {
                        result = store[j];
                    }
                } else {
                    last = j + 1;// last = j;亦不会影响结果，因为若从j开始的序列的长度至多和从j+1开始的序列长度一样
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * HashSet 滑动窗口解法 因为使用了集合，远不如solution2....
     * <p>
     * runtime: 28ms 62.41%<br>
     * memory: 40mb 14.10%
     */
    int solution3(String s) {
        int result = 0;
        int begin = 0;
        int end = 0;
        int len = s.length();
        
        HashSet<Character> set = new HashSet<>();
        
        while (begin < len && end < len) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(begin));
                begin++;
            } else {
                set.add(s.charAt(end));
                result = Math.max(result, end - begin);
                end++;
            }
        }
        
        return result;
    }
}
