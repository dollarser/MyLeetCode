public class s1 {
    /**
     * 暴力的基础上用前缀和进行优化
     * <p>
     * f(i,j)为左上角为[0,0]，右下角为[i-1][j-1]
     * <p>
     * 时间复杂度O((m*n)^2)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        //前缀和
        int[][] preSum = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //计算[i,j]为起点的最大和
                for (int l = i; l < row; l++) {
                    for (int m = j; m < col; m++) {
                        //统计[i,j],[l,m]之间的和
                        int sum = preSum[l + 1][m + 1] - preSum[i][m + 1] - preSum[l + 1][j] + preSum[i][j];
                        if (sum <= k) {
                            max = Math.max(sum, max);
                        }
                    }
                }
            }
        }
        return max;
    }
}
