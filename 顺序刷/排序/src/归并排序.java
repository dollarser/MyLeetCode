public class 归并排序 {
    public static void main(String[] args) {
        int[]  arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        int[] temp = new int[arr.length];
        merge_sort(arr, temp, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
    public static void merge_sort(int arr[], int temp[], int start, int end) {
        //递归结束
        if (start >= end)
            return;
        int mid = start+(end-start)/2;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;

        merge_sort(arr, temp, start1, end1);
        merge_sort(arr, temp, start2, end2);
        // 归并
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] < arr[start2]) {
                temp[k++] = arr[start1++];
            } else {
                temp[k++] = arr[start2++];
            }
        }
        //处理结尾
        while (start1 <= end1)
            temp[k++] = arr[start1++];
        while (start2 <= end2)
            temp[k++] = arr[start2++];
        //将结果复制到原始数组中
        for (k = start; k <= end; k++)
            arr[k] = temp[k];
    }

}
