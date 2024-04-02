import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author sunlingzhang
 * @date 2023/10/26 16:58
 * 503.下一个更大元素II
 * https://leetcode.cn/problems/next-greater-element-ii/
 *
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；数字 2 找不到下一个更大的数；第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 提示:
 *
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Q3_p503 {
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] nums = {1,2,1};
            int[] ans = solution.nextGreaterElements(nums);
            System.out.println(Arrays.toString(ans));
        }
        /**
         * 正序遍历
         * @param nums
         * @return
         */
        public int[] nextGreaterElements(int[] nums) {
            Deque<Integer> deque = new ArrayDeque<>();
            int[] res = new int[nums.length];
            int[] visited = new int[nums.length];
            Arrays.fill(visited, 0);
            for (int i = 0; i < 2 * nums.length; i++) {
                while(!deque.isEmpty() && nums[deque.peek()] < nums[i%nums.length]) {
                    int index = deque.pop();
                    res[index] = nums[i%nums.length];
                    visited[index] = 1;
                }
                if (visited[i%nums.length] == 0) {
                    deque.push(i%nums.length);
                }
            }
            while(!deque.isEmpty()) {
                int index = deque.pop();
                res[index] = -1;
            }
            return res;
        }

        public int[] nextGreaterElements2(int[] nums) {
            Deque<Integer> deque = new ArrayDeque<>();
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);
            for (int i = 0; i < 2 * nums.length; i++) {
                while(!deque.isEmpty() && nums[deque.peek()] < nums[i%nums.length]) {
                    int index = deque.pop();
                    res[index] = nums[i%nums.length];
                }
                if (res[i%nums.length] == -1) {
                    deque.push(i%nums.length);
                }
            }
            return res;
        }
    }
}
