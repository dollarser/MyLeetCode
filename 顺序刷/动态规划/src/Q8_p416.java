/**
 * 416. 分割等和子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 * 题目难易：中等
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
 *
 * 示例 1: 输入: [1, 5, 11, 5] 输出: true 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例 2: 输入: [1, 2, 3, 5] 输出: false 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */

/**
 * 思路：
 * 爬楼梯角度，数组是步长，数组和的一半是楼顶，问能否到楼顶
 * 0-1背包角度：数组长即物品数，数值即重量，数组和的大小即背包的容量
 * 就是最大重量等于容量
 * 重点
 */
public class Q8_p416 {public static void main(String[] args) {
    int[] nums = {1,5,11,5};
    Solution solution = new Solution();
    boolean ans = solution.canPartition(nums);
    System.out.println(ans);
}

    static class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int a : nums) {
                sum += a;
            }
            //判断是否能平分
            if (sum % 2 != 0) return false;
            else sum = sum / 2;

            int[][] dp = new int[nums.length][sum+1];
            //初始化dp数组，放入第0个物体后的状态
            for (int j = nums[0]; j <= sum; j++) {
                dp[0][j] = nums[0];
            }
            //递推公式
            for (int i = 1; i < nums.length; i++) {
                for (int j = 1; j <= sum; j++) {
                    //背包容量可以容纳nums[i]
                    if (j >= nums[i]) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[nums.length-1][sum] == sum;
        }
    }
}