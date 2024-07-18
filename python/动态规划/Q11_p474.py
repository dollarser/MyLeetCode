"""
474.一和零

https://leetcode.cn/problems/ones-and-zeroes/description/

给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

 

示例 1：

输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
示例 2：

输入：strs = ["10", "0", "1"], m = 1, n = 1
输出：2
解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 

提示：

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] 仅由 '0' 和 '1' 组成
1 <= m, n <= 100
"""
from typing import List


class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        """
        背包问题：两个背包，一个背包装0，另一个背包装1
        递推公式：
            遍历到第i个元素，假设含有0的个数n0，含有1的个数n1
            可以加入，此时长度等于0背包-n0时的长度和1背包-n1的长度取最大值
            一般公式：
                dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-n0][k-n1] + 1)
            特殊情况
                j < n0 时，第i个不能加入：dp[i][j][k] = dp[i-1][j][k]
                k < n1 时，第i个不能加入：dp[i][j][k] = dp[i-1][j][k]
        边界值：
            i表示遍历的元素序号 0~len(strs) + 1
            j， k表示背包容量, 分别为 m + 1， n+1
            i = 0时，无元素放入，长度为0
            j=0时不放入，k=0时不放入
            i
        """
        # 不能使用这种初始化，列表是引用传递，这样所有行列公用一个列表
        # dp = [[[0] * (n +1)] * (m+1)] * (len(strs)+1)
        dp = [[[0] * (n +1) for j in range(m+1)] for i in range(len(strs)+1)]
        for i in range(1, len(strs)+1):
            str_ = strs[i-1]
            n0 = str_.count("0")
            n1 = str_.count("1")
            # 这里需要从0开始遍历，因为j为0时也能装下不含0的字符串
            for j in range(m+1):
                # 这里需要从0开始遍历，因为i为0时也能装下不含1的字符串
                for k in range(n+1):
                    if j < n0 or k < n1:
                        dp[i][j][k] = dp[i-1][j][k]
                    else:
                        dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-n0][k-n1] + 1)
        # 循环结束ijk的生命周期还在，和java、c不同
        # return dp[len(strs)][m][n]
        return dp[i][j][k]


if __name__ == "__main__":
    s = Solution()
    strs = ["10","0001","111001","1","0"]
    m = 5
    n = 3
    # strs = ["10","0","1"]
    # m = 1
    # n = 1
    n = s.findMaxForm(strs, m, n)
    print(n)