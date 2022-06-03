/**
 * 62.不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 *
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 2, n = 3
 * 输出：3
 * 解释： 从左上角开始，总共有 3 条路径可以到达右下角。
 *
 * 向右 -> 向右 -> 向下
 * 向右 -> 向下 -> 向右
 * 向下 -> 向右 -> 向右
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class Q4_p62 {
    public static void main(String[] args) {
        int m = 3, n = 7;
        Solution solution = new Solution();
        int ans = solution.uniquePaths(m, n);
        System.out.println(ans);
    }

    static class Solution {
        public int uniquePaths(int m, int n) {
            // C_3^7
            int[][] dp = new int[101][101];
            //初始化，数组外另加了一圈0 base
            for (int i = 0; i < dp.length; i++) {
                dp[0][i] = 0;
            }
            for (int j = 0; j < dp.length; j++) {
                dp[j][0] = 0;
            }
            dp[0][1] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[j][i] = dp[j][i-1] + dp[j-1][i];
                }
            }
            return dp[m][n];
        }
    }
}