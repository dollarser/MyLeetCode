import java.util.*;
/**
 * https://blog.csdn.net/qq_38649940/article/details/118557299
 * https://www.nowcoder.com/discuss/1035538
 * 主要参考：https://www.nowcoder.com/discuss/1037077
 * 0是路，1是墙，2是起点，3是终点
 */
public class 华为_士兵的任务2 {
    static class Node implements Comparable {
        int x, y, t;
        Node() {}
        Node(int a, int b, int c) {
            x = a;
            y = b;
            t = c;
        }
        @Override
        public int compareTo(Object o) {
            Node o_temp = (Node) o;
            return t - o_temp.t;
        }
    }
    ////自定义比较器，降序排列
    //static Comparator<Integer> cmp = new Comparator<Integer>() {
    //    public int compare(Integer e1, Integer e2) {
    //        return e2 - e1;
    //    }
    //};
    //Queue<Integer> q = new PriorityQueue<>(cmp);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [][] g = new int[n][m];
        int [][] vis = new int[n][m];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int ans = 0;
        //默认升序
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                g[i][j] = sc.nextInt();
                if (g[i][j] == 2) {
                    vis[i][j] = 1;
                    queue.add(new Node(i, j, 0));
                }
            }
        }
        while (!queue.isEmpty()) {
            Node cur = queue.peek();
            queue.poll();
            for (int i = 0; i < 4; ++i)
            {
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= n || ty >= n || vis[tx][ty]==1 || g[tx][ty] == 1)
                    continue;

                if (g[tx][ty] == 3) {
                    ans = cur.t + 1;
                    break;
                }
                vis[tx][ty] = 1;
                if (g[tx][ty] == 4) {
                    queue.add(new Node(tx, ty, cur.t + 3));
                }
                else if (g[tx][ty] == 6) {
                    queue.add(new Node(tx, ty, cur.t + 1));
                    for (int j = 0; j < 4; ++j) {
                        int xx = tx + dx[j];
                        int yy = ty + dy[j];
                        if (g[xx][yy] == 1)
                            g[xx][yy] = 0;
                    }
                }
                else {
                    queue.add(new Node(tx, ty, cur.t + 1));
                }
            }
            if (ans == 0) break;
        }
        System.out.println(ans);
    }
}
