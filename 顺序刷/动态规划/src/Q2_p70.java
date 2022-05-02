/**
 * 70. 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1 阶 + 1 阶
 * 2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1 阶 + 1 阶 + 1 阶
 * 1 阶 + 2 阶
 * 2 阶 + 1 阶
 */
public class Q2_p70 {
    public static void main(String[] args) {
        int n = 3;
        Solution solution = new Solution();
        int ans = solution.climbStairs(n);
        System.out.println(ans);
    }

    static class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[46];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
                // System.out.println(dp[i]);
            }
            return dp[n];
        }
    }
}