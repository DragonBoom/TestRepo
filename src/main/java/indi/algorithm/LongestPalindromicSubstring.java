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
     * 思路：遍历数组，对于每个元素，找到以其或其右边为中点的最大的回文字符串，取其中最大的一个后返回<br>
     * 这其实就是暴力破解法。。。
     * 
     * <p>最大时间复杂度为 O(n^2)。。。
     */
    public String solution1(String s) {
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
            if (s.charAt(i) == s.charAt(i + 1)) {// aa
                palindromic = true;
                left = i;
                right = i + 1;
                while (right + 1 < len && s.charAt(left) == s.charAt(right + 1)) {
                    right++;
                }
            } else if (i + 2 < len && s.charAt(i) == s.charAt(i + 2)) {// aba
                palindromic = true;
                left = i;
                right = i + 2;
            }

            if (palindromic) {
                palindromic = false;
                while (left - 1 >= 0 && right + 1 < len) {
                    if (s.charAt(left - 1) == s.charAt(right + 1)) {
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }

                int curLen = right - left + 1;
                if (curLen > max) {
                    max = curLen;
                    resultLeft = left;
                }
            }
        }
        return s.substring(resultLeft, resultLeft + max);
    }

    @Test
    void go() {
        System.out.println(solution1("d"));
        System.out.println(solution1("dd"));
        System.out.println(solution1("db"));
        System.out.println(solution1("dddddddd"));
        System.out.println(solution1("aaa"));
        System.out.println(solution1("aabaa"));
    }
}
