/**
 * 134. 加油站
 * https://leetcode.cn/problems/gas-station/
 *
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1: 输入:
 *
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3 解释:
 *
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2: 输入:
 *
 * gas = [2,3,4]
 *
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释: 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油。开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油。
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油。你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class Q8_p134 {
    public static void main(String[] args) {
        int[] gas={2,3,4};
        int[] cost={3,4,3};
        Solution solution = new Solution();
        int ans = solution.canCompleteCircuit(gas, cost);
        System.out.println(ans);
    }
    //只要油的总数大于路程总数一定能跑完全程
    //只要跑完全程结尾的一站一定是代价与油量差值最大的站
    static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int sum = 0;
            int minSum = Integer.MAX_VALUE;
            int minIndex = 0;
            int ans = -1;

            for (int i = 0; i < gas.length; i++) {
                sum += gas[i] - cost[i];
                if (sum < minSum) {
                    minSum = sum;
                    minIndex = i;
                }
            }
            if(sum>=0) ans = (minIndex + 1) % gas.length;
            return ans;
        }
    }

    /**
     * 如果x到不了y+1（但能到y），那么从x到y的任一点出发都不可能到达y+1。
     * 因为从其中任一点x'出发的时，油箱是从0开始加油，而从x出发到达x'时油箱中的油不会只会多余等于0。
     * 既然从油量大于等于0开始都到不了y+1，那么从0开始就更不可能到达y+1了...
     */
    class Solution1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int i = 0;
            while (i < n) {
                int restSum=0;
                int cnt = 0;
                while (cnt < n) {
                    int j = (i + cnt) % n;
                    restSum += gas[j]-cost[j];
                    if (restSum<0) {
                        break;
                    }
                    cnt++;
                }
                if (cnt == n) {
                    return i;
                } else {
                    i = i + cnt + 1;
                }
            }
            return -1;
        }
    }

    //超时
    class Solution2 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int ans=-1;
            for (int i = 0; i < gas.length; i++) {
                int rest = gas[i]-cost[i];
                int index = (i+1)%gas.length;
                while(rest>=0 && index!=i) {
                    rest += gas[index]-cost[index];
                    index = (index+1)%gas.length;
                }
                if(rest>=0 && index==i) {
                    ans=i;
                    break;
                }
            }
            return ans;
        }
    }
}
