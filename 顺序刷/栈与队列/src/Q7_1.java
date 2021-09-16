import java.util.*;

/**
 * 通过TreeMap排序，时间复杂度O(nlogn)，空间复杂度O(n)
 */
public class Q7_1 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,2,3,3,4,5,5,5};
        int k = 3;
        Solution solution = new Solution();
        int ans[] = solution.topKFrequent(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            int ans[] = new int[k];
            /**
             * 借助Map排序，让复杂度为 nlogn，
             * 另外由于Java不同于python允许TreeMap键值重名，通过List保存值，模拟值相同
             */
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : nums) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i)+1);
                } else {
                    map.put(i, 1);
                }
            }
            Map<Integer, List<Integer> > map_ = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                List<Integer> temp;
                if (map_.containsKey(entry.getValue())) {
                    temp = map_.get(entry.getValue());
                } else {
                    temp = new LinkedList<>();
                }
                temp.add(entry.getKey());
                map_.put(entry.getValue(), temp);
            }
            /**
             * 输出排序的结果
             */
            int i = 0;
            int[] sorted = new int[nums.length];
            for (Map.Entry<Integer, List<Integer> > entry : map_.entrySet()) {
                for (int j: entry.getValue() ) {
                    sorted[i] = j;
                    ++i;
                }
            }
            for (int j = i-k; j < i; j++) {
                ans[j- (i-k)] = sorted[j];
            }
            return ans;
        }
    }
}