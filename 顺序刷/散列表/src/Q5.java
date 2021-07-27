import java.util.HashMap;
import java.util.Map;

/**
 * 第454题.四数相加II
 *  https://leetcode-cn.com/problems/4sum-ii/
 *
 *  给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 *  为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 *
 *  例如:
 *
 *  输入: A = [ 1, 2] B = [-2,-1] C = [-1, 2] D = [ 0, 2] 输出: 2 解释: 两个元组如下:
 *
 *  (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 *  (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class Q5 {
    public static void main(String[] args) {
        int [] A = {1, 2},
        B = {-2,-1},
        C = {-1, 2},
        D = {0, 2};

        Solution solution = new Solution();
        int ans = solution.fourSumCount(A, B, C, D);
        System.out.println(ans);
    }

    static class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            int ans = 0;
            //因为set会去重，因此可以用map保存出现的次数
            Map<Integer, Integer> map = new HashMap<>();
            int temp;
            for (int i: nums1) {
                for (int j : nums2) {
                    temp = i+j;
                    if( map.containsKey(temp) ) {
                        map.put(i+j, map.get(temp) + 1);
                    } else {
                        map.put(temp, 1);
                    }
                }
            }
            for (int i = 0; i < nums3.length; i++) {
                for (int j = 0; j < nums4.length; j++) {
                    temp = nums3[i] + nums4[j];
                    if(map.containsKey(-temp)) {
                        ans += map.get(-temp);
                    }
                }
            }
            return ans;
        }
    }
}
