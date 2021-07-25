/**
 * 142.环形链表II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 题意： 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 */
public class Q7 {
    public static void main(String[] args) {
        int[] head_ = {3,2,0,-4};
        int pos = 1;


        ListNode head = new ListNode(head_);
        ListNode.cycle(head, pos);

        Solution solution = new Solution();
        ListNode ans = solution.detectCycle(head);
        ListNode.linkPrint(ans);
    }

    static public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {// 有环
                    ListNode index1 = fast;
                    ListNode index2 = head;
                    /**
                     * 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                     */
                    while (index1 != index2) {
                        index1 = index1.next;
                        index2 = index2.next;
                    }
                    return index1;
                }
            }
            return null;
        }
    }
}
