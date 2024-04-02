/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 226. 翻转二叉树
 * 示例：
 *       4
 *     /  \
 *    2    7
 *  /  \  /  \
 * 1   3 6    9
 *
 *       4
 *     /  \
 *    7    2
 *  /  \  /  \
 * 9   6 3    1
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 */
public class Q14 {
    public static void main(String[] args) {
        Integer[] list = {1,2,3,4,5,null,7};
        TreeNode root = TreeNode.createTreeFromList(list);
        TreeNode.printTree(root);
        Solution solution = new Solution();
        TreeNode ans = solution.invertTree(root);
        TreeNode.printTree(ans.right);
//        System.out.println(ans.toString());
    }
    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            //递归函数的终止条件，节点为空时返回
            if(root==null) {
                return null;
            }
            //下面三句是将当前节点的左右子树交换
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;

            //递归交换当前节点的 左子树
            invertTree(root.left);
            //递归交换当前节点的 右子树
            invertTree(root.right);
            //函数返回时就表示当前这个节点，以及它的左右子树
            //都已经交换完了
            return root;
        }
    }
}
