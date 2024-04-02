import java.util.HashSet;
import java.util.Set;

/**
 * 第202题. 快乐数
 * https://leetcode-cn.com/problems/happy-number/
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * 重点是：确定return false的条件
 * 一个n位数，每位数的平方和最多是两位数；n个两位数相加，假设n的位数为k，则n个两位数最多是 k+2位数。
 * 因此无论原始的n位数多大，每位的平方和都会快速降到3位数。ps：对于32位int不超过两次计算就会降到3位数
 *
 * 对于3位数，此时n=3, n的位数是1，即k=1，此时最多还是3位数，结果不会在高于3位数。
 * 再具体的分析，一个3位数每位的平方和必定 <=243(999的结果); 此时以及能够断定之后结果不会再超过243。但这里我们再次计算后必定<=163(199的结果)，再次计算必定 <= 162(99的结果);
 * 之后结果都 <=162，因此最多循环162次必定重复。可得结论任何一个上述数字，经过平方和的运算必定循环。而快乐数不过是循环数字为1而已。
 *
 * 有循环可以用set判断
 */
public class Q3 {
    public static void main(String[] args) {
        int n = 19;
        Solution solution = new Solution();
        boolean ans = solution.isHappy(n);
        System.out.println(ans);
    }

    static class Solution {
        public boolean isHappy(int n) {
            Set<Integer> record = new HashSet<>();

            while(n != 1 && !record.contains(n)) {
                record.add(n);
                n = getNextNumber(n);
            }

            return n == 1;
        }

        private int getNextNumber(int n) {
            int res = 0;
            while(n!=0) {
                int temp = n%10;
                res += temp*temp;
                n = n/10;
            }
            return res;
        }
    }
}
