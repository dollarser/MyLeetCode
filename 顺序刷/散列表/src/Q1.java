/**
 * 242.有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1: 输入: s = "anagram", t = "nagaram" 输出: true
 *
 * 示例 2: 输入: s = "rat", t = "car" 输出: false
 *
 * 说明: 你可以假设字符串只包含小写字母。
 */
public class Q1 {
    public static void main(String[] args) {
        //String s = "anagram", t = "nagaram";
        String s = "a", t = "ab";
        Solution solution = new Solution();
        boolean ans = solution.isAnagram(s, t);
        System.out.println(ans);
    }

    static class Solution {
        public boolean isAnagram(String s, String t) {
            int sArray[] = new int[26];
            int tArray[] = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                sArray[c]++;
            }

            for (int i = 0; i < t.length(); i++) {
                int c = t.charAt(i) - 'a';
                tArray[c]++;
            }
            for (int i = 0; i < sArray.length; i++) {
                if(sArray[i] != tArray[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
