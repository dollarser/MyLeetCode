import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232.用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * 示例:
 *
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */
public class Q1 {
    public static void main(String[] args) {
        int x = 0;
        MyQueue solution = new MyQueue();
        solution.push(x);
        System.out.println();
    }

    static class MyQueue {
        // java中的 Stack 有设计上的缺陷，官方推荐使用 Deque(双端队列) 代替 Stack
        Deque<Integer> stIn;
        Deque<Integer> stOut;
        /** Initialize your data structure here. */
        /** Initialize your data structure here. */
        public MyQueue() {
            stIn = new ArrayDeque<>();
            stOut = new ArrayDeque<>();
        }

        /** Push element x to the back of queue. */
        //入栈和入队没区别
        public void push(int x) {
            stIn.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        //出队需要把栈颠倒，但不用每次都进行，如果之前颠倒的还不为空就不用
        public int pop() {
            // 只要 stOut 为空，那么就应该将 stIn 中所有的元素倒腾到 stOut 中
            if (stOut.isEmpty()) {
                while (!stIn.isEmpty()) {
                    stOut.push(stIn.pop());
                }
            }
            // 再返回 stOut 中的元素
            return stOut.pop();
        }

        /** Get the front element. */
        //使用已经实现的方法查看第一个元素
        public int peek() {
            // 直接使用已有的pop函数
            int res = this.pop();
            // 因为pop函数弹出了元素res，所以再添加回去
            stOut.push(res);
            return res;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stIn.isEmpty() && stOut.isEmpty();
        }
    }

}
