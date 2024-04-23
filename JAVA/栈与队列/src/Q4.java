import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * 提示：
 *
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 */
public class Q4 {
    public static void main(String[] args) {
        String s = "abbaca";
        Solution solution = new Solution();
        String ans = solution.removeDuplicates(s);
        System.out.println(ans);
    }

    static class Solution {
        public String removeDuplicates(String s) {
//            Deque<Character> deque = new ArrayDeque<>();
//            for (int i = 0; i < s.length(); i++) {
//                if (deque.isEmpty() || s.charAt(i) != deque.peek()) {
//                    deque.push(s.charAt(i));
//                } else {
//                    deque.pop();
//                }
//            }
//            String str = "";
//            //剩余的元素即为不重复的元素
//            while (!deque.isEmpty()) {
//                str = deque.pop() + str;
//            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (sb.length() ==0 || s.charAt(i) != sb.charAt(sb.length() -1)) {
                    sb.append(s.charAt(i));
                } else {
                    sb.deleteCharAt(sb.length() -1);
                }

            }
            return sb.toString();
        }
    }
}
