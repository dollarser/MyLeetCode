import java.util.*;

/**
 * 第15题. 三数之和
 * https://leetcode-cn.com/problems/3sum/
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意： 答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 该题难点在于去重，因为如果数组中有重复的元素，而重复的元素又满足条件，会得到重复的结果
 * 通过排序可以很好的方面后面去重
 */
public class Q6 {
    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);

    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            return null;
        }
    }
}