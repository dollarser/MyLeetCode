/**
 * 19.删除链表的倒数第N个节点
 * 题目链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class Q5 {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        int n = 2;

        ListNode head = new ListNode(arr);
        ListNode.linkPrint(head);
        Solution solution = new Solution();
        solution.removeNthFromEnd(head, n);
        ListNode.linkPrint(head);
    }

     static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode p, q, h;
            h = new ListNode(0, head);
            q = h;
            p = h.next;
            int i = 0;
            while(p != null) {
                if(i >= n) {
                    q = q.next;
                }
                p = p.next;
                i++;
            }
            q.next = q.next.next;
            return h.next;
        }
    }
}
