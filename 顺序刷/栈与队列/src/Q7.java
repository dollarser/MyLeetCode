import java.util.*;

/**
 * 本题收获，使用优先队列表示大顶堆或小顶堆
 * 因为堆是使用数组表示完全二叉树，优先队列也是数组形式，本质上相同
 * 思想：先计数，根据计数使用小顶堆排序，因为小顶堆每次将最小的选出，
 * 踢出堆，最终堆中留下的就是最大的k个数
 */
public class Q7 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        Solution solution = new Solution();
        int ans[] = solution.topKFrequent(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            int[] result = new int[k];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            // 根据map的value值正序排，相当于一个小顶堆
            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
            for (Map.Entry<Integer, Integer> entry : entries) {
                queue.offer(entry);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            for (int i = k - 1; i >= 0; i--) {
                result[i] = queue.poll().getKey();
            }
            return result;
        }
    }
}