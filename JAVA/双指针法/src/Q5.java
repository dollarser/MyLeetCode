
/**
 * 206.反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 题意：反转一个单链表。
 *
 * 示例: 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
 *
 * 关键点：头插法 + 定义头节点，或者理解成双指针，每次将后一个指针反转指向前一个指针
 */
public class Q5 {
    public static void main(String[] args) {
        int head_[] = {1,2,3,4,5};
        ListNode head = new ListNode(head_);
        Solution solution = new Solution();
        ListNode ans = solution.reverseList(head);
        ListNode.linkPrint(ans);
        System.out.println(ans);
    }

    static class Solution {
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
