/**
 * 双指针法
 */
public class Q2_1 {
    public static void main(String[] args) {
        int nums[] = {3,2,2,3}, target = 3;

        Q2_1.Solution solution = new Q2_1().new Solution();
        int ans = solution.removeElement(nums, target);
        System.out.println(ans);
    }
    class Solution {
        public int removeElement(int[] nums, int val) {
            // 快慢指针
            int fastIndex = 0;
            int slowIndex;
            for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
                if (nums[fastIndex] != val) {
                    nums[slowIndex] = nums[fastIndex];
                    slowIndex++;
                }
            }
            return slowIndex;

        }
    }
}
