import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 *
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 */
public class Q2 {
    public static void main(String[] args) {
        int x = 0;
        MyStack solution = new MyStack();
        solution.push(x);
        System.out.println(solution.empty());
        solution.pop();
        solution.empty();
        System.out.println(solution.empty());
    }

    static class MyStack {

        Queue<Integer> queue1; // 和栈中保持一样元素的队列
        Queue<Integer> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<>(); // 和栈中保持一样元素的队列
            queue2 = new LinkedList<>();; // 辅助队列
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if (queue1.isEmpty()) {
                queue1.offer(x);
                while (!queue2.isEmpty()) {
                    queue1.offer(queue2.poll());
                }
            } else {
                queue2.offer(x);
                while (!queue1.isEmpty()) {
                    queue2.offer(queue1.poll());
                }
            }

        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (queue1.isEmpty()) {
                return queue2.poll();
            } else {
                return queue1.poll();
            }
        }

        /** Get the top element. */
        public int top() {
            if (queue1.isEmpty()) {
                return queue2.peek();
            } else {
                return queue1.peek();
            }
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}
