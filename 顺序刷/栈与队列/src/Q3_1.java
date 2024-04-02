import java.util.Deque;
import java.util.LinkedList;

/**
 * 优点，遇到左括号，直接将相应的右括号入栈
 * 遇到右括号直接判断和栈顶元素是否相等
 */
public class Q3_1 {
    static class Solution {
        public boolean isValid(String s) {
            Deque<Character> deque = new LinkedList<>();
            char ch;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i);
                //碰到左括号，就把相应的右括号入栈
                if (ch == '(') {
                    deque.push(')');
                }else if (ch == '{') {
                    deque.push('}');
                }else if (ch == '[') {
                    deque.push(']');
                } else if (deque.isEmpty() || deque.peek() != ch) {
                    return false;
                }else {//如果是右括号判断是否和栈顶元素匹配
                    deque.pop();
                }
            }
            //最后判断栈中元素是否匹配
            return deque.isEmpty();
        }
    }
}
