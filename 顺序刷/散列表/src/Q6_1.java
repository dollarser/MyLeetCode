import java.util.*;

/**
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
 * 该题难点在于去重，因为如果数组中有重复的元素，而重复的元素又满足条件，会得到重复的结果
 * 通过排序可以很好的方面后面去重
 */
public class Q6_1 {
    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);

    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //排序方便去重
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();

            //initialCapacity是容量而不是长度
            List<Integer> temp_ = new ArrayList<Integer>(3);
            temp_.add(0, 100001);
            temp_.add(1, 100001);
            temp_.add(2, 100001);
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    for (int k = j+1; k < nums.length; k++) {
                        boolean flag = (temp_.get(0) == nums[i] && temp_.get(1) == nums[j] && temp_.get(2) == nums[k] );
                        if(!flag && (nums[i] + nums[j] + nums[k] == 0)) {
                            if(temp_.get(0) == -2) {
                                System.out.println(temp_);
                                System.out.println(i+":"+j+":"+k);
                            }
                            temp_.set(0, nums[i]);
                            temp_.set(1, nums[j]);
                            temp_.set(2, nums[k]);
                            List<Integer> temp = new ArrayList<Integer>();
                            temp.add(0, nums[i]);
                            temp.add(1, nums[j]);
                            temp.add(2, nums[k]);
                            //add是之间将引用加入
                            ans.add(temp);
                        }
                    }
                }
            }
            return ans;
        }
    }
}
