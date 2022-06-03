import java.util.LinkedList;
import java.util.List;

/**
 * 93.复原IP地址
 * https://leetcode.cn/problems/restore-ip-addresses/
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
// id必须为4段，所以切3刀，每段0~255，不能有前导0
public class Q7_p93 {
    public static void main(String[] args) {
        String s =  "25525511135";
        Solution solution = new Solution();
        List<String> ans = solution.restoreIpAddresses(s);
        System.out.println(ans);
    }
    static class Solution {
        List<String> ans = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        public List<String> restoreIpAddresses(String s) {
            backtracking(s, 0, 0);
            return ans;
        }

        void backtracking(String s, int start, int num) {
            if (start>=s.length() && num == 4) {
                ans.add(sb.substring(0, sb.length()-1));
                return;
            }
            int temp=0;
            for (int i = start; i < s.length(); i++) {
                temp = temp * 10 + s.charAt(i)-'0';
                if (temp <=255) {
                    //添加字串
                    sb = sb.append(s.substring(start, i+1)+".");
                    backtracking(s, i+1, num+1);
                    //吐出，注意吐出的是字符串和最后的点
                    sb.delete(sb.length()-(i-start+1)-1, sb.length());
                    //先导零单独处理
                    if (s.charAt(start) == '0') break;
                } else break;
            }
        }
    }
}
