public class Q6_1 {
    public static void main(String[] args) {


    }
    static int solution(String[] args) {
        String haystack = "mississippi", needle = "issip";
        int[] next = getNext(needle);

        //使用前缀表
        int j = -1;
        int i = 0;
        while (i < haystack.length()) {
            if (j==-1 || haystack.charAt(i) == needle.charAt(j)) {
                if (j==needle.length()-1) return i-j;
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        return -1;
    }
    //右移一位版前缀表,next[0] = -1表示前缀表没有匹配
    static private int[] getNext(String s) {
        int[] next = new int[s.length()];
        int j = -1;
        int i = 0;
        //用while更合适
        while(i<s.length()) {
            //如果字符相同
            if (j==-1 || s.charAt(i) == s.charAt(j)) {
                //右移一位版，先加后赋值
                ++j;
                ++i;
                next[i] = j;
            }
            //如果字符不相同
            else {
                //右移一位版，直接用当前下标的next数组
                //这里是什么，next数组使用时就是什么
                j = next[j];
            }
        }
        return next;
    }
}
