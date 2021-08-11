public class Q7 {
    public static void main(String[] args) {
        String s = "abab";
        Solution solution = new Solution();
        boolean ans = solution.repeatedSubstringPattern(s);
        System.out.println(ans);
    }
    static class Solution {
        public boolean repeatedSubstringPattern(String s) {
            int[] next = new int[s.length()];
            int i = 1, j = 0;
            next[i] = -1;
            //标准前缀
            while( i < s.length()-1) {
                if (s.charAt(i) == s.charAt(j)) {
                    next[i] = j;
                    ++i;
                    ++j;
                } else if( j == 0 &&  s.charAt(i) != s.charAt(j)) {
                    next[i] = j;
                    ++i;
                } else {
                    j = next[j];
                }
            }
            int k = s.length() - 1;
            System.out.println(next[s.length()-1]);
            if(s.length() % (s.length() - next[s.length()-1] - 1) == 0) {
                return true;
            }
            return false;
        }
    }
}
