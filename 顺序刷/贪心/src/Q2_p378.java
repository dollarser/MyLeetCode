import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 376. 摆动序列
 * https://leetcode.cn/problems/wiggle-subsequence/
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例 1:
 *
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 *
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 *
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 *
 * 如图，找极大技校值，端点肯定会被包含
 *    /\/\  /\
 *  \/    \/  \/\___/
 */
public class Q2_p378 {
    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};

        Solution solution = new Solution();
        int ans = solution.wiggleMaxLength(nums);
        System.out.println(ans);
    }

    //严格正负交替
    static class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) return nums.length;
            //当前差值
            int curDiff = 0;
            //上一个差值
            int preDiff = 0;
            int len = 1; //数组端点
            int i = 1;
            while (i < nums.length) {
                //得到当前差值
                curDiff = nums[i] - nums[i - 1];
                //特殊情况，如果前一个差等于0，则向前寻找第一个差不等于0的值
                if (preDiff == 0) {
                    //记录等于0的点 nums[i-1]
                    int left = nums[i-1];
                    //记录左值
                    for (int j = i-1; j >=0; j--) {
                        if (nums[j] != left) {
                            preDiff  = left-nums[j];
                            break;
                        }
                    }
                }
                if ( (curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                    len++;
                    preDiff = curDiff;
                }
                i++;
            }
            return len;
        }
    }

    class Solution1 {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }
            //当前差值
            int curDiff = 0;
            //上一个差值
            int preDiff = 0;
            //默认最右边有关峰值
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                //得到当前差值
                curDiff = nums[i] - nums[i - 1];
                //如果当前差值和上一个差值为一正一负
                //等于0的情况表示初始时的preDiff
                if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                    count++;
                    preDiff = curDiff;
                }
            }
            return count;
        }
    }
}
