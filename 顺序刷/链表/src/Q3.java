/**
 * 206.反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 题意：反转一个单链表。
 *
 * 示例: 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
 * head = [1,2,3,4,5]
 */
public class Q3 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int[] arr) {
            val = arr[0];
            next = null;

            for (int i = 1; i < arr.length; i++) {
                ListNode n = new ListNode(arr[i]);

            }
            this.val = val; this.next = next;
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode h = null;
            ListNode p = head, q = null;
            while(p != null) {
                q = p.next;
                p.next = h;
                h = p;
                p = q;
            }
            return h;
        }
    }
}
