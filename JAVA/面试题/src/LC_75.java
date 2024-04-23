/**
 * 75. 颜色分类
 * https://leetcode.cn/problems/sort-colors/
 *
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
public class LC_75 {
    public static void main(String[] args) {
        Solution s = new LC_75().new Solution();
        int[] nums = {2,0,2,1,1,0};


    }

    class Solution {
        public void sortColors(int[] nums) {
            //计数法或者叫hash,统计一共有几个0，1，2
            //双指针
            int p0=0, p1=0;

            for (int i = 0; i < nums.length; i++) {

            }
        }
    }
}


