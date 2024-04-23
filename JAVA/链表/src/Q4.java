import java.util.Arrays;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Q4 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public static void main(String[] args) {
        ListNode h = null;
        int head[] = {1,2,3,4};
        h = new ListNode(head[0]);
        ListNode p = h;
        for(int i=1; i< head.length; ++i) {
            p.next = new ListNode(head[i]);
            p = p.next;
        }
        Solution solution = new Solution();
        ListNode ans = solution.swapPairs(h);
        while(ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if(head == null) return null;
            ListNode h = new ListNode(0, head);
            ListNode p = h;
            ListNode q = head.next;
            while(q != null) {
                p.next.next = q.next;
                q.next = p.next;
                p.next = q;


                p = q.next;
                if(p.next != null) {
                    q = p.next.next;
                } else {
                    q = null;
                }
            }
            return h.next;
        }
    }
}
