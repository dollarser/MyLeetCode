"""
494.目标和
https://leetcode.cn/problems/target-sum/description/

难度：中等

给你一个非负整数数组 nums 和一个整数 target 。
向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。


示例1：
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

示例2：
输入：nums = [1], target = 1
输出：1
提示：

数组非空，且长度不会超过 20 。
初始的数组的和不会超过 1000 。
保证返回的最终结果能被 32 位整数存下。
"""
from typing import List


class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        nums_sum = sum(nums)
        if nums_sum-target < 0 or (nums_sum-target) % 2 != 0: 
            return 0
        else:
            end = (nums_sum-target) // 2 + 1
        # 定义dp数组 （物体种类数+1） * （背包容量+1）
        dp = [[0] * end for i in range(len(nums)+1)]
        # 初始化dp数组 容量为0时有一种方法，就是不放，dp[i][0]=1; 其他位置默认为0
        for i in range(len(nums)+1):
            dp[i][0] = 1
        # dp状态转移，注意dp[i]对应的是nums[i-1]
        for i in range(1, len(nums)+1):
            for j in range(end):
                if j-nums[i-1] < 0:
                    dp[i][j] = dp[i-1][j]
                else:
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]
        return dp[len(nums)][end-1]


if __name__ == "__main__":
    s = Solution()
    nums = [1,2]
    target = 2
    n = s.findTargetSumWays(nums, target)
    print(n)