import java.util.TreeSet;

/**
 * 我们枚举矩形的上下边界，并计算出该边界内每列的元素和，则原问题转换成了如下一维问题：
 * 给定一个整数数组和一个整数 kk，计算该数组的最大区间和，要求区间和不超过 kk。
 * 定义长度为 n 的数组 a 的前缀和
 * 则区间 [l,r)的区间和 a_l+a_{l+1}+...+a_{r-1}可以表示为 S_r-S_lS
 * 我们可以在枚举 rr 的同时维护一个存储 S_i(i<r) 的有序集合，则可以在O(logn) 的时间内二分找到符合要求的 S_l
 * */

class Solution {
    public static void main(String[] args) {
        int matrix[][] = {{1,0,1},{0,-2,3}};
        int k = 2;
        Solution solution = new Solution();
        int ans = solution.maxSumSubmatrix(matrix, k);
        System.out.println(ans);
    }
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}