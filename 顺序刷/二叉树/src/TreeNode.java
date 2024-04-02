import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 根据数组创建二叉树
     * @param list
     * @return
     */
    static TreeNode createTreeFromList(Integer[] list) {
        if (list.length == 0 || list[0] == null) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode h = new TreeNode(list[0]);
        TreeNode p = null;
        deque.offer(h);
        int i = 1;
        while(!deque.isEmpty()) {
            p = deque.poll();
            if (i >= list.length || list[i] == null) {
                p.left = null;
            } else {
                p.left = new TreeNode(list[i]);
                deque.offer(p.left);
            }
            ++i;
            if (i >= list.length || list[i] == null) {
                p.right = null;
            } else {
                p.right = new TreeNode(list[i]);
                deque.offer(p.right);
            }
            ++i;
        }
        return h;
    }

    /**
     * 层次打印 二叉树
     * @param root
     */
    static void printTree(TreeNode root) {
        if (root == null) return;
        List<List<Integer>> ans = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            int len = deque.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                root = deque.poll();
                temp.add(root.val);
                if (root.left != null) deque.offer(root.left);
                if (root.right != null) deque.offer(root.right);
            }
            ans.add(temp);
        }
        System.out.println(ans);
    }
}
