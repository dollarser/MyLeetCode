import java.util.Arrays;

/**假设当船的运载能力为 xx 时，我们可以在 DD 天内运送完所有包裹，那么只要运载能力大于 xx，
 * 我们同样可以在 DD 天内运送完所有包裹：我们只需要使用运载能力为 xx 时的运送方法即可。
 * 使用二分查找的方法
 * */
public class Solution {
    public static void main(String[] args) {
        //int weights[] = {1,2,3,4,5,6,7,8,9,10};
        int weights[] = {1,2,3,1,1};
        //int D = 5;
        int D = 4;
        S1 solution = new S1();
        int ans = solution.shipWithinDays(weights, D);
        System.out.println(ans);
    }
    public int shipWithinDays(int[] weights, int D) {
        // 确定二分查找左右边界
        int left = Arrays.stream(weights).max().getAsInt(), right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = (left + right) / 2;
            // need 为需要运送的天数
            // cur 为当前这一天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            if (need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
