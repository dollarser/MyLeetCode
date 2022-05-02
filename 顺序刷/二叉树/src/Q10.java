/**
 * 二叉树层序遍历登场！
 * 116.填充每个节点的下一个右侧节点指针
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Q10 {
    public static void main(String[] args) {
        Integer[] list = {1,2,3,4,5,6,7};
        Node root = Node.createPerfectTreeFromList(list);
        Node.printPerfectTree(root);
        Solution solution = new Solution();
        Node ans = solution.connect(root);
        Node.printPerfectTree(ans.right.next);
    }
    static class Solution {
        public Node connect(Node root) {
            if (root == null) return null;
            Node nextHead = null;
            Node p = root, q = null;
            p.next = null;
            int nowLayer = 0;
            int layer = -1;
            while (nowLayer != layer) {
                layer = nowLayer;
                while(p != null) {
                    if (p.left != null) {
                        if (q == null) {
                            nextHead = p.left;
                            nowLayer++;
                        } else {
                            q.next = p.left;
                        }
                        q = p.left;
                    }
                    if (p.right != null) {
                        if (q == null) {
                            nextHead = p.right;
                            nowLayer++;
                        } else {
                            q.next = p.right;
                        }
                        q = p.right;
                    }
                    p = p.next;
                }
                q = null;
                p = nextHead;
            }
            return root;
        }
    }
}
