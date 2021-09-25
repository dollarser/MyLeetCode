import java.util.ArrayList;
import java.util.List;

/**
 * 94.二叉树的中序遍历
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 */
public class Q2 {
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
            inorder(ans, root);
            return ans;
        }
        private void inorder(List<Integer> ans, TreeNode root) {
            if (root == null) return;
            inorder(ans, root.left);
            ans.add(root.val);
            inorder(ans, root.right);
        }
    }
}
