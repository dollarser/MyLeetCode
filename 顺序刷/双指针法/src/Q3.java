/**
 * 题目：剑指Offer 05.替换空格
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1： 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class Q3 {
    public static void main(String[] args) {
        String s = "We are happy.";
        Solution solution = new Solution();
        solution.replaceSpace(s);
        System.out.println(s);
    }
    static class Solution {
        public String replaceSpace(String s) {
            if (s == null) {
                return null;
            }
            //选用 StringBuilder 单线程使用，比较快，选不选都行
            StringBuilder sb = new StringBuilder();
            //使用 sb 逐个复制 str ，碰到空格则替换，否则直接复制
            for (int i = 0; i < s.length(); i++) {
                if (" ".equals(String.valueOf(s.charAt(i)))) {
                    sb.append("%20");
                } else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
    }
}
