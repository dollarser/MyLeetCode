# 200. 岛屿数量
# https://leetcode.cn/problems/number-of-islands/description/

# 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
# 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
# 此外，你可以假设该网格的四条边均被水包围。


# 示例1:
# 输入：grid = [
#   ["1","1","1","1","0"],
#   ["1","1","0","1","0"],
#   ["1","1","0","0","0"],
#   ["0","0","0","0","0"]
# ]
# 输出：1

# 示例2:
# 输入：grid = [
#   ["1","1","0","0","0"],
#   ["1","1","0","0","0"],
#   ["0","0","1","0","0"],
#   ["0","0","0","1","1"]
# ]
# 输出：3

# 提示：
# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 300
# grid[i][j] 的值为 '0' 或 '1'
from typing import List

class Solution:
    # 使用深搜或广搜遍历的方法，
    # 需要几次遍历能遍历完所有点就有多少岛屿
    def __init__(self):
        self.visited = []
        # 重点，提供了List将多种情况统一
        self.dir = [[1, 0], [-1, 0], [0, 1], [0, -1]]

    def numIslands(self, grid: List[List[str]]) -> int:
        self.visited = [[0 for i in grid[0] ] for j in grid]
        cnt = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1' and self.visited[i][j] != 1:
                    self.dfs(grid, i, j)
                    cnt += 1
                else:
                    self.visited[i][j] = 1
        return cnt
        

    def dfs(self, grid, row, hoc):
        for x, y in self.dir:
            nextx = x + row
            nexty = y + hoc
            # 临界值判断
            if nextx < 0 or nextx > len(grid) - 1 or nexty < 0 or nexty > len(grid[0]) - 1:
                continue
            # 条件作为终止条件
            if self.visited[nextx][nexty] == 0 and grid[nextx][nexty] == '1':
                self.visited[nextx][nexty] = 1
                self.dfs(grid, nextx, nexty)

            
if __name__ == "__main__":
    grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
    ]
    s = Solution()
    ans = s.numIslands(grid)
    print(ans)