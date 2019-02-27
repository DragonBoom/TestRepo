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
        return len % 2 == 0 ? average(getEleAtI(len / 2, a, b), getEleAtI(len / 2 - 1, a, b))
                : (double) getEleAtI(len / 2, a, b);
    }

    private int getEleAtI(int i, int[] a, int[] b) {
        if (i <= a.length - 1) {
            return a[i];
        } else {
            return b[i - a.length];
        }
    }

    private void printInts(int[] ints) {
        System.out.println(Arrays.toString(ints));
    }

    public double solution2(int[] nums1, int[] nums2) {
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
        // tree
        int len = largeNums.length + smallNums.length;
        boolean isDoubleMid = len % 2 == 0;
        int midLen = len / 2 + 1;

        int count = 0;
        int last = -1;
        int iAtSmall = 0;
        int iAtLarge = 0;
        while (count < midLen) {
            count++;
            int cur = -1;
            if (iAtLarge >= largeNums.length) {
                cur = smallNums[iAtSmall];
                iAtSmall++;
            } else if (iAtSmall >= smallNums.length) {
                cur = largeNums[iAtLarge];
                iAtLarge++;
            } else if (largeNums[iAtLarge] <= smallNums[iAtSmall]) {
                cur = largeNums[iAtLarge];
                iAtLarge++;
            } else {
                cur = smallNums[iAtSmall];
                iAtSmall++;
            }
            if (count == midLen) {
                if (isDoubleMid) {
                    return average(last, cur);
                } else {
                    return cur;
                }
            }
            last = cur;
        }
        return 0;
    }

    @Test
    void go() {
        int[] a = new int[] { 1, 2, 3 };
        int[] b = new int[] { 1, 2, 2 };
        double findMedianSortedArrays = this.solution2(a, b);
        System.out.println("result: " + findMedianSortedArrays);
    }
}
