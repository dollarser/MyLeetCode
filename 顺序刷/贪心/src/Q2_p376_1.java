public class Q2_p376_1 {
    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        Solution solution = new Solution();
        int ans = solution.wiggleMaxLength(nums);
        System.out.println(ans);
    }

    /**
     * 神解 动态规划加贪心
     * 假设 up[i] 表示 nums[0:i] 中最后两个数字递增的最长摆动序列长度，down[i] 表示 nums[0:i] 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 1。
     *
     * 接下来我们进行分类讨论：
     * nums[i+1] > nums[i]
     * 假设 down[i] 表示的最长摆动序列的最远末尾元素下标正好为 i，遇到新的上升元素后，up[i+1] = down[i] + 1 ，这是因为 up 一定从 down 中产生（初始除外），并且 down[i] 此时最大。
     * 假设 down[i] 表示的最长摆动序列的最远末尾元素下标小于 i，设为 j，那么 nums[j:i] 一定是递增的，因为若完全递减，最远元素下标等于 i，若波动，那么 down[i] > down[j]。由于 nums[j:i] 递增，down[j:i] 一直等于 down[j] ，依然满足 up[i+1] = down[i] + 1 。
     *
     * nums[i+1] < nums[i]，类似第一种情况
     * nums[i+1] == nums[i]，新的元素不能用于任何序列，保持不变
     */
    static class Solution {
        public int wiggleMaxLength(int[] nums) {
            int down = 1, up = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1])
                    up = down + 1;
                else if (nums[i] < nums[i - 1])
                    down = up + 1;
            }
            return nums.length == 0 ? 0 : Math.max(down, up);
        }
    }


    // 使用动态规划
    static class Solution1 {
        public int wiggleMaxLength(int[] nums) {
            // 0 i 作为波峰的最大长度
            // 1 i 作为波谷的最大长度
            int dp[][] = new int[nums.length][2];

            dp[0][0] = dp[0][1] = 1;
            for (int i = 1; i < nums.length; i++){
                //i 自己可以成为波峰或者波谷
                dp[i][0] = dp[i][1] = 1;

                for (int j = 0; j < i; j++){
                    if (nums[j] > nums[i]){
                        // i 是波谷
                        dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                    }
                    if (nums[j] < nums[i]){
                        // i 是波峰
                        dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                    }
                }
            }
            return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
        }
    }
}
