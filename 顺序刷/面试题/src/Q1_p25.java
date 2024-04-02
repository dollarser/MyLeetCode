/**
 * 25.K个一组翻转链表
 * https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */

public class Q1_p25 {
    public static void main(String[] args) {

    }
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            //计算链表长度
            int len = 0;
            ListNode temp = head;
            while(temp!=null) {
                temp=temp.next;
                len++;
            }
            //正式处理
            ListNode h = new ListNode();
            h.next = head;
            //初始头结点也是尾结点
            ListNode tail = h;

            ListNode p = head;
            ListNode q = null;

            int i = 0;
            while (p!=null) {
                if (i%k == 0 && len-i>=k) {
                    //处理头尾结点
                    tail.next = p; //将上一组的尾结点next指针指向当前结点
                    head = tail; //将上一组尾结点设为下一组的头结点
                    tail = p; //将当前结点设为新的尾结点
                    //调整为常规
                    q = p.next;
                    tail.next = null;
                    p = q;
                } else if (i%k == 0 && len-i<k) {
                    tail.next=p;
                    break;
                }
                else {
                    q = p.next;
                    p.next = head.next;
                    head.next = p;
                    p = q;
                }
                i++;
            }
            return h.next;
        }

        public ListNode reverse(ListNode head) {
            ListNode h = null;
            ListNode p = head;
            ListNode q = null;
            while (p!=null) {
                q = p.next;
                p.next = h;
                h = p;
                p = q;
            }
            return h;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
