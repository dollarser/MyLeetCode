import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历登场！
 * 107. 二叉树的层序遍历 II
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q5 {
    public static void main(String[] args) {
        Integer[] list = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.levelOrderBottom(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
                ans.add(0, temp); //头插法
            }
            return ans;
        }
    }
}
