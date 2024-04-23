import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 第77题. 组合
 * https://leetcode.cn/problems/combinations/
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Q1_p77 {
    public static void main(String[] args) {
        int n = 4, k = 2;
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combine(n, k);
        System.out.println(ans);
    }

    static class Solution {
        List<List<Integer>> ans = new LinkedList<>();
        //方便增删
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> combine(int n, int k) {
            combineHelper(n, k, 1);
            return ans;
        }
        private void combineHelper(int n, int k, int startIndex) {
            //终止条件
            if (list.size() >= k) {
                //需要new一个新的，因为原始的list一直在变
                //因为长度固定，使用ArrayList即可
                List<Integer> temp = new ArrayList<>(list);
                ans.add(temp);
                return;
            }
            for (int i = startIndex; i <= n; i++) {
                list.add(i);
                combineHelper(n, k, i+1);
                list.removeLast();
            }
        }
    }
}
