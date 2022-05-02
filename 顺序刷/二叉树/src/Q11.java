import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树层序遍历登场！
 * 117.填充每个节点的下一个右侧节点指针II
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有next 指针都被设置为 NULL。
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class Q11 {
    public static void main(String[] args) {
        Integer[] list = {1,2,3,4,5,null,7};
        Node root = Node.createBiTreeFromList(list);
        Node.printBiTree(root);
        Solution solution = new Solution();
        Node ans = solution.connect(root);
        Node.printBiTree(ans.right);
//        System.out.println(ans.toString());
    }
    static class Solution {
        public Node connect(Node root) {
            if (root == null) return null;
            Deque<Node> deque = new LinkedList<>();
            deque.offer(root);
            Node p, q=root;
            int layerLen = 1, nextLayerLen = 0;
            int count = 0;
            while(!deque.isEmpty()) {
                // 出队
                p = deque.poll();
                count++;
                //入队左子树
                if(p.left != null) {
                    deque.offer(p.left);
                    nextLayerLen++;
                }
                //入队右子树
                if(p.right != null) {
                    deque.offer(p.right);
                    nextLayerLen++;
                }
                if(count == layerLen) {
                    layerLen = nextLayerLen;
                    nextLayerLen = 0;
                    count = 0;
                    p.next = null;
                } else {
                    p.next = deque.peek();
                }
            }
            return root;
        }
    }
}
