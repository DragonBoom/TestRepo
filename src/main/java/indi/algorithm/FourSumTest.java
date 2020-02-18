package indi.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wzh
 * @since 2020.02.13
 */
public class FourSumTest {

    class Solution {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new LinkedList<>();
            if (nums == null || nums.length < 4) {
                return result;
            }
            // sort
            Arrays.sort(nums);// 从小到大地排序
            
            // recursion
            // 存在问题，一个起点，可能有多个组合！
            /*
            * 这个循环能否移到递归方法内？不好实现，因为第一步与之后的步骤相当不同，第一次是负责同级的遍历，而之后的递归是负责下级的遍历
            */
            List<Integer> tempList = new ArrayList<>(4);
            for (int i = 0; i< nums.length; i++) {
                // 当在同一深度内遍历时，跳过相同的值
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                tempList = go(nums, i, 4, 0, target, tempList, result);// 注意这里返回的tempList与一开始传入的tempList不是同一个对象
            }
            return result;
        }
        
        /**
        * 这个递归的原理，就是简单的暴力破解，借助递归，将所有可能都遍历一遍。。。
        * <p>递归是深度优先，采用栈的结构出入结点，一直维护着一个栈的List，每次循环，在找到目标队列后将List添加至结果中，然后再深复制一个新List继续出栈入栈。对每次递归，将遍历其下级深度的所有可能，最后返回时将不包含当前结点。
        * <p>由于使用了暴力破解，时间复杂度应该是 O(n^4)
        */
        // O(n^4)
        private List<Integer> go(int[] nums, int begin, int depth, int sum, int target, List<Integer> tempList, List<List<Integer>> result) {
            sum += nums[begin];
            depth--;
            // tempList.add(nums[begin]);// 添加当前节点 -- 其实应该没有必要在这里添加到list的
            // System.out.println(tempList + " v=" + nums[begin] + "  sum=" + sum + " dep=" + depth);
            
            if (depth == 0 && sum == target) {
                // found it !!!
                tempList.add(nums[begin]);
                result.add(tempList);
                tempList = new ArrayList<>(tempList);// 复制上一次的结果，可直接用构造函数，是深复制
                tempList.remove(tempList.size() - 1);// 保证返回到上一级递归时，没有添加当前节点
                return tempList;
            }
            // sum > target 这一条件，当新增的值是负数时无效
            // 这里应该存在优化的空间。。。
            if(depth == 0 // 隐藏条件 sum != target 
            || nums.length - begin - 1 < depth // 剩余深度不足
            || (nums[begin + 1] >= 0 && sum > target)) {// sum只大不小，永远无法获得target
                // never found
                return tempList;
            }
            tempList.add(nums[begin]);
            // 1. 当循环最后一个结点找到目标序列时，可以结束循环
            // 2. 对于下一级结点的循环，要跳过相同值（下一级结点只有两种情况：取相同值的下一个结点，或取不同值的其他结点，这样就会遍历出所有的情况）
            
            for (int i = begin + 1; i < nums.length; i++) {
                // 当在同一深度内遍历时，跳过相同的值
                if (i > begin + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                tempList = go(nums, i, depth, sum, target, tempList, result);
           
            }
            tempList.remove(tempList.size() - 1);// 移除当前节点，保证返回到上一级递归时，可以添加其他同级的结点，即处于没有添加当前结点的状态
            return tempList;
        }
    }
    
    // O(n^(lg2+1))
    class Solution2 {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            // 思路2：模仿3sum，通过移动左右游标，而不是嵌套循环来实现
            // 移动左右游标的原理是通过最大、最小值的移动来判断与目标值的差距并预测下一步的行动，从而通过一次遍历实现两次遍历的效果。因此，一次只能移动两个游标
            // 对于这个问题，可转化为1 + 3sum

            List<List<Integer>> result = new ArrayList<>();

            if (nums == null || nums.length < 4) {
                return result;
            }

            // sort
            Arrays.sort(nums);

            int len = nums.length;
            for(int i = 0; i + 3 < len; i++) {
                // skip duplicate (depth = 4)
                // 在该深度内，只有1个游标在遍历，因此1个值只需要取第1次（注意只取1次这个说法不够准确，且起点为该深度的起点，不一定是0）
                while (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                    i++;
                }
                // calc 3 sum
                threeSum(i + 1, nums, target - nums[i], result);
            }
            return result;
        }

        void threeSum(int begin, int[] nums, int target, List<List<Integer>> result) {
            int prevIndex = begin - 1;
            int len = nums.length;
            for (int i = begin; i + 2 < len; i++) {
                // skip dulicate (depth = 3)
                // 在该深度内，只有1个游标在遍历，因此1个值只需要取第1次（注意只取1次这个说法不够准确，且起点为该深度的起点，不一定是0）
                while (i - 1 > prevIndex && i + 2 < len && nums[i - 1] == nums[i]) {
                    i++;
                }
                int low = i + 1;
                int high = len - 1;

                // move low or high
                while (low < high) {
                    // System.out.println(prevIndex + " " + nums[prevIndex] + " " + nums[i] + " " + nums[low] + " " + nums[high]);
                    int sum = nums[i] + nums[low] + nums[high];
                    if (sum == target) {
                        List<Integer> singleResult = new ArrayList<>();
                        singleResult.add(nums[prevIndex]);
                        singleResult.add(nums[i]);
                        singleResult.add(nums[low]);
                        singleResult.add(nums[high]);
                        result.add(singleResult);
                        // 移动low与high至更下、更大的值
                        while (low + 1 < len && nums[low + 1] == nums[low]) {
                            low++;
                        }
                        while (high - 1 > prevIndex && nums[high] - 1 == nums[high]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (sum < target) {
                        // 移动low至更大的值
                        while (low + 1 < len && nums[low + 1] == nums[low]) {
                            low++;
                        }
                        low++;

                    } else if (sum > target) {
                        // 移动high至更小的值
                        while (high - 1 > prevIndex && nums[high] - 1 == nums[high]) {
                            high--;
                        }
                        high--;
                    }
                }
            }
        }


    }
}
