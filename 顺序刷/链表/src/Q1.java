/**
 * 203.移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * 题意：删除链表中等于给定值 val 的所有节点。
 *
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
public class Q1 {
    public static void main(String[] args) {
        int headArr[] = {1,2,6,3,4,5,6};
        int val = 6;
        ListNode head = new ListNode(headArr);
        Solution solution = new Solution();
        ListNode newHead = solution.removeElements(head, val);
        while(newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
    static class Solution {
        public ListNode removeElements(ListNode head, int val) {
            //h是虚拟头节点，为了方便操作
            ListNode h = new ListNode(0, head);
            ListNode p = h, q = head;
            while(q != null) {
                if(q.val == val) {
                    //删除一个元素
                    p.next = q.next;
                } else {
                    p = q;
                }
                q = q.next;
            }
            return h.next;
        }
    }
}


