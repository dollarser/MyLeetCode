import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第18题. 四数之和
 * https://leetcode-cn.com/problems/4sum/
 *
 * 题意：给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例： 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 */
public class Q8 {
    public static void main(String[] args) {
        int nums[] = {1, 0, -1, 0, -2, 2}, target = 0;

        Solution solution = new Solution();
        List<List<Integer>> ans = solution.fourSum(nums, target);
        System.out.println(ans);
    }

    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();
            //因为负数越加越小，所有此处不能用nums[i] <= target作为循环条件
            for (int i = 0; i < nums.length-3; i++) {
                if (i > 0 && nums[i - 1] == nums[i]) {
                    continue;
                }
                for (int j = i+1; j < nums.length-2; j++) {
                    if (j > i+1 && nums[j - 1] == nums[j]) {
                        continue;
                    }
                    int k = j+1, l = nums.length-1;
                    while(k < l) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum < target) {
                            k++;
                        } else if (sum > target) {
                            l--;
                        } else {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            temp.add(nums[l]);
                            ans.add(temp);
                            //去重
                            k++;
                            l--;
                            while(k < l && nums[k] == nums[k-1]) k++;
                            while(k < l && nums[l] == nums[l+1]) l--;
                        }
                    }
                }

            }
            return ans;
        }
    }
}
