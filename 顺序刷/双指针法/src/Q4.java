/**
 * 151.翻转字符串里的单词
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 本题目我的思路是，直接新建一个字符数组，遍历原字符串
 * 每遍历一个单词，将该单词复制到新字符数组中
 * 期间注意句中多余空格，句首，句末多余空格的处理
 * 句中空格的处理：如果当前遍历到空格，且该空格前还是空格，或者该空格前没有字符，则此时不将空格复制到数组中，复制空格前的单词，之后进入下次遍历
 * 句首的处理：如果当前遍历到句首第一个单词且不是空格，遍历结束，将该单词复制到数组中，如果句首是空格，当句中空格处理
 * 句末多余空格的处理：如果句末直接遇到空格，此时数组为空，不将该空格复制到数组中。
 */
public class Q4 {
    public static void main(String[] args) {
        String s = "  We  are  happy. ";
        Solution solution = new Solution();
        String ans = solution.reverseWords(s);
        System.out.println(ans);
    }
    static class Solution {
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();
            int k = s.length();
            for (int i = s.length()-1; i > -1; i--) {
                //到最后一个元素，且最后一个元素不为空格，单独处理
                if(i == 0 && s.charAt(i) != ' ') {
                    for (int j = i; j < k; j++) {
                        sb.append(s.charAt(j));
                    }
                } else if (s.charAt(i) == ' ') {
                    for (int j = i+1; j < k; j++) {
                        sb.append(s.charAt(j));
                    }
                    /**
                     * 判断要不要加空格:
                     * 前面没有元素则不加空格，后面还有空格则不加空格，已经到最后一个元素则不加空格
                     */
                    k = i;
                    if(sb.length() ==0 || i == 0 || s.charAt(i-1) == ' ') {
                        continue;
                    }
                    //sb.append("*");
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }

}
