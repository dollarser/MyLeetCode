public class Q1_1 {
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
            while(p.next != null) {
                if(p.next.val == val) {
                    //删除一个元素
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return h.next;
        }
    }
}