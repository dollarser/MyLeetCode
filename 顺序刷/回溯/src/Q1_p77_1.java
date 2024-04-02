import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 进行剪枝优化，因为要组合需要k个数，
 * 所以遍历过程剩余的数加上已经插入的数少于k可剪枝
 */
public class Q1_p77_1 {
    public static void main(String[] args) {
        int n = 4, k = 2;
        Q1_p77.Solution solution = new Q1_p77.Solution();
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
            for (int i = startIndex; i <= n-(k-list.size())+1; i++) {
                list.add(i);
                combineHelper(n, k, i+1);
                list.removeLast();
            }
        }
    }
}
