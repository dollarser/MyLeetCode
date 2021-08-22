/**
 * 459.重复的子字符串
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class Q7 {
    public static void main(String[] args) {
        //String s = "abac";
        String s = "abacababacab";
        Solution solution = new Solution();
        boolean ans = solution.repeatedSubstringPattern(s);
        System.out.println(ans);
    }
    static class Solution {
        public boolean repeatedSubstringPattern(String s) {
            int[] next = new int[s.length()];
            int i = 1, j = 0;
            next[0] = -1;
            //标准前缀
            while( i < s.length() ) {
                if (j == -1 || s.charAt(i) == s.charAt(j)) {
                    next[i] = j;
                    ++i;
                    ++j;
                } else {
                    /**
                     * 回退是按前一个的next回退
                     */
                    j--;
                    if( j >= 0 ) j = next[j]+1;
                }
            }
            int k = s.length() - 1;
            for (int l: next) {
                System.out.println(l);
            }
            /**
             * 判断是否有循环子字串:
             * 原理 最终串比模式串多的字符如果能被串的长度整除则说明是重复子字串
             */
            if(s.length() % (s.length()-1 - next[s.length()-1]) == 0 &&
                    s.length() != s.length()-1 - next[s.length()-1] ) {
                return true;
            }
            return false;
        }
    }
}
