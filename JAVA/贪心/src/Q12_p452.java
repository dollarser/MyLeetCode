import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。
 * 墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。
 * 你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
 * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 *
 * 示例 1：
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * 示例 2：
 *
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 示例 3：
 *
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 示例 4：
 *
 * 输入：points = [[1,2]]
 * 输出：1
 * 示例 5：
 *
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * 提示：
 *
 * 0 <= points.length <= 10^4
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 */
public class Q12_p452 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[][] points = {{1,2},{3,4},{5,6},{7,8}};
        int[][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
        //int[][] points = {{1,2},{1,4}, {2,3}};
        int ans = solution.findMinArrowShots(points);
        System.out.println(ans);
    }
    /**
     * 重合区间的问题，先重排序
     */
    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // 根据气球直径的开始坐标从小到大排序
            // 使用Integer内置比较方法，不会溢出
            Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
            //Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
            int count = 1;  // points 不为空至少需要一支箭
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] > points[i - 1][1]) {  // 气球i和气球i-1不挨着，注意这里不是>=
                    count++; // 需要一支箭
                } else {
                    // 气球i和气球i-1挨着,更新重叠气球最小右边界
                    points[i][1] = Math.min(points[i][1], points[i - 1][1]);
                }
            }
            return count;
        }

        public int myFindMinArrowShots(int[][] points) {
            int ans = 0;
            //先从小到大排序
            Arrays.sort(points, (a, b)->{
                //两个数相减可能越界，无需比较区间右端
                //if(a[0] == b[0]) return a[1]-b[1];
                return Integer.compare(a[0], b[0]);
            });
            int[] temp = points[0];
            ans++;
            for(int i=1; i<points.length; i++) {
                if(points[i][0]<=temp[1]) {
                    temp[0] = points[i][0];
                    temp[1] = Math.min(temp[1], points[i][1]);
                } else {
                    ans++;
                    temp = points[i];
                }
            }
            return ans;
        }
    }
}
