/**
 * 双指针，如果和大于c则减小大数（为什么不减小 小数，因为小数是从小变大过来的，小数更小的情况已经全部考虑），
 * 如果和小于c则增大小数（为什么不增大 大数，因为大数是从大变小过来的，大数更大的情况已经全部考虑）
 */
class S2 {
    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}
