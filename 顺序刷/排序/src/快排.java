public class 快排 {
    public static void main(String[] args) {
        int[] nums = {5,2,7,3,13,4,12,0};
        quickSort(nums, 0, nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    //非递归写法，使用层次遍历保存每次枢轴的端点
    static void quickSort(int[] nums, int start, int end) {
        int temp = nums[start];
        int i=start, j=end;
        while(i < j) {
            while(i<j && nums[j]>temp) j--;
            nums[i] = nums[j];
            while(i<j && nums[i] <= temp) i++;
            nums[j] = nums[i];
        }
        nums[i] = temp;
        if (start < i-1) quickSort(nums, start, i-1);
        if (j+1 < end) quickSort(nums, j+1, end);
    }
}
