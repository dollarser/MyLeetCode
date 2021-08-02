import java.util.*;
/**
 * 基于Hash的方法，去重手法值得学习
 */
public class Q6_2 {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);

    }
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //排序方便去重
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();

            // 找出a + b + c = 0
            // a = nums[i], b = nums[j], c = -(a + b)
            for (int i = 0; i < nums.length; i++) {
                // 排序之后如果第一个元素已经大于零，那么不可能凑成三元组
                if (nums[i] > 0) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1]) { //三元组元素a去重
                    continue;
                }
                Set<Integer> set = new HashSet<>();
                for (int j = i + 1; j < nums.length; j++) {
                    if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) { // 三元组元素b去重
                        continue;
                    }
                    int c = 0 - (nums[i] + nums[j]);
                    if (set.contains(c)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(c);
                        ans.add(temp);
                        set.remove(c);// 三元组元素c去重
                    } else {
                        set.add(nums[j]);
                    }
                }
            }
            return ans;
        }
    }
}
