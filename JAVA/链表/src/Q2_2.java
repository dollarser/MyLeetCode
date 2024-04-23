public class Q2_2 {
    public static void main(String[] args) {
        Q2_1.MyLinkedList linkedList = new Q2_1().new MyLinkedList();

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
    static void linkPrint(Q2_1.MyLinkedList linkedList) {
        Q2_1.MyLinkedNode p = linkedList.head;
        while(p != null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println("len: "+linkedList.len);
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {
            this.val=val;
        }
    }
    class MyLinkedList {
        //size存储链表元素的个数
        int size;
        //虚拟头结点
        ListNode head;

        //初始化链表
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        //获取第index个节点的数值
        public int get(int index) {
            //如果index非法，返回-1
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode currentNode = head;
            //包含一个虚拟头节点，所以查找第 index+1 个节点
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.val;
        }

        //在链表最前面插入一个节点
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        //在链表的最后插入一个节点
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
        // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
        // 如果 index 大于链表的长度，则返回空
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            size++;
            //找到要插入节点的前驱
            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            ListNode toAdd = new ListNode(val);
            toAdd.next = pred.next;
            pred.next = toAdd;
        }

        //删除第index个节点
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;
        }
    }
}

