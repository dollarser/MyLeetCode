# 42. 接雨水
# https://leetcode.cn/problems/trapping-rain-water/description/

# 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
# 示例 1：

# 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
# 输出：6
# 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
# 示例 2：

# 输入：height = [4,2,0,3,2,5]
# 输出：9
from typing import List

class Solution:
    '''
    按行求，使用单调栈
    使用一个递减的单调栈：
    如果当前元素小于栈顶元素，将该元素加入
    如果当前元素等于栈顶元素，将元素下标更新，因为求水的时候左边的墙要用相等中最右边的
    如果当前元素大于栈顶元素，将当前元素和栈顶元素前一个组成容器计算容积，
    之后将栈顶元素弹出，再次比较当前元素与栈顶元素的关系
    '''
    def trap(self, height: List[int]) -> int:
        cnt = 0
        if len(height) < 3:
            return cnt
        
        stack = []
        stack.append(0)
        for i in range(1, len(height)):
            if height[i] < height[stack[-1]]:
                stack.append(i)
            if height[i] == height[stack[-1]]:
                stack.pop()
                stack.append(i)
            while(len(stack)>0 and height[i] > height[stack[-1]]):
                idx = stack.pop()
                if len(stack) > 0:
                    h = min(height[i], height[stack[-1]]) - height[idx]
                    # 注意宽度需要再减1，因为两边都是墙壁
                    w = i - stack[-1] - 1
                    cnt += h * w
            stack.append(i)

        return cnt
    
    '''
    按列求，可以用双指针法, 找某个节点左右最大的节点，易超时
    '''
    def trap2(self, height: List[int]) -> int:
        cnt = 0
        if len(height) < 3:
            return cnt

        # 暴力遍历
        for i in range(len(height)):
            left = height[i]
            right = height[i]
            for j in range(0, i):
                left = max(left, height[j])
            for j in range(i, len(height)):
                right = max(right, height[j])

            cnt += min(left, right) - height[i]
        
        return cnt

    '''
    双指针优化，不超时
    '''
    def trap3(self, height: List[int]) -> int:
        cnt = 0
        if len(height) < 3:
            return cnt

        # 提前查找每个柱子左右两边最大值
        max_left = []
        max_right = []
        max_l = height[0]
        max_r = height[-1]
        for i in range(len(height)):
            max_l = max(max_l, height[i])
            max_left.append(max_l)
        for i in range(len(height)-2, -1, -1):
            max_r = max(max_r, height[i])
            max_right.append(max_r)
        for i in range(len(height)):
            cnt += min(max_left[i], max_right[len(height) - i - 1]) - height[i]
        
        return cnt