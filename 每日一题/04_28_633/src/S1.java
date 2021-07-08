public class S1 {
    public static void main(String[] args) {
        int c=190789776;

        S1 solution = new S1();
        boolean ans = solution.judgeSquareSum(c);
        System.out.println(ans);


    }
    public boolean judgeSquareSum(int c) {
        int mid = (int)Math.ceil(Math.sqrt(c/2));
        double a;
        for (int i = 0; i <= mid; i++) {
            a = Math.sqrt(c-i*i);
            if(Math.ceil(a)-a == 0) {
                return true;
            }
        }
        return false;
    }
}
