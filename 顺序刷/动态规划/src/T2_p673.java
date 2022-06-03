/**
 * 673.最长递增子序列的个数
 * https://leetcode.cn/problems/number-of-longest-increasing-subsequence/
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 */
public class T2_p673 {
    public static void main(String[] args) {
        int[] nums = {1,2,4,3,5,4,7,2};
        Solution solution = new Solution();
        int ans = solution.findNumberOfLIS(nums);
        System.out.println(ans);
    }
    static class Solution {
        public int findNumberOfLIS(int[] nums) {
            int ans = 0;
            //dp表示以当前位置为结尾的长度
            int[] dp = new int[nums.length];
            int[] count = new int[nums.length];
            //注意初始化为1
            int max = 1;
            //初始化dp
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                count[i] = 1;
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            //count[i] = 1;
                            //注意这里记录的个数，所以不能直接初始化为1而是，用j的个数
                            count[i] = count[j];
                            if (dp[i] > max) max = dp[i];
                        } else if (dp[j] + 1 == dp[i]) {
                            count[i] += count[j];
                        }
                    }
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (max == dp[i]) ans += count[i];
            }
            return ans;
        }
    }
}
