package indi.algorithm;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MedianOfTwoSortedArrays {

    public double solution1(int[] nums1, int[] nums2) {
        int[] largeNums = nums1.length > nums2.length ? nums1 : nums2;
        // if empty
        if (nums1.length == 0 || nums2.length == 0) {
            return getMid(largeNums);
        }
        // if lucky
        int[] smallNums = nums1.length > nums2.length ? nums2 : nums1;
        if (smallNums[0] >= largeNums[largeNums.length - 1]) {
            return getMidBetweenOrdered(largeNums, smallNums);
        } else if (largeNums[0] >= smallNums[smallNums.length - 1]) {
            return getMidBetweenOrdered(smallNums, largeNums);
        }
        // if not lucky
        // aim: build two full ordered arrays
        for (int i = 0; i < smallNums.length; i++) {
            if (smallNums[i] > largeNums[0]) {
                // swap
                swap(smallNums, i, largeNums, 0);
                // 单次冒泡
                if (largeNums.length == 1) {
                    break;
                }
                for (int j = 0; j < largeNums.length - 1; j++) {
                    if (largeNums[j] > largeNums[j + 1]) {
                        // swap
                        swap(largeNums, j, largeNums, j + 1);
                    } else {
                        break;
                    }
                }
            }
        }

        return getMidBetweenOrdered(smallNums, largeNums);
    }

    private void swap(int[] a, int aPosition, int[] b, int bPosition) {
        int tmp = a[aPosition];
        a[aPosition] = b[bPosition];
        b[bPosition] = tmp;
        System.out.println("==");
        printInts(a);
        printInts(b);
        System.out.println("==");
    }

    private double getMid(int[] ints) {
        return (double) ints.length % 2 == 0 ? average(ints[ints.length / 2], ints[ints.length / 2 - 1])
                : ints[ints.length / 2];
    }

    private double average(int a, int b) {
        return ((double) (a + b)) / 2;
    }

    private double getMidBetweenOrdered(int[] a, int[] b) {
        int len = a.length + b.length;
        /*
         * a + n = x <=> n = x - a => n 的所有元素表示x到a的距离
         * 
         * 5 - 2 <=> [1 2 3 4 5] - [1 2] = [1 1 1] <=> 3 => 第二个元素会被舍弃掉!!
         * 
         * 5 + 2 <=> [1 1 1 1 1] + [1 1] = [1 1 1 1 1 [1 1]] <=> 7
         * 
         * 5 / 2 => [1 1 1 1 1] / 2 = [1 1 1/2] [1/2 1 1]
         * 
         * 4 / 2 => [1 1 1 1] / 2 = [1 1] [1 1]
         * 
         * 对于长为n的数组，若n为偶数，则中数为第(0 + n / 2)与第((0 + n / 2) + 1)个元素的平均数，即下标为(n / 2 - 1)与 (n
         * / 2)的元素的平均数 若n为奇数，则中数为第(0 + n / 2 + 1)个元素，即下标为(n / 2)的元素
         */

        if (a.length == b.length) {
            return average(a[a.length - 1], b[0]);
        } else if (a.length > b.length) {
            return (a.length + b.length) % 2 == 0 ? average(a[len / 2 - 1], a[len / 2]) : a[len / 2];
        } else {
            return (a.length + b.length) % 2 == 0 ? average(b[len / 2 - 1 - a.length], b[len / 2 - a.length])
                    : b[len / 2 - a.length];
        }
    }

    private void printInts(int[] ints) {
        System.out.println(Arrays.toString(ints));
    }

    public double solution2(int[] nums1, int[] nums2) {
        /**
         * 去掉【对不必遍历的情况的特殊处理】，反而更快了，占用内存也更低了？？？ TODO
         */
//        // if empty
//        if (nums1.length == 0) {
//            return getMid(nums2);
//        } else if (nums2.length == 0) {
//            return getMid(nums1);
//        }
//
//        // if lucky
//        if (nums1[nums1.length - 1] <= nums2[0]) {
//            return getMidBetweenOrdered(nums1, nums2);
//        } else if (nums2[nums2.length - 1] <= nums1[0]) {
//            return getMidBetweenOrdered(nums2, nums1);
//        }

        // if not lucky
        // loop (m + n) / 2

        int i1 = 0;// iterator at nums1
        int i2 = 0;// iterator at nums2
        int len = nums1.length + nums2.length;
        int finalMid = len / 2 + 1;// len % 2 == 0 ? len / 2 : len / 2;// finalMid index

        int last = 0;
        while (true) {
            // if i1 / i2 go end
            if (i1 == nums1.length) {
                if (len % 2 == 0) {
                    if (i1 + i2 + 1 == finalMid) {
                        return average(nums1[i1 - 1], nums2[i2]);
                    } else {
                        i2 = finalMid - nums1.length - 1;// update i2
                        return average(nums2[i2 - 1], nums2[i2]);
                    }
                } else {
                    return nums2[finalMid - nums1.length - 1];
                }
            } else if (i2 == nums2.length) {
                if (len % 2 == 0) {
                    if (i1 + i2 + 1 == finalMid) {
                        return average(nums2[i2 - 1], nums1[i1]);
                    } else {
                        i1 = finalMid - nums2.length - 1;// update i1
                        return average(nums1[i1 - 1], nums1[i1]);
                    }
                } else {
                    return nums1[finalMid - nums2.length - 1];
                }
            }
            // pop lastest
            int cur = 0;
            if (nums1[i1] <= nums2[i2]) {
                cur = nums1[i1];
                i1++;// pop
            } else if (nums2[i2] < nums1[i1]) {// could optimise ?
                cur = nums2[i2];
                i2++;// pop
            }
            //
            if (i1 + i2 == finalMid) {
                if (len % 2 == 0) {
                    return average(last, cur);
                } else {
                    return cur;
                }
            }
            last = cur;// go next and refresh last
        }
    }

    /**
     * leetcode 的标准答案，时间复杂度为 O(log(min(m,n)))
     * 
     * <p>
     * <a href=
     * "https://leetcode.com/problems/median-of-two-sorted-arrays/solution/">link</a>
     * 
     */
    public double solution3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        // 利用分治法查找满足条件的元素
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    @Test
    void go() {
        int[] a = new int[] { 1, 2 };
        int[] b = new int[] { -1, 3 };
        double findMedianSortedArrays = this.solution2(a, b);
        System.out.println("result: " + findMedianSortedArrays);
    }

}
