/**
 * 144. 二叉树的前序遍历
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {
        Integer[] list = {1,null,2,3};
        TreeNode root = TreeNode.createTreeFromList(list);
        // TreeNode.printTree(root);
        Solution solution = new Solution();
        List<Integer> ans = solution.preorderTraversal(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            // 递归方法 先序遍历
            List<Integer> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            preorder(ans, root);
            return ans;
        }
        private void preorder(List<Integer> ans, TreeNode root) {
            if (root == null) return;
            ans.add(root.val);
            preorder(ans, root.left);
            preorder(ans, root.right);
        }
    }
}
