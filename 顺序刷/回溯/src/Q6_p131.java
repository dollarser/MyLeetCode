import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 131.分割回文串
 * https://leetcode.cn/problems/palindrome-partitioning/
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例: 输入: "aab" 输出: [ ["aa","b"], ["a","a","b"] ]
 *
 * 分割类似组合，就是从切1刀穷举到切n刀，或从切n刀穷举切1刀
 */
public class Q6_p131 {
    public static void main(String[] args) {
        String s = "aab";
        Solution solution = new Solution();
        List<List<String>> ans = solution.partition(s);
        System.out.println(ans);
    }
    static class Solution {
        List<List<String>> ans = new LinkedList<>();
        Deque<String> deque = new LinkedList<>();
        public List<List<String>> partition(String s) {
            backtracking(s, 0);
            return ans;
        }

        void backtracking(String s, int start) {
            if (start>=s.length()) {
                ans.add(new ArrayList<>(deque));
            }
            //按分割数递归，每次切一刀，直到切完n刀
            for (int i = start; i < s.length(); i++) {
                //如果是回文串，则添加
                if (isHuiwei(s, start,i)) {
                    deque.add(s.substring(start, i+1));
                    backtracking(s, i+1);
                    deque.removeLast();
                }
            }
        }

        Boolean isHuiwei(String s, int start, int end) {
            for (int i = start, j=end; i < j; i++, j--) {
                if(s.charAt(i) != s.charAt(j)) return false;
            }
            return true;
        }
    }
}
