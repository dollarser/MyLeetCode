# 1049.最后一块石头的重量II
# https://leetcode.cn/problems/last-stone-weight-ii/description/

# 题目难度：中等
# 有一堆石头，每块石头的重量都是正整数。
# 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
# 1. 如果 x == y，那么两块石头都会被完全粉碎；
# 2. 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
# 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。

# 示例：
# 输入：[2,7,4,1,8,1]
# 输出：1

# 解释：
# 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
# 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
# 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
# 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。

# 示例 2：
# 输入：stones = [31,26,33,21,40]
# 输出：5

# 提示：
# 1 <= stones.length <= 30
# 1 <= stones[i] <= 1000
from typing import List

class Solution:
    def __init__(self):
        self.ans = 0

    # 每次两个石头相减，最终剩下的应该是求平均，让被减的石头和接近平均值
    def lastStoneWeightII(self, stones: List[int]) -> int:
        _sum = sum(stones)
        taget = _sum // 2
        dp = [[0] * (taget + 1) for i in range(len(stones))]
        # 初始化
        for  i in range(taget + 1):
            if i < stones[0]:
                dp[0][i] = 0
            else:
                dp[0][i] = stones[0]
        
        for i in range(1, len(stones)):
            for j in range(1, taget + 1):
                if j - stones[i] < 0:
                    dp[i][j] = dp[i-1][j]
                else:
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j-stones[i]] + stones[i])
        m = dp[-1][-1]

        return _sum - 2 * m
        
if __name__ == "__main__":
    s = Solution()
    stones = [2,7,4,1,8,1]
    ans = s.lastStoneWeightII(stones)
    print(ans)