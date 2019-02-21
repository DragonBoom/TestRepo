package indi.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume
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
     * 
     * record: "abcdeffe" "ded" "dd" "ccffwffdffwffcc"
     */
    public String solution1(String s) {
        if (s.length() == 0) {
            return "";
        } else if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return s.substring(1);
            }
        }

        int max = 0;
        int resultLeft = 0;
        int resultRight = 1;
        int left;
        int right;
        int len = s.length();
        // find mid
        for (int i = 1; i < len - 1; i++) {
            if ((s.charAt(i + 1) == s.charAt(i - 1)) || s.charAt(i) == s.charAt(i + 1)) {
                left = s.charAt(i - 1) == s.charAt(i + 1) ? i - 1 : i;
                right = i + 1;
                while (left >= 0 && right < len) {
                    left--;
                    right++;

                    if (!(left >= 0 && right < len && s.charAt(left) == s.charAt(right))) {
                        break;
                    }

                }
                // restore
                left++;
                right--;

                int tmp = right - left + 1;// cur len
                if (tmp > max) {
                    max = tmp;
                    resultLeft = left;
                    resultRight = right + 1;// for substring
                }
                i = Math.max(i, left);//
            }
        }
        return s.substring(resultLeft, resultRight);
    }

    @Test
    void go() {
        System.out.println(solution1("ccd"));
    }
}
