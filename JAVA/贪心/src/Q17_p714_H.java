import java.util.Arrays;
import java.util.LinkedList;

/**
 * 714. 买卖股票的最佳时机含手续费
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1: 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2 输出: 8
 *
 * 解释: 能够达到的最大利润: 在此处买入 prices[0] = 1 在此处卖出 prices[3] = 8 在此处买入 prices[4] = 4 在此处卖出 prices[5] = 9 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 */
public class Q17_p714_H {
    static class Solution {
        // 贪心思路

        /**
         * 每次买都需要加手续费，每次找一个极小值购买
         * 一旦
         */
        public int maxProfit(int[] prices, int fee) {
            int buy = prices[0] + fee;
            int sum = 0;
            for (int p : prices) {
                if (p + fee < buy) {
                    buy = p + fee;
                } else if (p > buy){
                    sum += p - buy;
                    buy = p;
                }
            }
            return sum;
        }

        //dp思路
        public int dpMaxProfit(int[] prices, int fee) {
            if (prices == null || prices.length < 2) {
                return 0;
            }

            int[][] dp = new int[prices.length][2];

            // bad case
            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }

            return dp[prices.length - 1][0];
        }

        public int myMaxProfit(int[] prices, int fee) {
            LinkedList<Integer> list = new LinkedList<>();
            int min = prices[0];
            int max = prices[0];
            int i = 1;
            /**
             * 这一段可以跳过，因此买股票是从最小值开始买，如果第一个值是最大值，则跳过
            //寻找第一个极大值
            while(i < prices.length && prices[i]>=max) {
                max = prices[i];
                i++;
            }
            //如果第一个值是极小值，则将第一个值添加，并添加极大值
            //否则不添加
            if(prices[i-1] != prices[0]) {
                list.add(prices[0]);
                list.add(prices[i-1]);
                min = prices[i-1];
            }
             */

            while (i < prices.length) {
                //先找极小值
                while(i < prices.length && prices[i]<=min) {
                    min = prices[i];
                    i++;
                }
                list.add(min);
                max = min;
                //如果i==prices.length,说明遍历结束且最后一个为极小值，应当跳过后续遍历
                if(i == prices.length) {
                    break;
                }
                //再找极大值
                while(i < prices.length && prices[i]>=max) {
                    max = prices[i];
                    i++;
                }
                list.add(max);
                min = max;
            }
            //此时list保存的即为波动数组

            return 0;
        }
    }
}
