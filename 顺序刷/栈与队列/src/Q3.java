import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class Q3 {
    public static void main(String[] args) {
        //String s = "(){}[]";
        //String s = "()}{";
        String s = "}(";
        Solution solution = new Solution();
        boolean ans = solution.isValid(s);
        System.out.println(ans);
    }

    static class Solution {
        public boolean isValid(String s) {
            Map<Character, Integer> map = new HashMap<>();
            map.put('(', 1);
            map.put('[', 2);
            map.put('{', 3);
            map.put('}', 4);
            map.put(']', 5);
            map.put(')', 6);
            Deque<Character> deque = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                if (!deque.isEmpty() ) {
                    Character temp = deque.pop();
                    if (map.get(temp) > 3 || map.get(temp) + map.get(s.charAt(i)) != 7) {
                        deque.push(temp);
                        deque.push(s.charAt(i));
                    }
                } else {
                    deque.push(s.charAt(i));
                }
            }
            return deque.isEmpty();
        }
    }
}
