import java.util.LinkedList;
import java.util.List;

/**
 * 647. 回文子串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 提示：输入的字符串长度不会超过 1000。
 */
public class T0_p647 {
    public static void main(String[] args) {
        String s = "aaaaa";
        Solution solution = new Solution();
        int ans = solution.countSubstrings(s);
        System.out.println(ans);
    }
    static class Solution {
        //用于保存子串
        List<String> ans = new LinkedList<>();
        public int countSubstrings(String s) {
            //至少每个字符串都是回文串
            int sum = s.length();
            //数组含义dp[i][j] 表示s[i]~s[j]是不是回文串
            Boolean[][] dp = new Boolean[s.length()][s.length()];
            //初始化dp数组
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }
            //递推
            for (int i = s.length()-1; i >= 0; i--) {
                for (int j = i+1; j < s.length(); j++) {
                    //也可以赋初值，后更改 dp[i][j] = false;
                    if(s.charAt(i) == s.charAt(j)) {
                        //处理i+1 == j的特殊情况
                        //注意递推公式 dp[i][j]依赖dp[i+1][j-1]
                        // 因此遍历顺序应该保证dp[i+1][j-1]优先dp[i][j]得出结果
                        //即i减j增的顺序
                        if ((i+1 == j) || (dp[i+1][j-1] == true)) {
                            dp[i][j] = true;
                            //添加字串
                            //ans.add(s.substring(i,j));
                            sum++;
                        }
                        else dp[i][j] = false;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
            return sum;
        }
    }
    //双指针法
    static class Solution2 {
        public int countSubstrings(String s) {
            //至少每个字符串都是回文串
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                //奇数
                for (int j = 0; j+i < s.length() && i-j>=0; j++) {
                    if (s.charAt(i-j) != s.charAt(i+j)) {
                        break;
                    } else {
                        sum++;
                    }
                }
                //偶数
                for (int j = 0; j+i+1 < s.length() && i-j>=0; j++) {
                    if (s.charAt(i-j) != s.charAt(i+j+1)) {
                        break;
                    } else {
                        sum++;
                    }
                }
            }
            return sum;
        }
    }
}
