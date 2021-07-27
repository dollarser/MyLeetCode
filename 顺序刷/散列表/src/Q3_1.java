/**
 * 这题可以用快慢指针的思想去做，有点类似于检测是否为环形链表那道题
 * 如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是
 * 两者一定会相等。如果没有循环重复，那么最后快慢指针也会相等，且都等于1。
 */
public class Q3_1 {
    public static void main(String[] args) {
        int n = 19;
        Solution solution = new Solution();
        boolean ans = solution.isHappy(n);
        System.out.println(ans);
    }

    static class Solution {
        public boolean isHappy(int n) {
            int fast=n;
            int slow=n;
            do{
                slow=squareSum(slow);
                fast=squareSum(fast);
                fast=squareSum(fast);
            }while(slow!=fast);
            if(fast==1)
                return true;
            else return false;
        }

        private int squareSum(int m){
            int squaresum=0;
            while(m!=0){
                squaresum+=(m%10)*(m%10);
                m/=10;
            }
            return squaresum;
        }
    }
}
