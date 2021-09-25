import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 429.N叉树的层序遍历
 * 题目链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 */
public class Q8 {
    public static void main(String[] args) {
        Integer[] list = {1,null,3,2,4,null,5,6};
        Node root = Node.createTreeFromList(list);
        Node.printTree(root);
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.levelOrder(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> ans = new LinkedList<>();
            Deque<Node> deque = new LinkedList<>();
            if (root != null) {
                deque.offer(root);
            }
            Node p = null;
            while(!deque.isEmpty()) {
                int len = deque.size();
                List<Integer> temp = new LinkedList<>();
                for (int i = 0; i < len; i++) {
                    root = deque.poll();
                    temp.add(root.val);
                    if (root.children != null) {
                        for (int j = 0; j < root.children.size(); j++) {
                            p = root.children.get(j);
                            deque.offer(p);
                        }
                    }
                }
                ans.add(temp);
            }
            return ans;
        }
    }
}
