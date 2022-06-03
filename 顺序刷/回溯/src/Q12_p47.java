import java.util.*;

/**
 * 47.全排列 II
 * https://leetcode.cn/problems/permutations-ii/
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出： [[1,1,2], [1,2,1], [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Q12_p47 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.permuteUnique(nums);
        System.out.println(ans);
    }
    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            //用于记录放入的数字
            int[] usedId = new int[nums.length];
            backtracking(nums, usedId);
            return ans;
        }
        void backtracking(int[] nums, int[] usedId) {
            if (path.size()>=nums.length) {
                ans.add(new ArrayList<>(path));
                return;
            }
            //记录当前层被遍历过的结点
            HashMap<Integer, Boolean> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                //之前层没遍历过, 当前层也没被遍历过
                if (usedId[i] != 1 && !map.containsKey(nums[i])) {
                    //标记当前层已经遍历
                    map.put(nums[i], true);
                    usedId[i] = 1;
                    path.add(nums[i]);

                    backtracking(nums, usedId);

                    usedId[i] = 0;
                    path.removeLast();
                }
            }
        }
    }
    //通过排序简化判断重复数字
    class Solution2 {
        //存放结果
        List<List<Integer>> result = new ArrayList<>();
        //暂存结果
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            boolean[] used = new boolean[nums.length];
            Arrays.fill(used, false);
            Arrays.sort(nums);
            backTrack(nums, used);
            return result;
        }

        private void backTrack(int[] nums, boolean[] used) {
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
                // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
                // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                    continue;
                }
                //如果同⼀树⽀nums[i]没使⽤过开始处理
                if (used[i] == false) {
                    used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                    path.add(nums[i]);
                    backTrack(nums, used);
                    path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                    used[i] = false;//回溯
                }
            }
        }
    }
}
