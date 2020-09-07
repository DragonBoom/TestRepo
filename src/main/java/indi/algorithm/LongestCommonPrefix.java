package indi.algorithm;

public class LongestCommonPrefix {
    private static final String EMPTY = "";
    
    /**
     * 暴力破解法<br>
     * 最大时间复杂度为: O(n) n取元素个数
     * <pre>
     * Runtime: 4ms, faster than 99.85%
     * Memory Usage: 38.3MB, less than 25.93%
     * </pre> 
     */
    public String solution1(String[] strs) {
        if (strs.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        char lastChar = ' ';
        for (int i = 0;; i++) {
            if (i == strs[0].length()) {
                return sb.toString();
            } else {
                lastChar = strs[0].charAt(i);
            }
            
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length()) {
                    return sb.toString();
                }
                if (strs[j].charAt(i) != lastChar) {
                    return sb.toString();
                }
            }
            sb.append(lastChar);
        }
    }
}
