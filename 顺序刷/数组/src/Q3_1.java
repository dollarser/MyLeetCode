/**
 * 滑动窗口
 */
public class Q3_1 {
    public static void main(String[] args) {
        int nums[] = {2,3,1,2,4,3}, target = 7;

        Solution solution = new Solution();
        int ans = solution.minSubArrayLen(target, nums);
        System.out.println(ans);
    }

    static class Solution {
        // 滑动窗口
        public int minSubArrayLen(int s, int[] nums) {
            int left = 0;
            int sum = 0;
            int result = Integer.MAX_VALUE;
            for (int right = 0; right < nums.length; right++) {
                sum += nums[right];

                while (sum >= s) {
                    result = Math.min(result, right - left + 1);
                    sum -= nums[left++];
                }
            }
            return result == Integer.MAX_VALUE ? 0 : result;
        }
    }
}