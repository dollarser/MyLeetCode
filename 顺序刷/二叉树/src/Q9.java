import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历登场！
 * 515.在每个树行中找最大值
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/submissions/
 *
 * 您需要在二叉树的每一行中找到最大的值。
 * 示例：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 */
public class Q9 {
    public static void main(String[] args) {
        Integer[] list = {1, 3, 2, 5, 3, null, 9};
        TreeNode root = TreeNode.createTreeFromList(list);
        TreeNode.printTree(root);
        Solution solution = new Solution();
        List<Integer> ans = solution.largestValues(root);
        System.out.println(ans.toString());
    }

    /**
     * 通过队列中插入null区分不同层，也可以通过计数的方式区分不同层
     */
    static class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List ans = new LinkedList();
            if(root == null) {
                return ans;
            }
            TreeNode p = root;
            Deque<TreeNode> deque = new LinkedList();
            deque.offer(p);
            deque.offer(null);
            int maxTemp = p.val;
            while(!deque.isEmpty()) {
                p = deque.poll();
                if(p == null) {
                    ans.add(maxTemp);
                    maxTemp = Integer.MIN_VALUE;
                    if (!deque.isEmpty()) {
                        deque.offer(null);
                    }
                } else {
                    if (p.val > maxTemp) {
                        maxTemp = p.val;
                    }
                    if(p.left != null) deque.offer(p.left);
                    if(p.right != null) deque.offer(p.right);
                }
            }

            return ans;
        }
    }
}
