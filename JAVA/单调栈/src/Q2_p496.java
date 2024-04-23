import java.util.*;

/**
 * @author sunlingzhang
 * @date 2023/10/25 15:56
 * 496.下一个更大元素 I
 * https://leetcode.cn/problems/next-greater-element-i/
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。
 * 如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出-1 。
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 */
public class Q2_p496 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1,3,5,2,4}, nums2 = {5,4,3,2,1};
        int[] ans = solution.nextGreaterElement2(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }
    static class Solution {
        /**
         * 从前往后遍历，单调栈（递减）
         * 出栈时指定下一个数字
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Deque<Integer> stack = new LinkedList<>();
            int[] next = new int[nums2.length];
            Map<Integer, Integer> hash = new HashMap<>();
            int[] ans = new int[nums1.length];
            for (int i=0; i<nums2.length; i++) {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    Integer index = stack.pop();
                    next[index] = nums2[i];
                }
                hash.put(nums2[i], i);
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                Integer index = stack.pop();
                next[index] = -1;
            }

            for (int i = 0; i < nums1.length; i++) {
                ans[i] = next[hash.get(nums1[i])];
            }
            return ans;
        }
        /**
         * 从后往前遍历，单调栈（递减）
         * 入栈时指定下一个数字
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
            Deque<Integer> stack = new ArrayDeque<>();
            HashMap<Integer, Integer> hash = new HashMap<>(nums2.length);
            int[] ans = new int[nums1.length];
            for (int i = nums2.length-1; i >= 0; i--) {
                while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    stack.pop();
                }
                hash.put(nums2[i], stack.isEmpty() ? -1 : nums2[stack.peek()]);
                stack.push(i);
            }
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = hash.get(nums1[i]);
            }
            return ans;
        }
    }
}
