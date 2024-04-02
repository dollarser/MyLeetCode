import java.util.*;

/**
 * 46.全排列
 * https://leetcode.cn/problems/permutations/
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 */
public class Q11_p46 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.permute(nums);
        System.out.println(ans);
    }
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        public List<List<Integer>> permute(int[] nums) {
            //用于记录放入的数字
            HashMap<Integer, Boolean> map = new HashMap<>();
            backtracking(nums, map);
            return ans;
        }
        void backtracking(int[] nums, HashMap map) {
            //结束条件
            if (path.size()>=nums.length) {
                ans.add(new ArrayList<>(path));
            }
            //
            for (int i = 0; i < nums.length; i++) {
                //可以直接用path判断,不用map
                if (path.contains(nums[i]) ) {
                    continue;
                }
                //if (map.containsKey(nums[i])) continue;

                map.put(nums[i], true);
                path.add(nums[i]);
                backtracking(nums, map);
                map.remove(nums[i]);
                path.removeLast();
            }
        }
    }
}
