# 2510Check if There is a Path With Equal Number of 0's And 1's
<p>You are given a <strong>0-indexed</strong> <code>m x n</code> <strong>binary</strong> matrix <code>grid</code>. You can move from a cell <code>(row, col)</code> to any of the cells <code>(row + 1, col)</code> or <code>(row, col + 1)</code>.</p>

<p>Return <code>true</code><em> if there is a path from </em><code>(0, 0)</code><em> to </em><code>(m - 1, n - 1)</code><em> that visits an <strong>equal</strong> number of </em><code>0</code><em>&#39;s and </em><code>1</code><em>&#39;s</em>. Otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/12/20/yetgriddrawio-4.png" />
<pre>
<strong>Input:</strong> grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The path colored in blue in the above diagram is a valid path because we have 3 cells with a value of 1 and 3 with a value of 0. Since there is a valid path, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/12/20/yetgrid2drawio-1.png" style="width: 151px; height: 151px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[0,0,1],[1,0,0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no path in this grid with an equal number of 0&#39;s and 1&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>
































# 解题:
# 1.更快的O(nm)的算法
首先注意到所有合法路径的长度均为 $n+m-1$，那么需要恰好经过 $(n+m-1)/2$ 个 $1$。接下来利用以下引理：

**引理.** 将所有合法路径中经过 $1$ 的最小(最大)次数记为 $mi$ ($ma$) 
，则对于所有 $mi$ ~ $ma$ 之间的整数 $t$，均存在经过恰好 $t$ 个 $1$ 的合法路径。

**证明.** 以下方法可以把一条合法路径连续地变成另一条，并保证所有中间状态仍然是合法路径。先找到起始和目标路径的第一个分岔点，沿其中一条路径 $P$ 向下走，直到走到该路径向右的拐点，记为 $(i,j)$ (左上角坐标记为 $(0,0)$)。将其向内翻折，即从该路径中去掉 $(i,j)$，增加它右上角的点 $(i-1,j+1)$。得到的新路径 $P'$ 仍然是合法的，经过 $1$ 的次数与 $P$ 相差不超过 $1$，且不断重复这一过程最终可得到目标路径。

那么只需用 DP 求出所有合法路径中经过 $1$ 的最小及最大次数即可。复杂度 $O(nm)$。

```
/***************************************************
Author: hqztrue
https://github.com/hqztrue/LeetCodeSolutions
Complexity: O(nm)
If you find this solution helpful, plz give a star:)
***************************************************/
const int N=105;
int mi[N][N],ma[N][N];
inline void upd_mi(int &x,int y){if (y<x)x=y;}
inline void upd_ma(int &x,int y){if (y>x)x=y;}
class Solution {
public:
	bool isThereAPath(vector<vector<int>>& a) {
		int n=a.size(),m=a[0].size(),t=(n+m-1)/2;
		if ((n+m-1)%2)return 0;
		for (int i=0;i<n;++i)memset(mi[i],0x3f,sizeof(int)*m),memset(ma[i],0,sizeof(int)*m);
		mi[0][0]=ma[0][0]=a[0][0];
		for (int i=0;i<n;++i)
			for (int j=0;j<m;++j){
				int x=a[i][j];
				if (i>=1)upd_mi(mi[i][j],mi[i-1][j]+x),upd_ma(ma[i][j],ma[i-1][j]+x);
				if (j>=1)upd_mi(mi[i][j],mi[i][j-1]+x),upd_ma(ma[i][j],ma[i][j-1]+x);
			}
		return mi[n-1][m-1]<=t&&ma[n-1][m-1]>=t;
	}
};
```
# 2.位运算+动态规划，6行极简双百O(m*n)（2510. Check if There is a Path With Equal Number of 0's And 1's）
### 解题思路
![image.png](https://pic.leetcode.cn/1672302507-TPFviT-image.png)
注意到本题只允许向下和向右走，因此路径长度是固定的（k = m+n-1），因此如果路径长度k是奇数，一定无法完成题目要求，直接排除，如果k是偶数，那么该路径包含的0或1都恰好为k//2 = (m+n-1)//2 = (m+n)//2。所以本题转换为，是否存在一条路径使得经过1的数量恰好为k//2。
对于一个如图所示的01矩阵，我们可以从上至下，从左往右进行遍历，用一个同样大小的has矩阵记录每一个格子的【以该格子为终点的所有路径，经过1的数量的】可能的情况，例如{2,3}表示存在一条经过了2或3个1的路径到达该点，且不存在其他情况。如果k//2存在于终点格子的has中，则存在0和1各半的路径，否则没有。
![image.png](https://pic.leetcode.cn/1672304412-KeVAAh-image.png)
用二维列表+集合记录会比较麻烦，改用位运算来记录，如用12=0b1100=2^2+2^3来表示{2,3}。对于某个点，他最多从上方或者左边过来，不妨设上方点x={a1,a2,...,an}=2^a1+2^a2+...+2^an，左边点y={b1,b2,...,bn}=2^b1+2^b2+...+2^bn，合并所有路径得到z0={a1,a2,...,an}|{b1,b2,...,bn}=x|y，其中“|”表示按位或运算，如果该点的值为1，则所有路径都增加1，即z1=z0<<1。按顺序遍历结束后，查看终点的has值在第k//2位上是否为1，为1说明存在一条符合题意的路径，否则不存在。
### 代码

```python3
class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        m, n, nhas, x = len(grid), len(grid[0]), [1] + [0] * (len(grid[0])-1), 1
        if (m+n) % 2 == 0: return False
        for lis in grid:
            has, nhas = nhas, [nhas[0] << lis[0]]
            for i in range(1,n): nhas.append((nhas[-1]|has[i]) << lis[i])
        return 1 << ((m+n)//2) & nhas[-1] > 0
```
# 3.位运算优化动态规划
和[2267](https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path/)是一样的题，把所有可能的状态都继承过来(1就+1，0就-1)，并且可以把这个操作交给位运算


```Python3 []
class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        if (m + n) % 2 == 0:
            return False
        f = [[0] * n for _ in range(m)]
        f[0][0] = 1 << m + n
        for i in range(m):
            for j in range(n):
                if i > 0:
                    f[i][j] |= f[i - 1][j]
                if j > 0:
                    f[i][j] |= f[i][j - 1]
                if grid[i][j] == 1:
                    f[i][j] <<= 1
                else:
                    f[i][j] >>= 1
        return bool(f[-1][-1] & 1 << m + n)

```

```C++ []
class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if ((m + n) % 2 == 0) {
            return false;
        }
        vector<vector<bitset<210>>> dp(m + 1, vector<bitset<210>>(n + 1));
        dp[0][1].set(105);
        dp[1][0].set(105);
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (grid[i - 1][j - 1]) {
                    dp[i][j] = (dp[i - 1][j] << 1) | (dp[i][j - 1] << 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] >> 1) | (dp[i][j - 1] >> 1);
                }
            }
        }
        return dp[m][n][105];
    }
};
```
# 4.【Aya】Python 看到只有往右往下的规则限制的情况下就去考虑DP
### 解题思路
在lc上，有往右往下的规则限制的情况下就去考虑DP
如果四个方向都有，大概率是图的搜索
这里可以用一些trick来提升搜索效率
注意base的设置

### 代码

由于python支持负数索引，所以第三维开到m+n+1,使得k-1在k==0时候自动归到[][][m+n]上，而不用if来判断
c++不行。
```python3
class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        m,n = len(grid),len(grid[0])
        # 目标为 m+n-1个格子， 如果它本身都不是偶数，直接返回false
        if (m+n-1)%2 != 0:
            return False 
        # 只能往右边或者下边走
        # dp[i][j][k]表示到i,j格的时候，累计的1有k个
        # i,j本身是1
        # dp[i][j][k] = dp[i][j-1][k-1]|dp[i-1][j][k-1]
        # i,j本身不是1
        # dp[i][j][k] = dp[i][j-1][k]|dp[i-1][j][k]
        dp = [[[False for k in range(m+n+1)] for j in range(n+1)] for i in range(m+1)]
        # base: dp[0][1][0] = True 
        dp[0][1][0] = True 
        for i in range(1,m+1):
            for j in range(1,n+1):
                for k in range(i+j+1): # 这里可以优化成i+j+1
                    if grid[i-1][j-1] == 1:
                        dp[i][j][k] = dp[i][j-1][k-1]|dp[i-1][j][k-1]
                    else:
                        dp[i][j][k] = dp[i][j-1][k]|dp[i-1][j][k]
        return dp[m][n][(m+n-1)//2]
```

```
class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        if ((m+n-1)%2 != 0) {
            return false;
        }
        vector<vector<vector<bool>>> dp(m+1,vector<vector<bool>>(n+1,vector<bool>(m+n+1,false)));
        dp[0][1][0] = true;
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                for (int k = 0; k < i+j+1; k++) {
                    if (grid[i-1][j-1]) {
                        if (k-1 >= 0) {
                            dp[i][j][k] = dp[i-1][j][k-1] | dp[i][j-1][k-1];
                        }                      
                    } else {
                        dp[i][j][k] = dp[i-1][j][k] | dp[i][j-1][k];
                    }
                }
            }
        }
        return dp[m][n][(m+n-1)/2];
    }
};
```

