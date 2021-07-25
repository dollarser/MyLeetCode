import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 题意：给定两个数组，编写一个函数来计算它们的交集。
 *
 * 349. 两个数组的交集
 *
 * 说明： 输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
 */
public class Q2 {
    public static void main(String[] args) {
        //int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        int[] nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
        Solution solution = new Solution();
        int[] ans = solution.intersection(nums1, nums2);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            //HashSet使用Hash表的集合，不是红黑树
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();

            //数组存入Hash表中
            for (int i : nums1) {
                set1.add(i);
            }
            //遍历数组2的过程中判断哈希表中是否存在该元素
            for (int i : nums2) {
                if (set1.contains(i)) {
                    //借助HashSet去重
                    set2.add(i);
                }
            }
            //hash表转数组
            int index = 0;
            int[] ans = new int[set2.size()];
            for (int i : set2) {
                ans[index++] = i;
            }
            return ans;
        }
    }
}
