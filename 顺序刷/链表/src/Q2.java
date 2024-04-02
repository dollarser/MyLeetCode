/**
 * 707.设计链表
 * https://leetcode-cn.com/problems/design-linked-list/
 *
 * 题意：
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 */
public class Q2 {
    public static void main(String[] args) {
        int ans = 0;
        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//        //打印
//        linkPrint(linkedList);
//        linkedList.get(1);            //返回2
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        //打印
//        linkPrint(linkedList);
//        linkedList.get(1);            //返回3

        // ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
        // [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        linkPrint(linkedList); //3
//
//        linkedList.addAtIndex(3,0);
//        System.out.println(linkedList.tail.val);
//        linkPrint(linkedList); //4
//        linkedList.deleteAtIndex(2);
//        linkPrint(linkedList); //3
//        linkedList.addAtHead(6);
//        linkPrint(linkedList); //4
//        System.out.println(linkedList.tail.val);
//        linkedList.addAtTail(4);
//        linkPrint(linkedList); //5
//        linkedList.get(4);
//        linkedList.addAtHead(4);
//        linkedList.addAtIndex(5,0);
//        linkedList.addAtHead(6);
//        linkPrint(linkedList);


    // ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
    // [[],[1],[3],[1,2],[1],[0],[0]]
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);
        System.out.println(linkedList.head.val);
        System.out.println(linkedList.tail.val);
        linkPrint(linkedList);
        linkedList.get(1);
        linkedList.deleteAtIndex(0);
        linkedList.get(0);
        linkPrint(linkedList); //3

    }
    static void linkPrint(MyLinkedList linkedList) {
        MyLinkedList.MyLinkedNode p = linkedList.head;
        while(p != null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println("len: "+linkedList.len);
    }

    /**
     * 答案
     */
    static class MyLinkedList {
        class MyLinkedNode {
            int val;
            MyLinkedNode next;
            MyLinkedNode prev;
            public MyLinkedNode() {}
            public MyLinkedNode(int val) {
                this.val = val;
            }
        }
        MyLinkedNode head;
        MyLinkedNode tail;
        int len;
        /** Initialize your data structure here. */
        public MyLinkedList() {
            head = null;
            tail = null;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            int ans = -1;
            if(index<len) {
                MyLinkedNode p = head;
                for(int i=0; i<index; ++i) {
                    p = p.next;
                }
                ans = p.val;
            }
            return ans;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            MyLinkedNode p = new MyLinkedNode(val);
            p.next = head;
            head = p;
            //插入链表的第一个元素
            if(tail == null) {
                tail = p;
            }
            ++len;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            ++len;
            MyLinkedNode p = new MyLinkedNode(val);
            //插入链表的第一个元素
            if(head == null) {
                head = p;
                tail = p;
                return;
            }
            tail.next = p;
            tail = p;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index > len) return;
            if(index <= 0) {
                addAtHead(val);
                ++len;
                return;
            }
            MyLinkedNode p = head;
            MyLinkedNode q = new MyLinkedNode(val);
            for (int i = 0; i < index-1; i++) {
                p = p.next;
            }
            q.next = p.next;
            p.next = q;
            //如果插入的尾
            if(q.next == null) {
                tail = q;
            }
            ++len;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index < 0 || index >= len) return;
            --len;
            MyLinkedNode q = null, p = head;
            //删的头
            if(index == 0) {
                head = p.next;
                p.next = null;
                //如果删的也是尾
                if(tail == p) {
                    tail = q;
                }
                return;
            }
            for (int i = 0; i < index; i++) {
                q = p;
                p = p.next;
            }
            q.next = p.next;
            p.next = null;
            //如果删的尾
            if(tail == p) {
                tail = q;
            }
            return;
        }
    }
}
