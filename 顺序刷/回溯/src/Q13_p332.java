/**
 * 332.重新安排行程
 * https://leetcode.cn/problems/reconstruct-itinerary/
 *
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 * 提示：
 *
 * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 所有的机票必须都用一次 且 只能用一次。
 * 示例 1：
 *
 * 输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2：
 *
 * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * 题目总结: 整体是个图结构
 * 从结点JFK出发遍历所有路径
 * 规律起点和终点 边为奇数,过度结点边为偶数
 *
 * 思路: 遍历数组找JKF为起点的所有路径, 将路径排序依次将入栈
 * 接着递归找栈顶元素为起点的路径,入栈
 */
public class Q13_p332 {
    public static void main(String[] args) {
        String[][] temp = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> tickets = new LinkedList<>();
        for (String[] strings : temp) {
            LinkedList<String> list = new LinkedList<>();
            for (String string : strings) {
                list.add(string);
            }
            tickets.add(new ArrayList<>(list));
        }

        Solution solution = new Solution();
        List<String> ans = solution.findItinerary(tickets);
        System.out.println(ans);
    }
    static class Solution {
        List<String> ans = new LinkedList<>();
        LinkedList<String> path = new LinkedList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            path.add("JFK");
            int[] usedId = new int[tickets.size()];
            backtracking(tickets, "JFK");
            return ans;
        }

        void backtracking(List<List<String>> tickets, String start) {
            //结束条件
            if (ans.size()>0) return;
            if (path.size() == tickets.size() + 1) {
                ans = new LinkedList<>(path);
            }
            //有序set
            TreeSet<String> set =  new TreeSet<>();
            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).get(0).equals(start)) {
                    set.add(tickets.get(i).get(1));
                }
            }
            for (String s : set) {
                path.add(s);
                backtracking(tickets, s);
                path.removeLast();
            }
        }


    }
}
