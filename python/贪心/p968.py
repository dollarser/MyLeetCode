# 968.监控二叉树
# https://leetcode.cn/problems/binary-tree-cameras/description/

# 给定一个二叉树，我们在树的节点上安装摄像头。
# 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
# 计算监控树的所有节点所需的最小摄像头数量。

# 示例 1：

# 输入：[0,0,null,0,0]
# 输出：1
# 解释：如图所示，一台摄像头足以监控所有节点。
# 示例 2：

# 输入：[0,0,null,0,null,0,null,null,0]
# 输出：2
# 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

# 提示：
# 给定树的节点数的范围是 [1, 1000]。
# 每个节点的值都是 0。
from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    '''
    思虑：二叉树越往下节点越多，一个监控节点可以监控上中下三层
    不应该把监控放叶子节点，需要放在叶子节点的上一层节点
    局部最优推全局最优

    来看看这个状态应该如何转移，先来看看每个节点可能有几种状态：

    有如下三种：
        该节点无覆盖
        本节点有摄像头
        本节点有覆盖
    我们分别有三个数字来表示：
        0：该节点无覆盖
        1：本节点有摄像头
        2：本节点有覆盖

    使用后续遍历：
        如果左右子树均覆盖或为空，本节点不放摄像头
        如果左右子树有一个没覆盖，本节点放摄像头
    '''
    ans = 0
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        
        def traversal(root):
            '''
            0：该节点无覆盖
            1：本节点有摄像头
            2：本节点有覆盖
            '''
            # 如果节点为空，默认为覆盖
            flag1 = 2
            flag2 = 2
            if root.left is not None:
                flag1 = traversal(root.left)
            if root.right is not None:
                flag2 = traversal(root.right)

            if flag1 == 2 and flag2 == 2:
                return 0
            if flag1 == 0 or flag2 == 0:
                self.ans = self.ans + 1
                return 1
            if flag1 == 1 or flag2 == 1:
                return 2

        flag = traversal(root)
        if flag == 0:
            self.ans += 1
        
        return self.ans

if __name__ == "__main__":
    s = Solution()
    s.minCameraCover()