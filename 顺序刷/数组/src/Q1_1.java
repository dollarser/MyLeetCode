import java.util.Scanner;

/**
 * 704.二分查找
 */
public class Q1_1 {
    public static void main(String[] args) {
        int nums[] = {-1,0,3,5,9,12}, target = 9;

        Solution solution = new Q1_1().new Solution();
        int ans = solution.search(nums, target);
        System.out.println(ans);
    }

    class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length, mid = 0;
            while(low+16<high) {
                mid = (low+high)/2;
                if(target < nums[mid]) {
                    high = mid;
                }
                if(target > nums[mid]) {
                    low = mid+1; //+1必不可少，否则可能死循环
                }
                if(target == nums[mid]) {
                    return mid;
                }
            }
            for(int i=low; i<high; ++i) {
                if(target == nums[i]) {
                    return i;
                }
            }
            return -1;
        }
    }
}