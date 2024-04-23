/**
 * 题解方法，将该过程分为3步
 * 1. 去除多于空格
 * 2. 字符反转
 * 3. 以空格为分界将每个单词内部再反转一次
 *
 * 举个例子，源字符串为："  the sky is blue  "
 * 移除多余空格 : "the sky is blue"
 * 字符串反转："eulb si yks eht"
 * 单词反转："blue is sky the"
 */
public class Q4_1 {
    public static void main(String[] args) {
        String s = "  We  are  happy. ";
        Solution solution = new Solution();
        String ans = solution.reverseWords(s);
        System.out.println(ans);
    }
    static class Solution {
        /**
         * 不使用Java内置方法实现
         * <p>
         * 1.去除首尾以及中间多余空格
         * 2.反转整个字符串
         * 3.反转各个单词
         */
        public String reverseWords(String s) {
            // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
            // 1.去除首尾以及中间多余空格
            StringBuilder sb = removeSpace(s);
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length() - 1);
            // 3.反转各个单词
            reverseEachWord(sb);
            return sb.toString();
        }

        private StringBuilder removeSpace(String s) {
            // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
            int start = 0;
            int end = s.length() - 1;
            while (s.charAt(start) == ' ') start++;
            while (s.charAt(end) == ' ') end--;
            StringBuilder sb = new StringBuilder();
            while (start <= end) {
                char c = s.charAt(start);
                if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
                start++;
            }
            // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
            return sb;
        }

        /**
         * 反转字符串指定区间[start, end]的字符
         */
        public void reverseString(StringBuilder sb, int start, int end) {
            // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
            while (start < end) {
                char temp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);
                start++;
                end--;
            }
            // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
        }

        private void reverseEachWord(StringBuilder sb) {
            int start = 0;
            int end = 1;
            int n = sb.length();
            while (start < n) {
                while (end < n && sb.charAt(end) != ' ') {
                    end++;
                }
                reverseString(sb, start, end - 1);
                start = end + 1;
                end = start + 1;
            }
        }
    }
}
