public class Q4_1 {
    // 递归版本
    class Solution {
        public ListNode swapPairs(ListNode head) {
            // base case 退出提交
            if(head == null || head.next == null) return head;
            // 获取当前节点的下一个节点
            ListNode next = head.next;
            // 进行递归
            ListNode newNode = swapPairs(next.next);
            // 这里进行交换
            next.next = head;
            head.next = newNode;

            return next;
        }
    }
}
