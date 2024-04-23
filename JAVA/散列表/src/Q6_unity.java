import java.util.*;

public class Q6_unity {
    public static void main(String[] args) {

    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new LinkedList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i]>0) return result;
                if (i>0 && nums[i] == nums[i-1]) continue;
                int j=i+1, k=nums.length-1;
                while(j<k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum>0) k--;
                    else if (sum<0) j++;
                    else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while(j<k && nums[j+1] == nums[j]) j++;
                        while(j<k && nums[k-1] == nums[k]) k--;
                        j++;
                        k--;
                    }
                }
            }

            return result;
        }
    }
}
