public class Q2_1 {

    public static void main(String[] args) {
        MyLinkedList linkedList = new Q2_1().new MyLinkedList();

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
        MyLinkedNode p = linkedList.head;
        while(p != null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println("len: "+linkedList.len);
    }

    class MyLinkedNode {
        int val;
        MyLinkedNode next;
        public MyLinkedNode() {}
        public MyLinkedNode(int val) {
            this.val = val;
        }
    }
    class MyLinkedList {
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
