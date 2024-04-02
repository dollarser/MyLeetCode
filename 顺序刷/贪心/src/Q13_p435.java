import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * https://leetcode.cn/problems/non-overlapping-intervals/
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意: 可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class Q13_p435 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        solution.eraseOverlapIntervals(intervals);

    }
    static class Solution {
        public int myEraseOverlapIntervals(int[][] intervals) {
            //从小到大排序
            Arrays.sort(intervals, (a,b)->{
                //等价于a[0]-b[0]
                return Integer.compare(a[0], b[0]);
            });
            int ans = 0;
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                //区间不重合（端点重合不算重合）,直接更新端点
                if(intervals[i][0]>=end) {
                    end = intervals[i][1];
                } else{
                    //区间重合，删除一个区间
                    ans++;
                    //选择区间右侧大的删除，更新新的区间右侧
                    end = Math.min(end, intervals[i][1]);
                }
            }
            return ans;
        }

        public int eraseOverlapIntervals(int[][] intervals) {
            int ans=0;
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0],o2[0]);
                }
            });
            int now = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int l = intervals[i][0];
                int h = intervals[i][1];
                //区间不重合
                if(now<=l) {
                    now = h;
                    continue;
                }
                //区间重合
                if (h<=now) {
                    now=h;
                }
                ans+=1;
            }
            return ans;
        }
    }
}
