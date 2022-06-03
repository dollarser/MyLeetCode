import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216.组合总和III
 * https://leetcode.cn/problems/combination-sum-iii/
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
 *
 * 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Q2_p216 {
    public static void main(String[] args) {
        int n = 7, k = 3;
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combinationSum3(k, n);
        System.out.println(ans);
    }

    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        //方便增删
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> combinationSum3(int k, int n) {
            combinationSum3Helper(n, k, 1, 0);
            return ans;
        }
        private void combinationSum3Helper(int n, int k, int startIndex, int sum) {
            //终止条件
            if (list.size() >= k) {
                if (sum == n) {
                    List<Integer> temp = new ArrayList<>(list);
                    ans.add(temp);
                }
                return;
            }
            for (int i = startIndex; i <= 9-(k-list.size())+1; i++) {
                //如果和大于n，进行剪枝
                //省略了sum += i，直接传参时加上
                if (sum + i > n) break;
                list.add(i);
                combinationSum3Helper(n, k, i+1, sum+i);
                list.removeLast();
            }
        }
    }
}
