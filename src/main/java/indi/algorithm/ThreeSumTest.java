package indi.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ThreeSumTest {
    
    @Test
    void go() {
        Solution solution = new Solution();
        int[] testCase1 = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        List<List<Integer>> result = solution.threeSum(testCase1);
        // 测试结果只能到leetcode线上测试..
    }

    
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            // validate
            if (nums == null || nums.length < 3) {
                return result;
            }        
            // sort
            Arrays.sort(nums);
            
            int len = nums.length;
            // for every index, search aim
            for (int i = 0; i < len - 2; i++) {
                int aim = -nums[i];
                // loop
                // 若使用for循环，需要再嵌套两个循环
                // 使用从最小、最大两端遍历，则只需要再嵌套一次循环
                
                int low = i + 1;
                int high = len - 1;
                
                // 根据结果决定移动哪端的下标，每次移动都由上次结果决定，因此仅需一次循环
                while (low < high) {
                    // must skip duplicate before ++/--
                    // 在移动左右下标前，需要先跳过元素，否则移动下标无法做到切换元素的作用
                    // 并且，仅能对需要移动的下标跳过重复元素，因为当只需要移动某一边的结点时，无法判断该结点是否可以取与另一边（另一个结点）相同的值，若也移动了另一边，会使需要移动的结点永远无法获取到与另一个结点相同的值
                    int actual = nums[low] + nums[high];
                    if (actual == aim) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[low]);
                        list.add(nums[high]);
                        result.add(list);
                        
                        // skip duplicate
                        while (low + 1 < high && nums[low + 1] == nums[low]) {
                            low++;
                        }

                        while (high - 1 > low && nums[high - 1] == nums[high]) {
                            high--;
                        }
                        
                        low++;
                        high--;
                    } else if (actual < aim) {
                        // skip duplicate
                        while (low + 1 < high && nums[low + 1] == nums[low]) {
                            low++;
                        }
                        
                        low++;// means actual increase
                    } else {
                        // skip duplicate
                        while (high - 1 > low && nums[high - 1] == nums[high]) {
                            high--;
                        }
                        high--;// means actual decrease;
                    }

                }
                
                // min element skip duplicate, to be other value
                while (i + 1 < len - 2 && nums[i + 1] == nums[i]) {
                    i++;
                }
                
            }
            return result;
        }
        
    }
}
