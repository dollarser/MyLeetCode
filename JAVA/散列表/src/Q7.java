/**
 * 383. 赎金信
 * https://leetcode-cn.com/problems/ransom-note/
 *
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public class Q7 {
    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";

        Solution solution = new Solution();
        boolean ans = solution.canConstruct(ransomNote, magazine);
        System.out.println(ans);
    }
    static class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] arr = new int[26];
            char[] ransomArr = ransomNote.toCharArray();
            char[] magazineArr = magazine.toCharArray();
            for (int i = 0; i < magazine.length(); i++) {
                arr[magazineArr[i]-'a'] += 1;
            }
            for (int i = 0; i < ransomNote.length(); i++) {
                arr[ ransomArr[i]-'a']--;
                if(arr[ ransomArr[i]-'a'] < 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
