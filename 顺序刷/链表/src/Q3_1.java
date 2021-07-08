/**错误方法，引用传递，不能更改形参的值
 */
public class Q3_1 {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        ListNode head = new Q3_1().new ListNode(arr);
        linkPrint(head);
        Solution solution = new Q3_1().new Solution();
        solution.reverseList(head);
        linkPrint(head);
    }

    static void linkPrint(ListNode listNode) {
        ListNode p = listNode;
        while(p != null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println("");
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int[] arr) {
            val = arr[0];
            next = null;
            ListNode p = this;
            for (int i = 1; i < arr.length; i++) {
                ListNode n = new ListNode(arr[i]);
                p.next = n;
                p = n;
            }
        }
    }

    class Solution {
        /**引用传递，不能更改形参的值
         */
        public ListNode reverseList(ListNode head) {
            ListNode p = head.next, q = null;
            head.next = null;
            while(p != null) {
                q = p.next;
                p.next = head;
                head = p;
                p = q;
            }
            head = null;
            return head;
        }
    }
}

