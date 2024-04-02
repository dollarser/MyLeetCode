public class Q3_2 {
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
            //两个循环合并，反而时间和内存都延长了
            for (int i = 1, j=0; i < nums.length+1; i++) {
                sums[i] = sums[i-1] + nums[i-1];
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
