# 45.跳跃游戏 II
# https://leetcode.cn/problems/jump-game-ii/description/

# 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

# 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，
# 你可以跳转到任意 nums[i + j] 处:
# 0 <= j <= nums[i] 
# i + j < n
# 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

# 示例:

# 输入: [2,3,1,1,4]
# 输出: 2
# 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳  1  步，然后跳  3  步到达数组的最后一个位置。
# 说明: 假设你总是可以到达数组的最后一个位置。
from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return 0

        # 当前能到的最远位置
        start = 0
        # 下一步能到的最远距离
        end = 0
        # 步数
        step = 0
        for i in range(len(nums)):
            # 更新当前能到的最远距离
            end = max(end, nums[i] + i)
            # 如果走到了当前能到达的最远位置，再往前需要增加一步
            # 且增加一步后，需要更新能到的最远位置
            if i == start:
                # 步数增加1
                step += 1
                # 更新当前能到的最远位置
                start = end
                # 如果最远位置到达最后一个，则提前结束
                if end >= len(nums)-1:
                    break
        return step
    
    
    def jump1(self, nums: List[int]) -> int:
        start = 0
        end = 0
        step = 0
        while(end < len(nums)):
            for i in range(start, end+1):
                start = i + 1
                if end < i + nums[i]:
                    end = i + nums[i]
            step += 1
            
        return step-1

if __name__ == "__main__":
    s = Solution()
    ans = s.jump([2,3,1,1,4])
    print(ans)
