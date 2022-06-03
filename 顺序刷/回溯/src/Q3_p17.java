import java.util.LinkedList;
import java.util.List;

/**
 * 17.电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例: 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明：尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class Q3_p17 {
    public static void main(String[] args) {
        String digits = "23";
        Solution solution = new Solution();
        List<String> ans = solution.letterCombinations(digits);
        System.out.println(ans);
    }

    static class Solution {
        String[] nums = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new LinkedList<>();
        StringBuffer stringBuffer = new StringBuffer();
        public List<String> letterCombinations(String digits) {
            backtracking(digits, 0);
            return ans;
        }

        private void backtracking(String digits, int index) {
            //终止条件
            if (index >= digits.length()) {
                //如果输入的不是空串
                if (index > 0) {
                    ans.add(new String(stringBuffer));
                }
                return;
            }
            char c = digits.charAt(index);
            String s = nums[c-'0']; //获取数字对应的字符串
            for (int j = 0; j < s.length(); j++) {
                stringBuffer.append(s.charAt(j));
                backtracking(digits, index+1);
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
            }
        }
    }
}
