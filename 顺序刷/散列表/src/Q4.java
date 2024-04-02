/**
 * 1. 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 *
 * 所以返回 [0, 1]
 */

import java.util.*;

public class Q4 {
    public static void main(String[] args) {
        int n = 19;
        int[] nums = {2,7,11,15};
        int target = 9;
        Solution solution = new Solution();
        int[] ans = solution.twoSum(nums, target);
        System.out.println(ans);
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int ans[] = new int[2];
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++) {
                if(map.containsKey(target - nums[i])) {
                    ans[1] = i;
                    ans[0] = map.get(target - nums[i]);
                    //这里退出即可，节省内存和时间
                    return ans;
                }
                map.put(nums[i], i);
            }
            return ans;
        }
    }
}
