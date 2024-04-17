from typing import List
from collections import deque

class Solution:
    # 使用广搜遍历的方法，
    # 需要几次遍历能遍历完所有点就有多少岛屿
    def __init__(self):
        self.visited = []
        # 重点，提供了List将多种情况统一
        self.dir = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        self.queue = deque()

    def numIslands(self, grid: List[List[str]]) -> int:
        self.visited = [[0 for i in grid[0] ] for j in grid]
        cnt = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if self.visited[i][j] == 0 and grid[i][j] == '1':
                    self.queue.append([i, j])
                    self.visited[i][j] = 1
                    self.bfs(grid)
                    cnt += 1
        return cnt
        

    def bfs(self, gird: List[List[int]]):
        while self.queue:
            row, col = self.queue.popleft()
            for i, j in self.dir:
                nextRow = row + i
                nextCol = col + j
                if nextRow < 0 or nextRow > len(gird) - 1 or \
                    nextCol < 0 or nextCol > len(gird[0]) - 1:
                    continue
                if self.visited[nextRow][nextCol] == 0 and gird[nextRow][nextCol] == '1':
                    self.queue.append([nextRow, nextCol])
                    self.visited[nextRow][nextCol] = 1
                    self.bfs(gird)

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