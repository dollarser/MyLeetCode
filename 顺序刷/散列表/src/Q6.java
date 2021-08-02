import java.util.*;

/** ======= 重点题目 =========
 * 第15题. 三数之和
 * https://leetcode-cn.com/problems/3sum/
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意： 答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 双指针法
 */
public class Q6 {
    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);

    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            /**
             * 排序，对降低复杂度至关重要，方便后续操作
             * 如果一个问题，复杂度超过 n^2，不妨考虑通过排序将复杂度降低
             */

            Arrays.sort(nums);
            // 从左到右遍历，i表示三个数字中最左边的数字
            for (int i = 0; i < nums.length; i++) {
                //对于已经排序的数字，最左边的数都大于0，则和肯定不会等于0，循环结束
                if (nums[i] > 0) {
                    return result;
                }
                /** 去重
                 * 如果当前位置i的值等于前一个位置i-1的值，则直接跳过，所有情况在前一个位置时都已经考虑
                 * 需要注意的是不能认为当两个数相同时跳过前一个数，只考虑后一个数也行，只能跳过后一个考虑前一个
                 * 因为存在结果中包含这两个数的情况，此时只有考虑前一个数时才能得到这种结果
                 */
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (right > left) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum > 0) {
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //去重操作
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;

                        right--;
                        left++;
                    }
                }
            }
            return result;
        }
    }
}