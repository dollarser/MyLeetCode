# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.left = left
        self.right = right
        self.val = val
    def __repr__(self):
        return "TreeNode({}, {}, {})".format(self.val, repr(self.left), repr(self.right))


class Solution(object):
    def isBalanced(self, root: TreeNode) -> bool:
        """
        :type root: TreeNode
        :rtype: bool
        """

        if self.get_high(root) == -1:
            return False
        else:
            return True

    
    def get_high(self, node: TreeNode) -> int:
        if node is None:
            return 0
        lh = self.get_high(node.left)
        rh = self.get_high(node.right)
        # 如果不是平衡树
        if lh == -1 or rh == -1 or abs(lh-rh)>1:
            return -1
        return max(lh, rh) + 1


if __name__ == "__main__":
    s = Solution()
    n1 = TreeNode(1)
    n2 = TreeNode(2)
    n3 = TreeNode(3)
    n4 = TreeNode(4)
    n5 = TreeNode(5)
    n6 = TreeNode(6)
    n7 = TreeNode(7)
    n8 = TreeNode(8)
    n9 = TreeNode(9)

    n1.left = n2
    n1.right = n3
    n2.left = n4
    n2.right = n5
    n3.left = n6
    n3.right = n7
    n4.left = n8
    n4.right = n9

    print(s.isBalanced(n1)) 