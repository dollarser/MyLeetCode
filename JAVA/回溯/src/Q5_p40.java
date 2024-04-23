import java.util.*;

/**
 * 40.组合总和II
 * https://github.com/dollarser/leetcode-master/blob/master/problems/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.md
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明： 所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。
 *
 * 示例 1: 输入: candidates = [10,1,2,7,6,1,5], target = 8, 所求解集为: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
 *
 * 示例 2: 输入: candidates = [2,5,2,1,2], target = 5, 所求解集为: [   [1,2,2],   [5] ]
 */
public class Q5_p40 {

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 7;

        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combinationSum2(candidates, target);
        System.out.println(ans);
    }
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> list = new LinkedList<>();
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //排序让相同的字符放在一起
            Arrays.sort(candidates);
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
                /**
                 * 注意这里的 i>start是指当前层第一个一定遍历，之后有相同的才跳过
                 */
                if (i>start && candidates[i] == candidates[i-1]) continue;
                if (i< candidates.length && sum+candidates[i] > target) continue;
                list.add(candidates[i]);
                //因为可以重复选择，因此start还是从i开始，而不是i+1
                backtracking(candidates, sum+candidates[i], i+1, target);
                list.removeLast();
            }

        }
    }
}
