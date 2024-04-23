/**
 * 53. 最大子序和
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例: 输入: [-2,1,-3,4,-1,2,1,-5,4] 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class Q3_53 {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        Solution solution = new Solution();
        int ans = solution.maxSubArray(nums);
        System.out.println(ans);
    }
    static class Solution {
        public int maxSubArray(int[] nums) {
            if (nums.length == 1){
                return nums[0];
            }
            int sumMax = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++){
                sum += nums[i];
                //取区间累计的最大值（相当于不断确定最大子序终止位置）
                sumMax = Math.max(sumMax, sum);
                if (sum <= 0){
                    // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                    sum = 0;
                }
            }
            return sumMax;
        }
    }
    //类似dp的思想，i之前的最大和大于0就加上，小于零就忽略
    static class Solution2 {
        public int maxSubArray(int[] nums) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (sum >= 0) {
                    sum = sum + nums[i];
                } else {
                    sum = nums[i];
                }
                max = Math.max(max, sum);
            }
            return max;
        }
    }
}
