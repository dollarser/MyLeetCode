import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 第51题. N皇后
 * https://leetcode.cn/problems/n-queens/
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 * ".Q.."
 * "...Q"
 * "Q..."
 * "..Q."
 * 或
 * "..Q."
 * "Q..."
 * "...Q"
 * ".Q.."
 */

public class Q14_p51 {
    public static void main(String[] args) {
        int n = 4;
        Solution solution = new Solution();
        List<List<String>> ans = solution.solveNQueens(n);
        System.out.println(ans);
    }
    static class Solution {
        List<List<String>> ans = new LinkedList<>();
        StringBuilder sb = null;

        public List<List<String>> solveNQueens(int n) {
            sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
               sb.append('.');
            }
            int[] usedId = new int[n];
            backtracking(n, usedId, 0);
            return ans;
        }
        //按行或列一次放置
        void backtracking(int n, int[] usedId, int index) {
            //终止条件
            if (index >= n) {
                LinkedList<String> path = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        sb.setCharAt(j, '.');
                    }
                    sb.setCharAt(usedId[i], 'Q');
                    path.add(new String(sb));
                }
                ans.add(new ArrayList<>(path));
                return;
            }
            //递归
            //遍历之前已经添加的
            for (int j = 0; j < n; j++) {
                if (check(usedId, index, j)) {
                    usedId[index] = j;
                    backtracking(n, usedId, index+1);
                }
            }
        }

        //判断同行同列或同斜线
        Boolean check(int[] usedId, int row, int col) {
            //如果是第0行可以随便放
            if (row == 0) return true;
            //其他行需要比较
            for (int i = 0; i < row; i++) {
                if (col==usedId[i] || Math.abs(row-i)==Math.abs(col-usedId[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
