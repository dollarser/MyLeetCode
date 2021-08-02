import java.util.*;

/**
 * 错误的案例，无法去重
 */
public class Q6_1 {
    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        int nums[] = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
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
