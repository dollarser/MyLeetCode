class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    ListNode(int[] list) {
        ListNode p = null, q;
        int i = 0;
        for (int a : list) {
            if(i == 0) {
                this.val = a;
                this.next = null;
                p = this;
            }else{
                q = new ListNode(a);
                p.next = q;
                p = q;
            }
            ++i;
        }
    }

    static void linkPrint(ListNode p) {
        int len = 0;
        while(p != null){
            System.out.print(p.val+" ");
            p = p.next;
            len++;
        }
        System.out.println("");
        System.out.println("len: "+len);
    }

    //相交链表
    static void twoListNode(ListNode listA, ListNode listB, int skipA, int skipB) {
        for (int i = 0; i < skipA; i++) {
            listA = listA.next;
        }
        for (int i = 0; i < skipB-1; i++) {
            if(listB != null) {
                listB = listB.next;
            }
        }
        listB.next = listA;
    }
}
