/**
 * 题目：剑指Offer58-II.左旋转字符串
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 *
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *
 * 限制：
 * 1 <= k < s.length <= 10000
 */
public class Q5 {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        Solution solution = new Solution();
        String ans = solution.reverseLeftWords(s, k);
        System.out.println(ans);
    }
    static class Solution {
        public String reverseLeftWords(String s, int n) {
            StringBuilder sb = new StringBuilder(s);
            reverse(sb, 0, n-1);
            reverse(sb, n, s.length()-1);
            reverse(sb, 0, s.length()-1);
            return sb.toString();
        }
        //左闭右闭
        void reverse(StringBuilder s, int left, int right) {
            while(left < right) {
                char temp = s.charAt(right);
                s.setCharAt(right, s.charAt(left));
                s.setCharAt(left, temp);

                left++;
                right--;
            }
        }
    }
}
