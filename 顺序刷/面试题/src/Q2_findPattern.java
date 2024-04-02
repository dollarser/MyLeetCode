import java.util.*;

public class Q2_findPattern {
    public static void main(String[] args) {
        String str1="baeebaendeabee";
        String pattern="aeeb";
        int ans = findPatt(str1, pattern);
        System.out.println(ans);
    }

    static int findPatt(String str, String pattern) {
        int ans=0;
        TreeMap<Character, Integer> map1 = new TreeMap<>();
        TreeMap<Character, Integer> map2 = new TreeMap<>();
        for(int i=0; i<pattern.length(); i++) {
            if (map1.containsKey(pattern.charAt(i))) {
                map1.put(pattern.charAt(i), map1.get(pattern.charAt(i))+1);
            }else {
                map1.put(pattern.charAt(i), 1);
            }
        }
        int len = 0;
        for(int i=0; i<str.length(); i++) {
            if (map2.containsKey(str.charAt(i))) {
                map2.put(str.charAt(i), map2.get(str.charAt(i))+1);
            } else {
                map2.put(str.charAt(i), 1);
            }
            len++;
            if (len>pattern.length()) {
                char key = str.charAt(i - pattern.length());
                int val = map2.get(key)-1;
                if (val <=0 ) map2.remove(key);
                else map2.put(key, val);
                len--;
            }
            // 判断是否相同
            System.out.println(map2);
            if (map1.equals(map2)){
                ans++;
            }
        }
        return ans;
    }
}

