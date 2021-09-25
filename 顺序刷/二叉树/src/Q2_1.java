import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 94.二叉树的中序遍历
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 */
public class Q2_1 {
    public static void main(String[] args) {
        Integer[] list = {1,null,2,3};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<Integer> ans = solution.inorderTraversal(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            TreeNode p = root;
            Deque<TreeNode> deque = new LinkedList<>();
            // 入栈同时左子树入栈，出栈时遍历，同时将右子树入栈
            deque.push(p);
            while ( !deque.isEmpty() ) {
                while(p != null && p.left != null) {
                    p = p.left;
                    deque.push(p);
                }
                p = deque.pop();
                ans.add(p.val);
                if ( p.right != null ) {
                    p = p.right;
                    deque.push(p);
                } else {
                    p = null;
                }
            }
            return ans;
        }
    }
}
