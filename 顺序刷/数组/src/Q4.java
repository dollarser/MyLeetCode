import java.util.Arrays;

/**
 * 59.螺旋矩阵II
 * 题目地址：https://leetcode-cn.com/problems/spiral-matrix-ii/
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 * 输入: 3
 * 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 */

public class Q4 {
    public static void main(String[] args) {
        int n = 3;
        Solution solution = new Solution();
        int ans[][] = solution.generateMatrix(n);
        for(int[] a : ans) {
            System.out.println(Arrays.toString(a));
        }
    }
    //整体思想：如果每轮之间规则不同，但每两轮或四轮之间规则相同，可以多轮作为一个循环
    static class Solution {
        public int[][] generateMatrix(int n) {
            int ans[][] = new int[n][n], num=1;
            int iLow=0, iHigh=n, jLow=0, jHigh=n;
            while(iLow<jHigh) {
                for (int j = jLow; j < jHigh; j++) {
                    ans[iLow][j] = num;
                    num++;
                }
                ++iLow;
                for (int i = iLow; i < iHigh; i++) {
                    ans[i][jHigh-1] = num;
                    num++;
                }
                --jHigh;
                for (int j = jHigh-1; j >= jLow; j--) {
                    ans[iHigh-1][j] = num;
                    num++;
                }
                --iHigh;
                for (int i = iHigh-1; i >= iLow; i--) {
                    ans[i][jLow] = num;
                    num++;
                }
                ++jLow;
            }
            return ans;
        }
    }
}
