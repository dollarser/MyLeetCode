import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author sunlingzhang
 * @date 2023/10/25 12:56
 * 739. 每日温度
 * https://leetcode.cn/problems/daily-temperatures/
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class Q1_p739 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] temperatures = {89,62,70,58,47,47,46,76,100,70};
        int[] ans = s.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        /**
         * 通过简单逆序遍历，在遍历过程中，记录每个数字出现的最后顺序（下标）
         * 同时找出在此数据之前出现的比当前数字大的最小的下表
         * @param temperatures
         * @return
         */
        public int[] dailyTemperatures(int[] temperatures) {
            int[] ans = new int[temperatures.length];
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = temperatures.length-1; i >= 0; i--) {
                int min = Integer.MAX_VALUE;
                for (int j = temperatures[i]+1; j < next.length; j++) {
                    min = Math.min(min, next[j]);
                }
                if (min == Integer.MAX_VALUE) {
                    ans[i] = 0;
                } else {
                    ans[i] = min-i;
                }
                next[temperatures[i]] = i;
            }
            return ans;
        }

        /**
         * 标准解法，单调栈
         * @param temperatures
         * @return
         */
        public int[] dailyTemperatures2(int[] temperatures) {
            int[] ans = new int[temperatures.length];
            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < temperatures.length; i++) {
                int temperature = temperatures[i];
                // 单调递减栈，如果栈中元素比当前值小就出栈，保证栈中元素永远是递减的，但是栈中不需要包含全部元素
                while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                    int prevIndex = stack.pop();
                    ans[prevIndex] = i - prevIndex;
                }
                stack.push(i);
            }
            return ans;
        }
    }
}
