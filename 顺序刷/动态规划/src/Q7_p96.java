/**
 * 96.不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例 1：
 *  1     1        2         3     3
 *   \     \      / \       /     /
 *    3     2    1   3     2     1
 *   /       \            /       \
 *  2         3          1         2
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 *
 * 1 <= n <= 19
 */
public class Q7_p96 {
    public static void main(String[] args) {
        int n = 3;
        Solution solution = new Solution();
        int ans = solution.numTrees(n);
        System.out.println(ans);
    }

    static class Solution {
        public int numTrees(int n) {
            int[] dp = new int[20];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                // 遍历结点作为作为根结点
                for (int j = 1; j <= i; j++) {
                    dp[i] = dp[i] + dp[j-1] * dp[i-j];
                }
            }
            return dp[n];
        }
    }
}
