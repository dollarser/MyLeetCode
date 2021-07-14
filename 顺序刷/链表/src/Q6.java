/**
 * 面试题 02.07. 链表相交
 * 题目链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 *
 * 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
 *
 * 示例 1：
 *
 * 输入：listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
 *
 * 输出：Reference of the node with value = 8
 *
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 */
public class Q6 {
    public static void main(String[] args) {
        int intersectVal = 8;
        int[] listA_ = {4,1,8,4,5};
        int[] listB_ = {5,0,1,8,4,5};
        int skipA = 2, skipB = 3;

        ListNode listA = new ListNode(listA_);
        ListNode listB = new ListNode(listB_);
        ListNode.linkPrint(listA);
        ListNode.linkPrint(listB);
        ListNode.twoListNode(listA, listB, skipA, skipB);
        ListNode.linkPrint(listA);
        ListNode.linkPrint(listB);

        Solution solution = new Solution();
        ListNode head = solution.getIntersectionNode(listA, listB);
        ListNode.linkPrint(head);
    }
    static public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA =0, lenB = 0, temp;
            ListNode pA = headA, pB = headB;
            while(pA != null) {
                lenA++;
                pA = pA.next;
            }
            while(pB != null) {
                lenB++;
                pB = pB.next;
            }
            if(lenB > lenA) {
                pA = headA;
                headA = headB;
                headB = pA;

                temp = lenA;
                lenA = lenB;
                lenB = temp;
            }
            pA = headA;
            pB = headB;
            for (int i = 0; i < lenA - lenB ; i++) {
                pA = pA.next;
            }
            while (pA != null) {
                if(pA == pB) {
                    return pA;
                }
                pA = pA.next;
                pB = pB.next;
            }
            return null;
        }
    }
}
