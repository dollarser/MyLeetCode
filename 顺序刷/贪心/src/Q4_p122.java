/**
 * 122.买卖股票的最佳时机II
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4。随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * 低买高卖，每次选局部最小值
 *    /\/\  __/\
 *  \/    \/    \/\___/
 */
public class Q4_p122 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        Solution solution = new Solution();
        int ans = solution.maxProfit(prices);
        System.out.println(ans);
    }
    //极值买卖，类比摆动数列
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) return 0;
            int cash; //卖
            int hold = -1; // 买
            int sum = 0;
            //遍历
            int pre = 0;
            for (int i = 1; i < prices.length; i++) {
                int cur = prices[i]-prices[i-1];
                if (pre==0) {
                    //买、卖、或不动
                    for (int j = i-1; j-1 >=0 ; j--) {
                        pre = prices[j] - prices[j-1];
                        if (pre != 0) break;
                    }
                    //如果pre还是0说明是第一个结点，此时如果股票增则购买，如果减则不购买
                }
                //pre如果是0说明是第一个结点，此时如果股票增则购买
                if (cur>0 && pre<=0) {
                    //买
                    hold = prices[i-1];
                }
                if (cur<0 && pre>0) {
                    //卖
                    cash = prices[i-1];
                    sum += cash-hold;
                    hold = -1; //做标志位，如果为0说明卖了
                }
                pre = cur;
            }
            //处理最后的结点
            if (hold != -1 ) {
                sum += prices[prices.length-1] - hold;
            }
            return sum;
        }

        //多次买卖
        public int maxProfit_2(int[] prices) {
            if (prices.length <= 1) return 0;
            //遍历
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i]>prices[i-1]) {
                    sum += prices[i]-prices[i-1];
                }
            }
            return sum;
        }
    }
}
