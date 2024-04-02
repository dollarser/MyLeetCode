import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历登场！
 * 199.二叉树的右视图
 * 题目链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 */
public class Q6 {
    public static void main(String[] args) {
        Integer[] list = {1,2,3,null,5,null,4};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<Integer> ans = solution.rightSideView(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            if (root != null) {
                deque.offer(root);
            }
            while (!deque.isEmpty()) {
                int len = deque.size();
                // 先右后左遍历，每层第一个就是右视图能看到的元素
                ans.add(deque.peek().val);
                for (int i = 0; i < len; i++) {
                    root = deque.poll();
                    if (root.right != null) deque.offer(root.right);
                    if (root.left != null) deque.offer(root.left);
                }
            }
            return ans;
        }
    }
}
