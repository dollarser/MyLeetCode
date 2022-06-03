import java.util.*;

/**
 * 491.递增子序列
 * https://leetcode.cn/problems/increasing-subsequences/
 *
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class Q10_p491 {
    public static void main(String[] args) {
        int[] nums = {1,4,6,1,1,1};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.findSubsequences(nums);
        System.out.println(ans);

    }
    //递增子序列第一反应是dp但是因为不是计数而是要返回每一个结果,因此用回溯
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        public List<List<Integer>> findSubsequences(int[] nums) {
            backtracking(nums, 0);
            return ans;
        }
        //因为要求不降子序列最少要有两个元素
        void backtracking(int[] nums, int start) {
            //结束条件, 剪枝充当结束条件
            if (path.size()>1) {
                ans.add(new ArrayList<>(path));
            }
            //用map记录本层已经使用的结点,比较通用,用数组也可以
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int i = start; i < nums.length; i++) {
                // 去重, 因为相同的数字并不在一起,不能使用此方式
                //if (i>start && nums[i] == nums[i-1]) continue;
                //使用map记录更通用
                if (map.getOrDefault(nums[i], 0)>0) {
                    continue;
                }
                //剪枝,如果空之间入栈,如果比当前栈顶大入栈
                if (path.isEmpty() || nums[i] >= path.peekLast()) {
                    map.put(nums[i], 1); //不计数当boolean用
                    //map.put(nums[i], map.getOrDefault(nums[i], 0)+1); //计数
                    path.add(nums[i]);
                    backtracking(nums, i+1);
                    path.removeLast();
                }
            }
        }
    }
}
