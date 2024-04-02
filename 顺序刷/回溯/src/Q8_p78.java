import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 78.子集
 * https://leetcode.cn/problems/subsets/
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例: 输入: nums = [1,2,3] 输出: [ [3],   [1],   [2],   [1,2,3],   [1,3],   [2,3],   [1,2],   [] ]
 */
public class Q8_p78 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution2 solution = new Solution2();
        List<List<Integer>> ans = solution.subsets(nums);
        System.out.println(ans);
    }
    //简单循环是逐步增加元素
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        public List<List<Integer>> subsets(int[] nums) {
            ans.add(new LinkedList<>());
            for (int i = 0; i < nums.length; i++) {
                //ans在插入过程长度一直变，因此需要保存最初的长度
                int len = ans.size();
                for (int j = 0; j < len; j++) {
                    List<Integer> temp = new LinkedList<>(ans.get(j));
                    temp.add(nums[i]);
                    ans.add(temp);
                }
            }
            return ans;
        }
    }

    //回溯法
    static class Solution2 {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        public List<List<Integer>> subsets(int[] nums) {
            backtracking(nums, 0);
            return ans;
        }
        //回溯是遍历长度从0到n的集合
        void backtracking(int[] nums, int start) {
            ans.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                backtracking(nums, i+1);
                path.removeLast();
            }
        }
    }
}
