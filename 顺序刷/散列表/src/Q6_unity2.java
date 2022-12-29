import java.util.*;

public class Q6_unity2 {
    public static void main(String[] args) {

    }

    class Solution {
        //合并链表
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode h1 = new ListNode(0);
            ListNode p = h1;
            p.next = null;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            if (l1 != null) p.next = l1;
            if (l2 != null) p.next = l2;
            return h1.next;

        }

        //反转链表
        public ListNode reverseList(ListNode head) {
            ListNode h = new ListNode(-1);
            ListNode p = h;
            while (head != null) {
                p = head.next;
                head.next = h.next;
                h.next = head;
                head = p;
            }
            return h.next;
        }

        Set<Integer> set = new HashSet<>();

        public boolean findTarget(TreeNode root, int k) {
            if (root==null) return false;
            if (set.contains(k-root.val)) return true;
            set.add(root.val);
            return  findTarget(root.left, k) ||
            findTarget(root.right, k);
        }

        //递归反转二叉树
        public TreeNode invertTree(TreeNode root) {
            if (root==null) return null;
            TreeNode temp = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(temp);
            return root;
        }

        public void midOrder(TreeNode root) {
            //原始中序遍历
            Deque<TreeNode> deque = new ArrayDeque<>();
            TreeNode p = root;
            if (p!=null) deque.addLast(p);
            while(p!=null && !deque.isEmpty()) {
                if (p.left!=null) {
                    deque.addLast(p.left);
                    p = p.left;
                } else {
                    TreeNode temp = deque.pollLast();
                    if (temp.right!= null) deque.addLast(temp.right);
                }

            }
        }

        public void midOrder2(TreeNode root) {
            //改进中序遍历
            Deque<TreeNode> deque = new ArrayDeque<>();
            TreeNode p = root;
            while(p!=null) {
               deque.addLast(p);
               p = p.left;
            }
            while(!deque.isEmpty()) {
                // 弹出要遍历的节点
                p = deque.pollLast();
                if (p.right != null) {
                    p = p.right;
                    while (p != null) {
                        deque.addLast(p);
                        p = p.left;
                    }
                }
            }
        }

        //两数之和
        public boolean mfindTarget(TreeNode root, int k) {
            Deque<TreeNode> ld = new ArrayDeque<>(), rd = new ArrayDeque<>();
            TreeNode temp = root;
            while (temp != null) {
                ld.addLast(temp);
                temp = temp.left;
            }
            temp = root;
            while (temp != null) {
                rd.addLast(temp);
                temp = temp.right;
            }
            TreeNode l = ld.peekLast(), r = rd.peekLast();
            while (l.val < r.val) {
                int t = l.val + r.val;
                if (t == k) return true;
                if (t < k) l = getNext(ld, true);
                else r = getNext(rd, false);
            }
            return false;
        }
        //分别从左到右中序遍历，和右到左逆中序遍历，进行查找直至二指针相同
        TreeNode getNext2(Deque<TreeNode> d, boolean order) {
            TreeNode p = d.pollLast();
            //如果是正序遍历
            if (order) {
                p = p.right;
                while(p!=null) {
                    d.addLast(p);
                    p=p.left;
                }
            // 如果是逆序遍历
            } else {
                p = p.left;
                while(p!=null) {
                    d.addLast(p);
                    p = p.right;
                }
            }
            return d.peekLast();
        }

        TreeNode getNext(Deque<TreeNode> d, boolean isLeft) {
            //如果是正序遍历，下一个节点在右子树的左下方
            TreeNode node = isLeft ? d.pollLast().right : d.pollLast().left;
            //将右子树的节点入栈
            while (node != null) {
                d.addLast(node);
                node = isLeft ? node.left : node.right;
            }
            return d.peekLast();
        }
        // 贪心算法重合区间
        public int eraseOverlapIntervals(int[][] intervals) {
            int ans=0;
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0],o2[0]);
                }
            });
            int now = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int l = intervals[i][0];
                int h = intervals[i][1];
                if(now<=l) {
                    now = h;
                    continue;
                }
                if (h<=now) {
                    now=h;
                }
                ans+=1;
            }
            return ans;
        }
    }

}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}