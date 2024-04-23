import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * N叉树
 * 二叉树
 * 完全二叉树
 * Definition for a Node.
 */

class Node {
    public int val;
    public List<Node> children;
    // 完美(满)二叉数
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }
    // 普通树
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    /**
     * 普通二叉树
     */
    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    static Node createBiTreeFromList(Integer[] list) {
        if (list.length == 0 || list[0] == null) {
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        Node h = new Node(list[0]);
        Node p = null;
        deque.offer(h);
        int i = 1;
        while(!deque.isEmpty()) {
            p = deque.poll();
            if (i >= list.length || list[i] == null) {
                p.left = null;
            } else {
                p.left = new Node(list[i]);
                deque.offer(p.left);
            }
            //if (p.left != null) System.out.println(p.left.val);
            ++i;
            if (i >= list.length || list[i] == null) {
                p.right = null;
            } else {
                p.right = new Node(list[i]);
                deque.offer(p.right);
            }
            ++i;
        }
        return h;
    }
    static void printBiTree(Node root) {
        if (root == null) return;
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Node> deque = new LinkedList<>();
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

    /**
     * 完美(满)二叉树
     */
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    /**
     * 构造完美二叉树
     */
    static Node createPerfectTreeFromList(Integer[] list) {
        if (list.length == 0 || list[0] == null) return null;
        Deque<Node> deque = new LinkedList<>();
        Node root = new Node(list[0]);
        Node p = null;
        deque.offer(root);
        int i = 1;
        while(!deque.isEmpty()) {
            p = deque.poll();
            if (i >= list.length || list[i] == null) {
                p.left = null;
            } else {
                p.left = new Node(list[i]);
                deque.offer(p.left);
            }
            ++i;
            if (i >= list.length || list[i] == null) {
                p.right = null;
            } else {
                p.right = new Node(list[i]);
                deque.offer(p.right);
            }
            ++i;
        }
        return root;
    }
    /**
     * 层次打印完美二叉树
     * @param root
     */
    static void printPerfectTree(Node root) {
        if (root == null) return;
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Node> deque = new LinkedList<>();
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

    /**
     * 构造多叉树
     * @param list
     * @return
     */
    static Node createTreeFromList(Integer[] list) {
        if (list.length == 0 || list[0] == null) return null;

        Deque<Node> deque = new LinkedList<>();
        Node root = new Node(list[0]);
        Node p = null;
        deque.offer(root);

        List<Node> children = new LinkedList<>();
        for (int i = 2; i < list.length; i++) {
            if (list[i] != null) {
                p = new Node(list[i]);
                deque.offer(p);
                children.add(p);
            } else {
                p = deque.poll();
                p.children = children;
                children = new LinkedList<>();
            }
        }
        p = deque.poll();
        p.children = children;
        return root;
    }

    /**
     * 层次打印多叉树
     * @param root
     */
    static void printTree(Node root) {
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
        System.out.println(ans);
    }
}