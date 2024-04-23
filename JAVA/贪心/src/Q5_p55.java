/**
 * 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Q5_p55 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        Solution solution = new Solution();
        boolean ans = solution.canJump(nums);
        System.out.println(ans);
    }

    static class Solution {
        //贪心，让每一次选择覆盖的距离都最长
        public boolean canJump(int[] nums) {
            int max = 0;
            //不能包括最后一个元素
            for (int i = 0; i < nums.length; i++) {
                //中间如果有不能到的地方，返回false
                if (max < i) return false;
                max = Math.max(max, i+nums[i]);
            }
            return true;
            //判断最后能不能到
            //if(max >= nums.length-1) return true;
            //else return false;
        }
    }
}
