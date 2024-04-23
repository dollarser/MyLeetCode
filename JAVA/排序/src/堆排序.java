public class 堆排序 {
    public static void main(String[] args) {
        int[]  arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        heap_sort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }
    //从下到上调整大根堆，默认子树是大根堆
    static void max_heap(int arr[], int start, int end) {
        int dad = start;
        int son = dad * 2 + 1;
        while (son <= end) {
            //若子节点索引在范围内才做比较
            //先比较两个子节点大小，选择最大的
            if (son + 1 <= end && arr[son] < arr[son + 1])
                son++;

            //如果父节点大于子节点代表调整完毕，直接跳出函数
            if (arr[dad] > arr[son])
                return;
            else {
                //交换父子内容再继续子节点和孙节点比较
                int temp = arr[dad];
                arr[dad] = arr[son];
                arr[son] = temp;
                //结点下移
                dad = son;
                son = dad * 2 + 1;
            }
        }
    }
    static void heap_sort(int arr[], int len) {
        int i;
        //初始化，i从最后一个父节点开始调整
        for (i = len / 2 - 1; i >= 0; i--)
            max_heap(arr, i, len - 1);
        //先将第一个元素和已排好元素前一位做交换，再从新调整，直到排序完毕
        for (i = len - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            max_heap(arr, 0, i - 1);
        }
    }
}
