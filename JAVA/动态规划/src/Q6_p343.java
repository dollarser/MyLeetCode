/**
 * 343. 整数拆分
 * https://leetcode-cn.com/problems/integer-break/
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
/**
 * 思路：和为n，则拆分的数字范围为1~n-1,相当于爬楼梯步长为1~n-1, 求到楼顶的方法
 * dp[i] = dp[i-j] * i-j; j=1~i
 */

public class Q6_p343 {
    public static void main(String[] args) {
        //int n = 2;
        int n = 10;
        Solution solution = new Solution();
        int ans = solution.integerBreak(n);
        System.out.println(ans);
    }

    static class Solution {
        public int integerBreak(int n) {
            int[] dp = new int[59];
            dp[1] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    int temp = Math.max(dp[j], j) * (i-j);
                    if (dp[i] < temp) {
                        dp[i] = temp;
                    }
                }
            }
            return dp[n];
        }
    }
}