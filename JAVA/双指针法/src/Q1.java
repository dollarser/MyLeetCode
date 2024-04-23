/**
 * 27. 移除元素
 * 题目地址：https://leetcode-cn.com/problems/remove-element/
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Q1 {
    public static void main(String[] args) {
        int nums[] = {3,2,2,3}, val = 3;
        Solution solution = new Solution();
        int ans = solution.removeElement(nums, val);
        System.out.println(ans);
    }

    static class Solution {
        //双指针法
        public int removeElement(int[] nums, int val) {
            int j = 0;
            for(int i=0; i< nums.length; ++i) {
                if(nums[i] == val) {
                    continue;
                } else {
                    nums[j] = nums[i];
                    ++j;
                }
            }
            return j;
        }
        //计数法
        public int removeElement2(int[] nums, int val) {
            int skip = 0;
            for(int i=0; i< nums.length; ++i) {
                if(nums[i] == val) {
                    ++skip;
                } else {
                    nums[i - skip] = nums[i];
                }
            }
            return nums.length-skip;
        }
    }
}
