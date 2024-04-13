from typing import List

# 149. 直线上最多的点数
# 困难
# 相关标签
# 相关企业
# 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。

# 示例 1：
# 输入：points = [[1,1],[2,2],[3,3]]
# 输出：3
# 示例 2：

# 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
# 输出：4
 

# 提示：
# 1 <= points.length <= 300
# points[i].length == 2
# -104 <= xi, yi <= 104
# points 中的所有点 互不相同

class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        size = len(points)
        if size < 3:
            return size
        ans = 0
        for i in range(size):
            for j in range(i+1, size):
                sum = 2
                for k in range(size):
                    if k != j and k != i:
                        if (points[k][0]-points[i][0])*(points[j][1]-points[i][1]) == \
                            (points[k][1]-points[i][1]) * (points[j][0]-points[i][0]):
                            sum += 1
                ans = max(ans, sum) 
                
        return ans
