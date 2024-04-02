public class S2 {
    public static void main(String[] args) {
        //int weights[] = {1,2,3,4,5,6,7,8,9,10};
        int weights[] = {1,2,3,1,1};
        //int D = 5;
        int D = 4;
        S2 solution = new S2();
        int ans = solution.shipWithinDays(weights, D);
        System.out.println(ans);
    }
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = 0, ave = 0;
        for(int i=0; i< weights.length; ++i) {
            sum += weights[i];
            if(weights[i]>max) max = weights[i];
        }
        ave = sum/D;
        max = max>ave?max:ave;
        for(int i=max; i<sum; ++i) {
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
