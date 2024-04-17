# 416. 分割等和子集
# https://leetcode.cn/problems/partition-equal-subset-sum/description/

# 题目难易：中等

# 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
# 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200

# 示例 1:

# 输入: [1, 5, 11, 5]
# 输出: true
# 解释: 数组可以分割成 [1, 5, 5] 和 [11].
# 示例 2:

# 输入: [1, 2, 3, 5]
# 输出: false
# 解释: 数组不能分割成两个元素和相等的子集.
# 提示：

# 1 <= nums.length <= 200
# 1 <= nums[i] <= 100
from typing import List

class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        sums = sum(nums)
        if sums % 2 != 0:
            return False
        
        half = sums // 2
        dp = [[0 for _ in range(half + 1)] for _ in range(len(nums))] 
        for i in range(half + 1):
            if i >= nums[0]:
                dp[0][i] = nums[0]
        
        for i in range(len(nums)):
            for j in range(1, half + 1):
                if j - nums[i] >= 0:
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j-nums[i]] + nums[i])
                else:
                    dp[i][j] = dp[i-1][j]
        for i in range(len(nums)):
            if dp[i][half] == half:
                return True
        return False
    
    def canPartition1(self, nums: List[int]) -> bool:
        
        total_sum = sum(nums)

        if total_sum % 2 != 0:
            return False

        target_sum = total_sum // 2
        dp = [[False] * (target_sum + 1) for _ in range(len(nums) + 1)]

        # 初始化第一行（空子集可以得到和为0）
        for i in range(len(nums) + 1):
            dp[i][0] = True

        for i in range(1, len(nums) + 1):
            for j in range(1, target_sum + 1):
                if j < nums[i - 1]:
                    # 当前数字大于目标和时，无法使用该数字
                    dp[i][j] = dp[i - 1][j]
                else:
                    # 当前数字小于等于目标和时，可以选择使用或不使用该数字
                    dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i - 1]]

        return dp[len(nums)][target_sum]

if __name__ == "__main__":
        s = Solution()
        s.canPartition()
