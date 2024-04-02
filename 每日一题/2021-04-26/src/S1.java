public class S1 {
    public static void main(String[] args) {
        //int weights[] = {1,2,3,4,5,6,7,8,9,10};
        int weights[] = {1,2,3,1,1};
        //int D = 5;
        int D = 4;
        S1 solution = new S1();
        int ans = solution.shipWithinDays(weights, D);
        System.out.println(ans);
    }
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, ave = 0;
        for(int i=0; i< weights.length; ++i) {
            sum +=weights[i];
        }
        //ave表示D天运完，最低的运载量
        ave = sum/D;
        //System.out.println(ave);
        //System.out.println(weights.length);
        for(int i=ave; i<sum; ++i) {
            if(tool(weights, D, i)==true) {
                return i;
            }
        }
        return sum;
    }
    public boolean tool(int[] weights, int D, int ans) {
        int sum = 0, day=0;
        for(int i=0; i< weights.length; ++i) {
            if(weights[i]>ans) {
                return false;
            }
            sum += weights[i];
            if(sum>ans) {
                sum=weights[i];
                ++day;
                if(day>D) return false;
            }
        }
        //for循环处理完最后一个就结束，如果不够一船没有处理，超过一船也没有处理超过的部分，所以后面直接+1天
        ++day;
        if(day>D) return false;
        return true;
    }
}
