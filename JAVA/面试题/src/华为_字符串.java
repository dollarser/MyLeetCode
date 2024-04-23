import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/discuss/1035538
 */
public class 华为_字符串 {
    static Boolean isalpha (char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        // please finish the function body here.
        // please define the JAVA output here. For example: System.out.println(s.nextInt());
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        String word;
        StringBuilder ans = new StringBuilder();
        int index = 0;
        // 输入的待匹配句子
        String str = sc.nextLine();
        //输入的匹配字符串列表, 输入ctrl+D作为EOF
        while (sc.hasNext()) {
            word = sc.next().toLowerCase();
            System.out.println(word);
            map.put(word, index);
            index += 1;
        }
        //处理输入
        LinkedList<String> list = new LinkedList<>();
        String[] str_list = str.split(" ");

        // 算法逻辑 双指针遍历数组
        int l=0, h=0;
        while (h < str.length()) {
            //符号直接添加
            while (l < str.length() && (str.charAt(l) == ' ' || str.charAt(l) == ',' || str.charAt(l) == '.')) {
                //比String str += str.charAt(l)节省内存开销
                ans.append(str.charAt(l));
                l++;
            }
            //引号内内容直接添加
            if (l < str.length() && str.charAt(l) == '"') {
                int i = str.indexOf('"', l+1);
                //ans.append(str.substring(l, i+1));
                ans.append(str, l, i+1);
                l = i+1;
            }
            // 字符串需要比较
            h = l;
            while (h < str.length() && isalpha(str.charAt(h))) {
                h++;
            }
            String temp = str.substring(l,h).toLowerCase();
            if (map.containsKey(temp)) {
                ans.append(map.get(temp)+"");
            } else {
                ans.append(temp);
            }
            l = h;
        }
        System.out.print(ans);
    }
}

