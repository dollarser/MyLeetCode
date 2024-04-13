# 84.柱状图中最大的矩形
# 力扣题目链接

# 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
# 求在该柱状图中，能够勾勒出来的矩形的最大面积。

# 示例1：
# 输入：heights = [2,1,5,6,2,3]
# 输出：10
# 解释：最大的矩形为图中红色区域，面积为 10

# 示例2:
# 输入： heights = [2,4]
# 输出： 4

# 提示
# 1 <= heights.length <=10^5
# 0 <= heights[i] <= 10^4
from typing import List

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        '''
        单调栈，分析使用递增还是递
        本题是左右两边第一个小于当前柱子的柱子，因此需要使用单调增栈
        因为单调增栈，栈中左边显然是第一个小于当前柱子的柱子，
        栈中右边如果大于当前柱子，直接入栈，如果小于当前柱子，则找到小于当前柱子的第一根柱子
        '''
        ans = 0
        size = len(heights)
        if size == 0:
            return ans
        
        # 数组尾部加个0，方便处理最后一个数据
        heights.append(0)
        stack = []
        stack.append(0)
        for i in range(1, len(heights)):
            if heights[i] > heights[stack[-1]]:
                stack.append(i)
                continue
            # 如果相等，则应该把后一个入栈，因为要标记需要求的矩形的左边界
            if heights[i] == heights[stack[-1]]:
                stack.pop()
                stack.append(i)
                continue
            # 遇到大于当前柱子的柱子，求以栈顶前三个元素构成的最大矩形
            while len(stack) > 0 and heights[i] < heights[stack[-1]]:
                left = -1
                h = heights[stack[-1]]
                stack.pop()
                if len(stack) > 0:
                    left = stack[-1]
                w = i - left -1
                ans = max(ans, w * h)
            stack.append(i)
                
        return ans

    '''
    和接雨水类似
    可以用双指针，可以用单调栈
    '''
    def largestRectangleArea1(self, heights: List[int]) -> int:
        '''
        暴力法：
            每个柱子可以和他左右两边不小于自己的柱子组成一个立方体
            高即是该柱子
            宽是right-left-1
        '''
        cnt = 0
        for i in range(len(heights)):
            left = i - 1
            right = i + 1
            while left >= 0 and heights[left] >= heights[i]:
                left -= 1
            while right < len(heights) and heights[right] >= heights[i]:
                right += 1
            h = heights[i]
            w = right - left - 1

            cnt += h * w
        return cnt


    def largestRectangleArea2(self, heights: List[int]) -> int:
        '''
        提前遍历
        '''
        cnt = 0
        lefts = [0] * len(heights)
        rights = [0] * len(heights)
        for i in range(len(heights)):
            left = i - 1
            while left >= 0 and heights[left] >= heights[i]:
                left -= 1
            lefts[i] = left
            right = i + 1
            while right < len(heights) and heights[right] >= heights[i]:
                right += 1
            rights[i] = right
        
        for i in range(len(heights)):
            w = rights[i] - lefts[i] - 1
            h = heights[i]
            cnt = max(w * h, cnt)

        return cnt

if __name__ == "__main__":
    s = Solution()
    ans = s.largestRectangleArea([2, 4])
    print(ans)
