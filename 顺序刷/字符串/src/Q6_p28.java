/**
 * 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1: 输入: haystack = "hello", needle = "ll" 输出: 2
 *
 * 示例 2: 输入: haystack = "aaaaa", needle = "bba" 输出: -1
 *
 * 说明: 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class Q6_p28 {
    public static void main(String[] args) {
        String haystack = "mississippi", needle = "issip";
        Solution solution = new Solution();
        int ans = solution.strStr(haystack, needle);
        System.out.println(ans);
    }
    //KMP 算法
    static class Solution {
        public int strStr(String haystack, String needle) {
            int length = needle.length();
            // 当 needle 是空字符串时我们应当返回 0
            if (length == 0) {
                return 0;
            }

            int[] next = getNext(needle);
            System.out.println(next);
            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                if(j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                    if(j == length-1) {
                        return i-j;
                    }
                    ++j;
                } else {
                    --i;
                    j = next[j];
                }
            }
            return -1;
        }

        //前缀表（右移一位，初为补-1）
        int[] getNext(String s){
            /**
             * ArrayList<Integer> next = new ArrayList(s.length());
             *             int j = -1;
             *             int i = 0;
             *             next.add(0, -1);
             *             while (i < s.length()-1) {
             *                 if(j == -1 || s.charAt(i) == s.charAt(j)) {
             *                     ++i;
             *                     ++j;
             *                     next.add(i, j);
             *                 } else {
             *                     j = next.get(j);
             *                 }
             *             }
             */

            int[] next = new int[s.length()];
            //双指针法
            // j指向前缀末尾位置，i指向后缀末尾，j的含义是最长前后缀相等的长度
            int j = -1;
            int i = 0;
            next[0] = -1;
            while (i < s.length()-1) {
                if(j == -1 || s.charAt(i) == s.charAt(j)) {
                    ++i;
                    ++j;
                    next[i] = j;
                } else {
                    j = next[j];
                }
            }
            return next;
        }

    }
}
