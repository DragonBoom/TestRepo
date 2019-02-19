package indi.algorithm;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * 
 * <p>
 * Note: Assume we are dealing with an environment which could only store
 * integers within the 32-bit signed integer range: [−231, 231 − 1]. For the
 * purpose of this problem, assume that your function returns 0 when the
 * reversed integer overflows.
 * 
 * @author Think
 *
 */
class ReverseInteger {

    /**
     * 利用Java自带API实现，比较偷懒的方法，而且实际看也不见得有多么省事。。。
     */
    int solution1(int x) {
        // 需要注意下负数的处理
        boolean isNegetive = false;
        if (x < 0) {
            x = -x;
            isNegetive = true;
        }
        String reverseStr = new StringBuilder().append(x).reverse().toString();
        if (isNegetive) {
            reverseStr = "-" + reverseStr;
        }
        // 当且仅当数值超出2^32的范围时才会抛异常，此时根据题干返回0即可
        try {
            return Integer.parseInt(reverseStr);
        } catch (Throwable e) {
            return 0;
        }
    }

    /**
     * 模仿leetcode上的标准答案
     */
    int solution2(int x) {
        int result = 0;
        boolean negative = false;
        
        // 若x为负数，则取绝对值处理，最后返回时再还原，便于判断是否越界
        /**
         * 越界判断条件本身就很容易越界
         */
        if (x < 0) {
            negative = true;
            x = -x;
        }
       
        while (x != 0) {
            // pop
            int offset = x % 10;
            x /= 10;

            // push
            if (result > (Integer.MAX_VALUE - offset) / 10) {
                return 0;
            }
            result = result * 10 + offset;
        }
        
        if (negative) {
            return -result;
        } else {
            return result;
        }
    }
}
