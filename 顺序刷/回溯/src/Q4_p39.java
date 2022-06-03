import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * https://leetcode.cn/problems/combination-sum/
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1： 输入：candidates = [2,3,6,7], target = 7, 所求解集为： [ [7], [2,2,3] ]
 *
 * 示例 2： 输入：candidates = [2,3,5], target = 8, 所求解集为： [   [2,2,2,2],   [2,3,3],   [3,5] ]
 */
public class Q4_p39 {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combinationSum(candidates, target);
        System.out.println(ans);
    }
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> list = new LinkedList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtracking(candidates, 0, 0, target);
            return ans;
        }
        private void backtracking(int[] candidates, int sum, int start, int target) {
            if (sum == target) {
                ans.add(new ArrayList<>(list));
                return;
            }
            //因为每次从0开始会出现重复，因此需要start
            for (int i = start; i < candidates.length; i++) {
                //剪枝优化
                if (sum+candidates[i] > target) continue;
                list.add(candidates[i]);
                //因为可以重复选择，因此start还是从i开始，而不是i+1
                backtracking(candidates, sum+candidates[i], i, target);
                list.removeLast();
            }

        }
    }
}
