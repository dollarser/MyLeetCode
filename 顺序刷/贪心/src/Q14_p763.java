import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 763.划分字母区间
 * https://leetcode.cn/problems/partition-labels/
 *
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8] 解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。 每个字母最多出现在一个片段中。 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class Q14_p763 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ababcbacadefegdehijhklij";
        solution.partitionLabels(s);
    }
    
    
    static class Solution {
        public List<Integer> partitionLabels(String s) {
            LinkedList<Integer> ans = new LinkedList<>();
            int[] arr = new int[26];
            // 保存每个字符最后出现的位置
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                arr[c] = i;
            }
            /**
             * 遍历字符串计算当前字符串中的字符最后出现的位置
             * 如果所有字符最后出现的位置等于当前位置，那么该字符之后即是分割位
             */
            int end = arr[s.charAt(0)-'a'];
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                end = Math.max(end, arr[c]);
                if(i == end) {
                    ans.add(end-start+1);
                    start = i+1;
                }
            }
            return ans;
        }
        public List<Integer> myPartitionLabels(String s) {
            LinkedList<Integer> ans = new LinkedList<>();
            int[][] arr = new int[26][2];
            // 构建区间数组，1-base
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                arr[c][1] = i+1;
                if (arr[c][0] == 0) arr[c][0] = i+1;
            }
            Arrays.sort(arr, (a, b)->{
                return a[0]-b[0];
            });
            int index = 0;
            while(arr[index][0]==0) {
                index++;
                continue;
            }
            int end = arr[index][1];
            int start = arr[index][0];
            for (int i = index+1; i < arr.length; i++) {
                //不重合
                if(end<arr[i][0]) {
                    ans.add(end-start+1);
                    end = arr[i][1];
                    start = arr[i][0];
                } else {
                    end = Math.max(end, arr[i][1]);
                }
            }
            //循环结束添加
            ans.add(end-start+1);
            return ans;
        }
    }
}
