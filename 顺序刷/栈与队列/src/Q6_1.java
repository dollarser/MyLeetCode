import java.util.ArrayDeque;
import java.util.Deque;


public class Q6_1 {
    public static void main(String[] args) {
        int[] nums = {9,11,5,6,4,8,7};
        int k = 2;
        Solution solution = new Solution();
        int ans[] = solution.maxSlidingWindow(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
    static class Solution {
        MyQueue myQueue = new MyQueue();

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1) {
                return nums;
            }
            int len = nums.length - k + 1;
            //存放结果元素的数组
            int[] ans = new int[len];
            if (k > nums.length) {
                for (int i = 0; i < nums.length; i++) {
                    myQueue.push(nums[i]);
                }
                ans[0] = myQueue.peek();
                return ans;
            }

            for (int i = 0; i < nums.length; i++) {
                myQueue.push(nums[i]);
                if (i+1 >= k) {
                    ans[i-k+1] = myQueue.peek();
                    // 如果和最大值相等，则说明滑窗越过了该值，将该值从队列中出队
                    if (nums[i-k+1] == myQueue.peek()) {
                        myQueue.pop();
                    }
                }

            }
            return ans;
        }

        class MyQueue {
            Deque<Integer> queue = new ArrayDeque();
            // 出队
            public Integer pop() {
                return queue.poll();
            }
            // 入队,将队中所有小于当前元素的删除
            public void push(Integer i) {
                // 当栈使用，弹出所有比当前小的，相等不用弹出
                while (queue.peekLast() != null && queue.peekLast() < i) {
                    // queue.pop(); pop是队首出队
                    queue.pollLast(); //这个才是队尾出队
                }
                //队尾入队是 offer()，push是队首入队
                queue.offer(i);
            }
            // 查看队首元素
            public Integer peek() {
                return queue.peekFirst();
            }
        }
    }
}
