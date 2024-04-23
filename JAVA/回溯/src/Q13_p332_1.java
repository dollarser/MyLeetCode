import java.util.*;

public class Q13_p332_1 {
    public static void main(String[] args) {
        String[][] temp = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> tickets = new LinkedList<>();
        for (String[] strings : temp) {
            LinkedList<String> list = new LinkedList<>();
            for (String string : strings) {
                list.add(string);
            }
            tickets.add(new ArrayList<>(list));
        }

        Solution solution = new Solution();
        List<String> ans = solution.findItinerary(tickets);
        System.out.println(ans);
    }

    static class Solution {
        private Deque<String> res;
        private Map<String, Map<String, Integer>> map;

        public List<String> findItinerary(List<List<String>> tickets) {
            map = new HashMap<String, Map<String, Integer>>();
            res = new LinkedList<>();
            //预处理, 建图, 类似树的孩子表示法
            for(List<String> t : tickets){
                Map<String, Integer> temp;
                //如果起点已经插入map中
                if(map.containsKey(t.get(0))){
                    temp = map.get(t.get(0));
                    //把终点保存在起点对应的map中,并记录路径的个数
                    temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
                }else{
                    //如果起点没有插入到map中, 创建起点对应的TreeMap
                    temp = new TreeMap<>();//升序Map
                    temp.put(t.get(1), 1);
                }
                map.put(t.get(0), temp);
            }

            res.add("JFK");
            backTracking(tickets.size());
            return new ArrayList<>(res);
        }

        private boolean backTracking(int ticketNum){
            if(res.size() == ticketNum + 1){
                return true;
            }
            //获取当前车票起点
            String last = res.getLast();
            if(map.containsKey(last)){//防止出现null
                //遍历当前起点的所有车票
                for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                    //到重点的路径数大于0,即还没有用完
                    int count = target.getValue();
                    if(count > 0){
                        //入栈
                        res.add(target.getKey());
                        //路径数减1
                        target.setValue(count - 1);
                        //递归
                        if(backTracking(ticketNum)) return true;

                        res.removeLast();
                        target.setValue(count);
                    }
                }
            }
            return false;
        }
    }
}
