import java.util.Arrays;
import java.util.LinkedList;

/**
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 */
public class Q15_p56 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] ans = solution.merge(intervals);
        for (int[] interval : ans) {
            System.out.println(interval[0]+","+interval[1]);
        }
    }
    static class Solution {
        public int[][] merge(int[][] intervals) {
            //从小到大排序
            Arrays.sort(intervals, (a,b)->{
                return Integer.compare(a[0], b[0]);
            });
            LinkedList<int[]> list = new LinkedList<>();
            int end = intervals[0][1];
            int start = intervals[0][0];
            for (int i = 1; i < intervals.length; i++) {
                //如果区间不重合
                if(intervals[i][0] > end) {
                    list.add(new int[]{start, end});
                    start = intervals[i][0];
                    end = intervals[i][1];
                    continue;
                }
                //如果区间重合，更新右端点
                end = Math.max(end, intervals[i][1]);
            }
            list.add(new int[]{start, end});
            return list.toArray(new int[list.size()][]);
        }
    }
}
