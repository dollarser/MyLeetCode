/**
 * 209.长度最小的子数组
 * 题目链接： https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class Q3 {
    public static void main(String[] args) {
        int nums[] = {2,3,1,2,4,3}, target = 7;

        Solution solution = new Solution();
        int ans = solution.minSubArrayLen(target, nums);
        System.out.println(ans);
    }

    //前n项和的形式
    static class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int sums[] = new int[nums.length+1], ans = (1<<31)-1;
            sums[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                sums[i+1] = sums[i] + nums[i];
            }
            for (int i = 1, j = 0; i < sums.length; i++) {
                if(sums[i]-sums[j] >= target) {
                    if(ans > i-j) {
                        ans = i-j;
                    }
                    ++j;
                    i--;
                }
            }
            return ans == (1<<31)-1 ? 0 : ans;
        }
    }
}
