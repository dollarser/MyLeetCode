import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102.二叉树的层序遍历
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class Q4 {
    public static void main(String[] args) {
        Integer[] list = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.levelOrder(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            if (root != null) {
                deque.push(root);
            }

            while (!deque.isEmpty()) {
                int len = deque.size();
                List<Integer> temp = new LinkedList<>();
                for (int i = 0; i < len; i++) {
                    root = deque.poll(); // 队列为空，返回null
                    temp.add(root.val);
                    if (root.left != null) {
                        deque.offer(root.left);
                    }
                    if (root.right != null) {
                        deque.offer(root.right);
                    }
                }
                ans.add(temp);
            }
            return ans;
        }
    }
}
