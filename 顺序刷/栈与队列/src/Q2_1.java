import java.util.ArrayDeque;
import java.util.Deque;

public class Q2_1 {
    /**
     * 优化只用一个队列，每次出栈都循环出队入队到最后一个
     */
    static class MyStack {
        // Deque 接口继承了 Queue 接口
        // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
        Deque<Integer> que1;
        /** Initialize your data structure here. */
        public MyStack() {
            que1 = new ArrayDeque<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            que1.addLast(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int size = que1.size();
            size--;
            // 将 que1 导入 que2 ，但留下最后一个值
            while (size-- > 0) {
                que1.addLast(que1.peekFirst());
                que1.pollFirst();
            }

            int res = que1.pollFirst();
            return res;
        }

        /** Get the top element. */
        public int top() {
            return que1.peekLast();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return que1.isEmpty();
        }
    }
}
