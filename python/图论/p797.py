# 797.所有可能的路径
# https://leetcode.cn/problems/all-paths-from-source-to-target/description/

# 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
# graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。


# 示例：
# 输入：graph = [[1,2],[3],[3],[]]
# 输出：[[0,1,3],[0,2,3]]
# 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3


# 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
# 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


# 提示：
# n == graph.length
# 2 <= n <= 15
# 0 <= graph[i][j] < n
# graph[i][j] != i（即不存在自环）
# graph[i] 中的所有元素 互不相同
# 保证输入为 有向无环图（DAG）
from typing import List

class Solution:
    def __init__(self):
        self.ans = []
        # path不设为全局变量就会出错
        self.path = [0]

    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        self.dfs1(0, graph, self.path)
        # self.dfs1(0, graph, [0]) # 报错ß
        return self.ans

    def dfs(self, id: int, graph: List[List[int]], path: List[int]):
        if id == len(graph)-1:
            path.append(id)
            self.ans.append(list(path))
            path.pop()
            return
        path.append(id)
        for i in graph[id]:
            self.dfs(i, graph, path)
        path.pop()

    def dfs1(self, id: int, graph: List[List[int]], path: List[int]):
        if id == len(graph)-1:
            self.ans.append(list(path))
            return
        
        for i in graph[id]:
            path.append(i)
            self.dfs1(i, graph, path)
            path.pop()


if __name__ == "__main__":
    graph = [[4,3,1],[3,2,4],[3],[4],[]]
    s = Solution()
    ans = s.allPathsSourceTarget(graph)
    print(ans)
