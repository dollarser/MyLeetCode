import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class LC_22 {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> ans = s.generateParenthesis(3);
        for (String an : ans) {
            System.out.println(an);
        }
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        generateHelper(ans, sb, n, 0);
        return ans;
    }

    public void generateHelper(List<String> ans, StringBuilder sb, int n, int cnt ) {
        if(sb.length()>=2*n) {
            ans.add(sb.toString());
            return;
        }
        if(cnt < n) {
            sb.append("(");
            generateHelper(ans, sb, n, cnt+1);
            sb.delete(sb.length()-1, sb.length());
        }
        if(sb.length()-cnt < cnt) {
            sb.append(")");
            generateHelper(ans, sb, n, cnt);
            //为什么需要删除，因为递归返回需要回归到没有进行当前操作的状态
            sb.delete(sb.length()-1, sb.length());
        }
    }
}
