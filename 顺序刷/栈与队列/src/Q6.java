import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 你能在线性时间复杂度内解决此题吗?
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class Q6 {
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

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1) {
                return nums;
            }
            int len = nums.length - k + 1;
            //存放结果元素的数组
            int[] res = new int[len];
            int num = 0;
            //自定义队列
            MyQueue myQueue = new MyQueue();
            //先将前k的元素放入队列
            for (int i = 0; i < k; i++) {
                myQueue.add(nums[i]);
            }
            res[num++] = myQueue.peek();
            for (int i = k; i < nums.length; i++) {
                //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
                myQueue.poll(nums[i - k]);
                //滑动窗口加入最后面的元素
                myQueue.add(nums[i]);
                //记录对应的最大值
                res[num++] = myQueue.peek();
            }
            return res;
        }

        /**
         * 单调队列，窗口滑动进行入队出队
         * 每次可以弹出一个最大值
         */
        class MyQueue {
            Deque<Integer> deque = new ArrayDeque<>();
            //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
            //同时判断队列当前是否为空
            void poll(int val) {
                if (!deque.isEmpty() && val == deque.peek()) {
                    deque.poll();
                }
            }
            //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
            //保证队列元素单调递减
            //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
            void add(int val) {
                while (!deque.isEmpty() && val > deque.getLast()) {
                    deque.removeLast();
                }
                deque.add(val);
            }
            //队列队顶元素始终为最大值
            int peek() {
                return deque.peek();
            }
        }

    }
}
