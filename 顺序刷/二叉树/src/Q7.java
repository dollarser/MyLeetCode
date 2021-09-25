import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 637.二叉树的层平均值
 * 题目链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
 *
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 * 示例 1：
 *
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *
 * 提示：
 *
 * 节点值的范围在32位有符号整数范围内。
 */
public class Q7 {
    public static void main(String[] args) {
        Integer[] list = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.createTreeFromList(list);
        TreeNode.printTree(root);
        Solution solution = new Solution();
        List<Double> ans = solution.averageOfLevels(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> ans = new LinkedList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            if (root != null) {
                deque.offer(root);
            }
            while (!deque.isEmpty()) {
                int len = deque.size();
                double sum = 0;  //注意精度
                for (int i = 0; i < len; i++) {
                    root = deque.poll();
                    sum += root.val;
                    if (root.left != null) deque.offer(root.left);
                    if (root.right != null) deque.offer(root.right);
                }
                ans.add(1.0 * sum/len);
            }
            return ans;
        }
    }
}
