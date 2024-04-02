import java.util.ArrayList;
import java.util.List;

/**
 * 145.二叉树的后序遍历
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/h
 * 给定一个二叉树的根节点 root ，返回它的 后序 遍历。
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Q3 {
    public static void main(String[] args) {
        Integer[] list = {1,null,2,3};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<Integer> ans = solution.postorderTraversal(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            postorder(ans, root);
            return ans;
        }
        private void postorder(List<Integer> ans, TreeNode root) {
            if (root == null) return;
            postorder(ans, root.left);
            postorder(ans, root.right);
            ans.add(root.val);
        }
    }
}
