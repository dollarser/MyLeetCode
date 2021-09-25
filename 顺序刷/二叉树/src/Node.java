import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// N叉树
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
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
        Node p = null, q = null;
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