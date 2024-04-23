import java.util.LinkedList;

/**
 * 738.单调递增的数字
 * https://leetcode.cn/problems/monotone-increasing-digits/
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class Q16_p738_H {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int n = 332;
        int n = 0;
        int ans = solution.monotoneIncreasingDigits(n);
        System.out.println(ans);
    }
    static class Solution {
        public int myMonotoneIncreasingDigits(int n) {
            //各位数单独处理，特别是当n=0时
            if (n<10) return n;

            LinkedList<Integer> list = new LinkedList<>();
            LinkedList<Integer> ans = new LinkedList<>();
            while (n > 0) {
                list.add(0, n % 10);
                n = n / 10;
            }

            Integer[] arr = list.toArray(new Integer[list.size()]);
            int flag = 0;
            for (int i = 1; i < arr.length; i++) {
                //如果前一个缩小了1，后面全9
                if (flag == 1) {
                    ans.add(9);
                    continue;
                }
                if (arr[i] >= arr[i-1]) {
                    ans.add(arr[i - 1]);
                } else {
                    //如果前一个数字与之前的相等，则减一的操作向后传递
                    //相邻数字相等的特殊情况
                    while(i>=2 && arr[i-1]==arr[i-2]) {
                        i--;
                        //之前添加的移除
                        ans.removeLast();
                    }
                    //因为arr[i-1]>arr[i],所以arr[i-1]>0,即arr[i-1]>=1
                    ans.add(arr[i - 1] - 1);
                    flag = 1;
                }
            }
            //处理最后一个
            if (flag == 1) {
                ans.add(9);
            }else{
                ans.add(arr[arr.length-1]);
            }

            int sum = 0;
            for (int i = 0; i < ans.size(); i++) {
                sum = sum * 10 + ans.get(i);
            }
            return sum;
        }
        //重点是寻找第一个需要减1的数字
        //之所以从后往前遍历，是因为当相邻数字相等时后一个数字修改会影响前一个数字
        public int monotoneIncreasingDigits(int n) {
            //整数转字符串
            String s = String.valueOf(n);
            //字符串转字符数组
            char[] chars = s.toCharArray();
            //即默认不需要修改
            int start = s.length();
            //从后往前遍历，一旦前一个大于后一个就修改前一个数字，一直向前修改下去
            for (int i = s.length() - 1; i > 0; i--) {
                //如果前一个大于当前，则减1，以当前为起点之后都修改为9
                if (chars[i-1] > chars[i]) {
                    chars[i-1]--;
                    start = i;
                }
            }
            //将最后一次修改之后的数字全部改为9
            for (int i = start; i < s.length(); i++) {
                chars[i] = '9';
            }
            //字符数组转字符串，转整数
            return Integer.parseInt(String.valueOf(chars));
        }
    }
}