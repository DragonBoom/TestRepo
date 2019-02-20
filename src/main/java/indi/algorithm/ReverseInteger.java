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
     * 
     * <p>
     * 思路: 倒转操作容易靠入栈/出栈实现，对整数a来说，出栈的数值即是offset = a % 10，出栈是 a /= 10，而入栈则是 a = a * 10 + offset
     * 
     * @author DragonBoom@2019.02.19 update finally (hope so)
     */
    int solution2(int x) {
        /**
         * 越界判断条件本身就可能越界 ！
         * 
         */
        int result = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;

        while (x != 0) {
            // pop
            int offset = x % 10;
            x /= 10;

            // push
            if (result > max - offset / 10 || result < min - offset / 10) {// 不能用 (Integer.MAX_VALUE - offset) /
                                                                           // 10，可能会越界
                return 0;
            }
            result = result * 10 + offset;
        }

        return result;
    }
}
