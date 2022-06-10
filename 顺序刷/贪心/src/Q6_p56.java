/**
 * 45.跳跃游戏II
 * https://leetcode.cn/problems/jump-game-ii/
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明: 假设你总是可以到达数组的最后一个位置。
 */
public class Q6_p56 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        Solution solution = new Solution();
        int ans = solution.jump(nums);
        System.out.println(ans);
    }
    static class Solution {
        //每次选最长的，那么跳跃的次数就最少
        public int jump(int[] nums) {
            int curLen = 0, preLen = 0;
            int i = 0;
            int ans = 0;
            while (i< nums.length) {
                int maxLen = 0;
                for (i = preLen; i <= curLen && i<nums.length; i++) {
                    maxLen = Math.max(maxLen, i + nums[i]);
                }
                ans++;
                preLen = curLen;
                curLen = maxLen;
            }
            //循环结束最后一次加是多余的
            return ans-1;
        }
    }
    //反向查找出发位置，从左到右查找第一个能到结尾的结点，把该结点设为新的结尾
    class Solution1{
        public int jump(int[] nums) {
            int position = nums.length - 1;
            int steps = 0;
            while (position > 0) {
                for (int i = 0; i < position; i++) {
                    if (i + nums[i] >= position) {
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }
    }
    //正向查找
    class Solution2 {
        public int jump(int[] nums) {
            int length = nums.length;
            int end = 0;
            int maxPosition = 0;
            int steps = 0;
            for (int i = 0; i < length - 1; i++) {
                maxPosition = Math.max(maxPosition, i + nums[i]);
                if (i == end) {
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }
    }
}
