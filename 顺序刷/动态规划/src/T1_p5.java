/**
 * 5.最长回文子串
 * 力扣题目链接
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 */
public class T1_p5 {
    public static void main(String[] args) {
        String s = "babad";
        Solution solution = new Solution();
        String ans = solution.longestPalindrome(s);
        System.out.println(ans);
    }

    static class Solution {
        public String longestPalindrome(String s) {
            int max = 0;
            int[] maxIndex = new int[2];
            //双指针法
            for (int i = 0; i < s.length(); i++) {
                //奇数
                for (int j = 0; j+i < s.length() && i-j >=0; j++) {
                    if (s.charAt(i-j) == s.charAt(i+j)) {
                        if (2 * j + 1 > max) {
                            max = 2 * j + 1;
                            maxIndex[0] = i-j;
                            maxIndex[1] = i+j;
                        }
                    } else {
                        break;
                    }
                }
                //偶数
                for (int j = 0; j+i+1 < s.length() && i-j >=0; j++) {
                    if (s.charAt(i-j) == s.charAt(i+j+1)) {
                        if (2 * (j + 1) > max) {
                            max = 2 * (j + 1);
                            maxIndex[0] = i-j;
                            maxIndex[1] = i+j+1;
                        }
                    } else {
                        break;
                    }
                }
            }
            return s.substring(maxIndex[0], maxIndex[1]+1);
        }
    }
}
