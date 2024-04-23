import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 先序遍历，非递归方式
 */
public class Q1_1 {
    public static void main(String[] args) {
        Integer[] list = {1,null,2,3};
        TreeNode root = TreeNode.createTreeFromList(list);
        Solution solution = new Solution();
        List<Integer> ans = solution.preorderTraversal(root);
        System.out.println(ans.toString());
    }
    static class Solution {
        Deque<TreeNode> deque;
        public List<Integer> preorderTraversal(TreeNode root) {
            // 使用栈 先序遍历 二叉树
            List<Integer> ans = new ArrayList<>();
            if (root == null) {
                return ans;  //树为空，返回空数组，而不是null
            }
            deque = new LinkedList<>();
            TreeNode p = root;
            ans.add(p.val);
            deque.push(p);
            //入栈时遍历，出栈时判断是否有右子树，有则将右子树入栈，无则出栈
            while (!deque.isEmpty()) {
                //p == null 表示该树左子树遍历完毕
                while (p != null && p.left != null) {
                    p = p.left;
                    ans.add(p.val);
                    deque.push(p);
                }
                p = deque.pop();
                if (p.right != null){
                    p = p.right;
                    ans.add(p.val);
                    deque.push(p);
                } else {
                    p = null;
                }

            }
            return ans;
        }
    }
}
