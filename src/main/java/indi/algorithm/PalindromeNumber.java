package indi.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Determine whether an integer is a palindrome (/ˈpælɪndrəʊm/ 回文). An integer
 * is a palindrome when it reads the same backward as forward.
 * 
 * <p>
 * 124 -> 421
 * 
 * @author DragonBoom
 *
 */
public class PalindromeNumber {

    /**
     * 思路：反转整数后进行比较
     * 
     * @param x
     * @return
     */
    public boolean solution1(int x) {
        if (x < 0) {
            return false;
        } else if (x == reverseInt(x)) {
            return true;
        } else {
            return false;
        }
    }

    private int reverseInt(int x) {
        int result = 0;
        int offset = 0;
        while (x != 0) {
            // pop
            offset = x % 10;
            x /= 10;

            // push
            result = result * 10 + offset;

        }
        return result;
    }

    /**
     * 思路：倒转一半
     * 
     * <p>转化题目：题目可以理解为判断1个数的前半部分是否等于后半部分
     * <p>性质：对a,b分别是s的前/后半部分，若s的前半部分等于后半部分，则a == b || a = b / 10 || b = a / 10，
     * 后面两个判断适用于s的位数为奇数的情况
     * 
     * 时间复杂度 O(lg(n))  We divided the input by 10 for every iteration, so the time complexity is O(\log_{10}(n))O(log 
10  (by leetcode)
 (n))
     * 空间复杂度 O(1)
     * 
     * @param x
     * @return
     */
    public boolean solution2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {// 排除必然不成立的情况
            return false;
        }
        int result = 0;// 代表x的前半部分
        while (x > result) {
            // push
            result = result * 10 + x % 10;

            // pop
            x /= 10;
        }
        // 若result退1位与x相同，则说明后半部分长度为前半部分-1，符合奇数长度下前半部分与后半部分相同的情况
        return x == result || x == result / 10;
    }

    @Test
    void go() {
        System.out.println(reverseInt(-121));
    }
}
