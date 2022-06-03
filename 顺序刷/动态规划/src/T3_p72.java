/**
 * 72. 编辑距离
 * https://leetcode.cn/problems/edit-distance/
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1： 输入：word1 = "horse", word2 = "ros" 输出：3 解释： horse -> rorse (将 'h' 替换为 'r') rorse -> rose (删除 'r') rose -> ros (删除 'e')
 *
 * 示例 2： 输入：word1 = "intention", word2 = "execution" 输出：5 解释： intention -> inention (删除 't') inention -> enention (将 'i' 替换为 'e') enention -> exention (将 'n' 替换为 'x') exention -> exection (将 'n' 替换为 'c') exection -> execution (插入 'u')
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */

/**
 * 初始化：
 * 递推关系：
 */
public class T3_p72 {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        Solution solution = new Solution();
        int ans = solution.minDistance(word1, word2);
        System.out.println(ans);
    }
    static class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            //dp[i][j]含义 字符串1中下标i-1结尾的子串，到字符串2中下标j-1结尾的子串的编辑距离
            //这样表示的原因是让dp数组的0下标表示空字符串
            int[][] dp = new int[m + 1][n + 1];

            // 初始化
            for (int i = 1; i <= m; i++) {
                dp[i][0] =  i;
            }
            for (int j = 1; j <= n; j++) {
                dp[0][j] = j;
            }
            //dp数组有效位从1开始
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 因为dp数组有效位从1开始
                    // 所以当前遍历到的字符串的位置为i-1 | j-1
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        //改：dp[i - 1][j - 1] + 1
                        //删1：dp[i][j - 1] + 1
                        //删2：dp[i - 1][j] + 1
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
            }
            return dp[m][n];
        }
    }
}
