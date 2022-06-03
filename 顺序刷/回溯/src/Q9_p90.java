import java.util.*;

/**
 * 90.子集II
 * https://leetcode.cn/problems/subsets-ii/
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 *
 * 与上题的区别是给定的集合中有重复元素
 * 但要求子集不能重复
 */
public class Q9_p90 {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.subsetsWithDup(nums);
        System.out.println(ans);
    }

    //回溯法
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            //排序将相同的元素放在一起,便于去重
            Arrays.sort(nums);
            backtracking(nums, 0);
            return ans;
        }
        //回溯是遍历长度从0到n的集合
        void backtracking(int[] nums, int start) {
            ans.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {
                //只需要添加一项,去重
                if (i>start && nums[i] == nums[i-1]) continue;
                path.add(nums[i]);
                backtracking(nums, i+1);
                path.removeLast();
            }
        }
    }
}
