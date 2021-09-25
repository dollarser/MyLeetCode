import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/** 非递归方式后序遍历
 */
public class Q3_1 {
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
            if (root == null) return ans;

            Deque<TreeNode> deque = new LinkedList<>();
            TreeNode p = root;
            TreeNode q = root;
            deque.push(p);
            // 后序遍历，左右子树都遍历完成，才遍历根，此时单纯用一个遍历引用p == null
            // 不足以用来表示左右子树是否遍历完成，用q表示右子树是否遍历
            while (!deque.isEmpty()) {
                while (p != null && p.left != null) {
                    p = p.left;
                    deque.push(p);
                }
                p = deque.peek();
                // q保存着已经遍历的节点，如果当前节点p.right != q，说明右子树还没遍历
                if (p.right != q && p.right != null) {
                    p = p.right;
                    deque.push(p);
                } else {
                    p = deque.pop();
                    ans.add(p.val);
                    q = p;
                    p = null; //栈顶左子树已经遍历完成
                }
            }
            return ans;
        }
    }
}
