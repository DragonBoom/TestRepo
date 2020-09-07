package indi.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Given a string s, find the longest palindromic /ˌpælɪn'drɒmɪk/ substring in s. You may assume
 * that the maximum length of s is 1000.
 * 
 * <pre>
 * Example 1:
 * 
 * Input: "babad" Output: "bab" Note: "aba" is also a valid answer.
 * 
 * Example 2:
 * 
 * Input: "cbbd" Output: "bb"
 * 
 * 
 * </pre>
 * 
 * @author wzh@2019.02.21
 *
 */
class LongestPalindromicSubstring {

    /**
     * 思路：遍历数组，对于每个元素，找到以其为中点的最大的回文字符串，取其中最长的一个后返回<br>
     * 这其实就是暴力破解法，很蠢很暴力。。。
     * 
     * Runtime: 8 ms
Memory Usage: 38 MB
     * 
     * <pre>
     * 性质:
     * 对于长度大于等于n的回文数组，若中点为i，则i + n == i - n
     * </pre>
     * <p>最大时间复杂度为 O(n^2)。。。
     */
    String solution1(String s) {
        if (s.length() == 0) {
            return "";
        }

        int max = 1;
        int resultLeft = 0;
        int left = 0;
        int right = 0;
        int len = s.length();
        // find mid
        for (int i = 0; i < len - 1; i++) {
            boolean palindromic = false;
            if (s.charAt(i) == s.charAt(i + 1)) {// 处理中点长度大于1的情况
                palindromic = true;
                // 设中点的左边界为i
                left = i;
                right = i + 1;
                // 找到中点的右边界
                while (right + 1 < len && s.charAt(left) == s.charAt(right + 1)) {
                    right++;
                }
                i = right;// 下一个回文的中点的下标最小为right+1
            } else if (i - 1 >= 0 && i + 1 < len && s.charAt(i - 1) == s.charAt(i + 1)) {// 处理中点长度为1的情况
                palindromic = true;
                left = i - 1;
                right = i + 1;
            }

            if (palindromic) {
                palindromic = false;
                // 一直遍历直至抵达回文/数组的边界
                while (left - 1 >= 0 && right + 1 < len) {
                    if (s.charAt(left - 1) == s.charAt(right + 1)) {
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }

                int curLen = right - left + 1;// + 1是为了保留left位置上的元素
                if (curLen > max) {
                    max = curLen;
                    resultLeft = left;
                }
                // 可以计算下一个回文的中点的起点吗？好像不行，如 aabaabaa
                // 下一个回文的中点最小为当前中点的右边界+1
            }
        }
        return s.substring(resultLeft, resultLeft + max);
    }
    
    String solution2(String s) {
        return null;
    }

    @Test
    void go() {
        System.out.println(solution1("d"));
        System.out.println(solution1("dd"));
        System.out.println(solution1("db"));
        System.out.println(solution1("dddddddd"));
        System.out.println(solution1("aaa"));
        System.out.println(solution1("aba"));
        System.out.println(solution1("aabaa"));
        System.out.println(solution1("abacab"));
        System.out.println(solution1("aabaabaa"));
    }
}
