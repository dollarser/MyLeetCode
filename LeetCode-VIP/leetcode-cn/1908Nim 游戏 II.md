# 1908Nim 游戏 II
<p>Alice 和&nbsp;Bob 交替进行一个游戏，<strong>由 Alice 先手</strong>。</p>

<p>在游戏中，共有&nbsp;<code>n</code>&nbsp;堆石头。在每个玩家的回合中，玩家需要 <strong>选择</strong> 任一非空石头堆，从中移除任意 <strong>非零</strong> 数量的石头。如果不能移除任意的石头，就输掉游戏，同时另一人获胜。</p>

<p>给定一个整数数组&nbsp;<code>piles</code> ，<code>piles[i]</code> 为 第&nbsp;<code>i</code>&nbsp;堆石头的数量，如果 Alice 能获胜返回&nbsp;<code>true</code><em>&nbsp;</em>，反之返回&nbsp;<code>false</code><em>&nbsp;。</em></p>

<p>Alice 和 Bob 都会采取<strong> 最优策略 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [1]
<strong>输出：</strong>true
<strong>解释：</strong>只有一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。piles = [0]。
- 第二回合，Bob 没有任何石头可以移除。Alice 获胜。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,1]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。 piles = [0,1]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,2,3]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 3 堆中 3 块石头。 piles = [1,2,0]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [1,1,0]。
- 第三回合，Alice 移除了第 1 堆中 1 块石头。piles = [0,1,0]。
- 第四回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 7</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能想出一个&nbsp;<strong>线性时间&nbsp;</strong>的解决方案吗？虽然这一答案可能超出了面试所需的范围，但了解它可能会很有趣。</p>
































# 解题:
# 1.【新手友好题解视频】详解博弈论 / 方法通吃 / Game of Nim
**视频讲解**

含 博弈论基本定义、题目解释（方法）、易懂的数学证明

![【新手友好题解视频】详解博弈论 / 方法通吃 / Game of Nim.mp4](bce9a954-8c91-4c2a-b5b4-32f314afc3ce)


**复杂度**
时间复杂度O(n)
空间复杂度O(1)

**笔记**

Nim游戏先手胜败定理： 假设有 n 堆石头，每堆中石头数量分别是 a1, a2, ..., an ；若a1 ^ a2 ^ ...^ an = 0，则先手必败；若 a1 ^ a2 ^ ...^ an = x ≠ 0  ，则先手必胜。

证明一：
若有:
a1 ^ a2 ^ ... ^ an = x ≠ 0
x的二进制表示中最高位的1在第 k 位，说明 a1, a2, ..., an 中至少存在一个 ai ，其二进制表示中第 k 位是1；显然 ai ^ x < ai ( ai 第 k 位变为 0 )，若从第 i 堆中拿走若干石子，使得第 i 堆剩余石子数为 ai ^ x ，则此时各堆石子数的异或值就是 0 ：
a1 ^ a2 ^ ...^ (ai ^ x) ^ ... ^ an = 0
证明若有：
a1 ^ a2 ^ ... ^ an = x ≠ 0
一定存在某种方法从某堆中拿走若干石子，让剩下的各堆石子数异或值变成0，形成必败状态。

证明二：
若有：
a1 ^ a2 ^ ...^ ai ^ … ^ an = 0
假设从第i堆中拿走若干石子，第i堆当前的石子个数变为bi，若当前各堆石子数异或值还是等于0：
a1 ^ a2 ^ ...^ bi ^ ...^ an = 0
将上下两式左右两边同时异或得到：
bi ^ ai = 0
即bi = ai，产生矛盾。证明若有:
a1 ^ a2 ^ ...^ ai ^ … ^ an = 0
则先手必败。

**代码**

```C++ []
class Solution {
public:
    bool nimGame(vector<int>& piles) {
        int t = 0;
        for(int p : piles){
            t ^= p;
        }
        return t != 0;
    }
};
```
```Java []
class Solution {
    public boolean nimGame(int[] piles) {
        int x = 0;
        for (int num : piles){
            x ^= num;
        }
        return x != 0;
    }
}
```
```JavaScript []
/**
 * @param {number[]} piles
 * @return {boolean}
 */
var nimGame = function(piles) {
     let x
     for(let d of piles)
     {
         x^=d
     }
     return x!=0
};
```
```Rust []
impl Solution {
    pub fn nim_game(piles: Vec<i32>) -> bool {
        let mut ans = 0;
        for pile in piles {
            ans ^= pile;
        }
        ans != 0
    }
}
```
# 2.1908. Nim 游戏 II
# 解法一

## 思路和算法

大多数博弈问题可以通过动态规划方法求解，这道题也是如此。

动态规划的求解过程包括状态定义、边界条件和状态转移方程三个要素，对于博弈问题，需要计算的答案即为初始状态的值。

由于这道题的具体实现较为复杂，因此首先列举动态规划的三个要素，然后考虑具体实现。

- 状态定义：每堆石子在特定数量的情况下，当前玩家的游戏结果。

- 边界条件：由于游戏结束的条件是当一个玩家无法继续取走石子时，该玩家落败，因此边界条件是当所有石子堆都只剩下 $0$ 个石子时，结果为失败。

- 状态转移方程：当前玩家可以选取任意一个非空的石子堆，在选定的堆至少取走 $1$ 个石子，至多取走该堆的全部石子，然后轮到另一个玩家。由于两个玩家都采取最优策略，因此如果当前玩家存在至少一个取走石子的方案，使得另一个玩家面临必败状态，则当前玩家一定会选取这样的方案，使得自己必胜，如果当前玩家不存在方案使得另一个玩家面临必败状态，则当前玩家将面临失败。

以下用具体例子说明状态转移方程的含义。

例 1：$\textit{piles}=[1]$。

Alice 先手，只有一种选择，即取走唯一的石子堆的 $1$ 个石子。在 Alice 取走石子之后，$\textit{piles}=[0]$，即边界状态，此时 Bob 无法继续取走石子，因此 Bob 失败，Alice 获胜。

例 2：$\textit{piles}=[1,1]$。

Alice 先手，只能在两堆石子中任意选一堆，取走该堆的 $1$ 个石子。在 Alice 取走石子之后，$\textit{piles}=[0,1]$ 或 $\textit{piles}=[1,0]$，这两种状态下，Bob 都可以取走最后 $1$ 个石子获胜，因此 Alice 失败。

例 3：$\textit{piles}=[2]$。

Alice 先手，有两种选择，即取走唯一的石子堆的 $1$ 个石子或 $2$ 个石子。

- 如果 Alice 取走 $1$ 个石子，则 $\textit{piles}=[1]$，此时轮到 Bob，根据例 1 可知，Bob 将获胜，Alice 将失败。

- 如果 Alice 取走 $2$ 个石子，则 $\textit{piles}=[0]$，此时 Bob 面临必败状态。

在上述两个方案中，Alice 的最优策略是选择第二种方案，使得 Bob 面临必败状态，从而让自己获胜。

## 实现

动态规划的状态定义为每堆石子的数量对应当前玩家的游戏结果。由于每堆石子的数量以数组的形式给出，而数组本身不同于单个数字可以直接用于索引，因此需要将数组进行处理。

用 $n$ 表示数组 $\textit{piles}$ 的长度，$m$ 表示数组 $\textit{piles}$ 中的最大元素，则数组 $\textit{piles}$ 可以转换成一个 $n$ 位的 $m+1$ 进制数。这道题的数据范围是 $1 \le n \le 7$ 和 $1 \le m \le 7$，因此 $n$ 位的 $m+1$ 进制数最大为 $7777777_{(8)}=2^{21}-1$，可以用 $32$ 位整型表示。

将数组 $\textit{piles}$ 转换成 $n$ 位的 $m+1$ 进制数时，数组从左到右的每个元素分别对应转换后的整数的**从低到高**的每一位。例如，$\textit{piles}=[1,2,3]$ 转换成 $4$ 进制数是 $321_{(4)}$。

由于状态不一定连续，因此使用哈希表而不是数组存储状态。初始状态在哈希表中的表示为 $(0, \text{false})$。

假设数组 $\textit{piles}$ 转换成 $n$ 位的 $m+1$ 进制数为 $\textit{state}$，可以根据 $\textit{state}$ 反向推出 $\textit{piles}$ 的各元素值，即可知道每一堆剩余的石子数量。状态转移时，遍历每一种可能的取走石子数量的方案。假设在下标 $i$ 的位置取走 $j$ 个石子（$1 \le j \le \textit{piles}[i]$），则取走石子之后，数组 $\textit{piles}$ 对应的 $n$ 位的 $m+1$ 进制数为 $\textit{nextState}=\textit{state} - j \times (m+1)^i$。由于 $\textit{nextState}<\textit{state}$，因此得到规模更小的问题。

对于当前的 $\textit{state}$，如果存在一个 $\textit{nextState}$ 使得哈希表中 $\textit{nextState}$ 对应的结果为 $\text{false}$，则 $\textit{state}$ 对应的结果为 $\text{true}$，将 $(\textit{state}, \text{true})$ 存入哈希表，否则 $\textit{state}$ 对应的结果为 $\text{false}$，将 $(\textit{state}, \text{false})$ 存入哈希表。

用 $\textit{total}$ 表示**原始** $\textit{piles}$ 转换成的 $n$ 位的 $m+1$ 进制数，则最终答案即为 $\textit{total}$ 对应的结果。

## 代码

```Java [sol1-Java]
class Solution {
    public boolean nimGame(int[] piles) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }
        int n = piles.length;
        int[] radices = new int[n];
        radices[0] = 1;
        for (int i = 1; i < n; i++) {
            radices[i] = radices[i - 1] * (maxPile + 1);
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += piles[i] * radices[i];
        }
        Map<Integer, Boolean> memo = new HashMap<Integer, Boolean>();
        memo.put(0, false);
        return dp(memo, n, radices, total);
    }

    public boolean dp(Map<Integer, Boolean> memo, int n, int[] radices, int state) {
        if (!memo.containsKey(state)) {
            int[] piles = new int[n];
            int remain = state;
            for (int i = n - 1; i >= 0; i--) {
                piles[i] = remain / radices[i];
                remain %= radices[i];
            }
            boolean flag = false;
            for (int i = 0; i < n && !flag; i++) {
                for (int j = 1; j <= piles[i]; j++) {
                    int nextState = state - j * radices[i];
                    boolean nextFlag = dp(memo, n, radices, nextState);
                    if (!nextFlag) {
                        flag = true;
                        break;
                    }
                }
            }
            memo.put(state, flag);
        }
        return memo.get(state);
    }
}
```

```C# [sol1-C#]
public class Solution {
    public bool NimGame(int[] piles) {
        int maxPile = 0;
        foreach (int pile in piles) {
            maxPile = Math.Max(maxPile, pile);
        }
        int n = piles.Length;
        int[] radices = new int[n];
        radices[0] = 1;
        for (int i = 1; i < n; i++) {
            radices[i] = radices[i - 1] * (maxPile + 1);
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += piles[i] * radices[i];
        }
        Dictionary<int, bool> memo = new Dictionary<int, bool>();
        memo.Add(0, false);
        return dp(memo, n, radices, total);
    }

    public bool dp(Dictionary<int, bool> memo, int n, int[] radices, int state) {
        if (!memo.ContainsKey(state)) {
            int[] piles = new int[n];
            int remain = state;
            for (int i = n - 1; i >= 0; i--) {
                piles[i] = remain / radices[i];
                remain %= radices[i];
            }
            bool flag = false;
            for (int i = 0; i < n && !flag; i++) {
                for (int j = 1; j <= piles[i]; j++) {
                    int nextState = state - j * radices[i];
                    bool nextFlag = dp(memo, n, radices, nextState);
                    if (!nextFlag) {
                        flag = true;
                        break;
                    }
                }
            }
            memo.Add(state, flag);
        }
        return memo[state];
    }
}
```

## 复杂度分析

- 时间复杂度：$O((m+1)^n)$，其中 $n$ 是数组 $\textit{piles}$ 的长度，$m$ 是数组 $\textit{piles}$ 中的最大元素。需要计算全部状态的值，最坏情况下，状态有 $(m+1)^n$ 个。

- 空间复杂度：$O((m+1)^n)$，其中 $n$ 是数组 $\textit{piles}$ 的长度，$m$ 是数组 $\textit{piles}$ 中的最大元素。需要使用哈希表记录全部状态，最坏情况下，状态有 $(m+1)^n$ 个。

# 解法二

## 思路和算法

上述动态规划方法的时间复杂度为指数级。其实，有一个简单的结论可以用于判断 Nim 游戏的结果：Nim 游戏中，当且仅当所有堆的石子数量的异或结果**不等于** $0$ 时，先手必胜。

利用该结论，可以将时间复杂度降低到 $O(n)$。

## 证明

在证明上述结论之前，首先定义两个概念：「必胜状态」和「必败状态」。这两个概念在动态规划方法中已经提及，这里给出更明确的定义。

1. 根据游戏规则，如果轮到的玩家无法继续操作，则该玩家落败，因此无法继续操作的状态为必败状态。这道题中，无法继续操作的状态是所有石子堆都只剩下 $0$ 个石子。

2. 从特定状态开始，存在一种操作将状态变成必败状态，则该特定状态为必胜状态。

3. 从特定状态开始，所有操作都会将状态变成必胜状态，则该特定状态为必败状态。

Nim 游戏中，所有石子堆都只剩下 $0$ 个石子时，为必败状态，此时所有堆的石子数量的异或结果为 $0$。

当所有堆的石子数量的异或结果不为 $0$ 时，即 $\textit{piles}[0] \oplus \textit{piles}[1] \oplus \ldots \oplus \textit{piles}[n-1] = k \ne 0$。令 $2^h \le k < 2^{h+1}$，即 $2^h$ 为 $k$ 的二进制表示中只保留最高位 $1$ 的结果，则一定存在 $0 \le i < n$，使得 $\textit{piles}[i] ~\&~ 2^h = 2^h$，此时必有 $\textit{piles}[i] \oplus k < \textit{piles}[i]$，因此可以在下标 $i$ 的堆中取走若干石子，使得该堆石子变成 $\textit{piles}[i] \oplus k$，即更新 $\textit{piles}[i]$ 的值为 $\textit{piles}[i] \oplus k$，更新 $\textit{piles}[i]$ 之后，$\textit{piles}[0] \oplus \textit{piles}[1] \oplus \ldots \oplus \textit{piles}[n-1] = 0$，即所有堆的石子数量的异或结果为 $0$。

当所有堆的石子数量的异或结果为 $0$ 时，即 $\textit{piles}[0] \oplus \textit{piles}[1] \oplus \ldots \oplus \textit{piles}[n-1] = 0$。根据异或性质，任意一堆石子的数量都等于其余所有堆的石子数量的异或。如果在下标 $i$ 的堆中取走若干石子，则在取走石子前，其余 $n-1$ 堆石子数量的异或等于 $\textit{piles}[i]$，在取走石子后，$\textit{piles}[i]$ 的值改变了，因此其余 $n-1$ 堆石子数量的异或不等于 $\textit{piles}[i]$，即所有堆的石子数量的异或结果不为 $0$。

以上三点可以概括如下：

1. 所有石子堆都只剩下 $0$ 个石子时，为必败状态，此时所有堆的石子数量的异或结果为 $0$。

2. 当所有堆的石子数量的异或结果不为 $0$ 时，一定存在一个方案，使得取走石子之后，所有堆的石子数量的异或结果为 $0$。

3. 当所有堆的石子数量的异或结果为 $0$ 时，对于所有方案，都会使得取走石子之后，所有堆的石子数量的异或结果不为 $0$。

由此可以定义 Nim 游戏的「必胜状态」和「必败状态」：所有堆的石子数量的异或结果不为 $0$ 为必胜状态，所有堆的石子数量的异或结果为 $0$ 为必败状态。

根据 $\textit{pile}$ 的初始状态可知先手是必胜状态还是必败状态，得到游戏结果。

## 代码

```Java [sol2-Java]
class Solution {
    public boolean nimGame(int[] piles) {
        int xor = 0;
        for (int pile : piles) {
            xor ^= pile;
        }
        return xor != 0;
    }
}
```

```C# [sol2-C#]
public class Solution {
    public bool NimGame(int[] piles) {
        int xor = 0;
        foreach (int pile in piles) {
            xor ^= pile;
        }
        return xor != 0;
    }
}
```

## 复杂度分析

- 时间复杂度：$O(n)$，其中 $n$ 是数组 $\textit{piles}$ 的长度。需要遍历数组一次计算异或结果。

- 空间复杂度：$O(1)$。
# 3.【C++】数学O(n) 异或和  以及 关于异或和的解释（Sprague-Grundy定理） （附题目翻译）
### 解题思路
[Sprague-Grundy定理是怎么想出来的](https://zhuanlan.zhihu.com/p/20611132)

结论涉及的证明参考上述链接，下面只利用部分结论

**简述：** 
这个游戏的基础状态可以划分为多个相互独立的子态（每堆单独划分）

子态对组合形成的父态的影响，与子态是胜态还是败态，胜态距离败态的距离有关，定义为SG(x)

如果子态不能继续细分，子态的SG等于其 次态的SG值 **组成的集合** **之外** 的最小的自然数

对于本题，同一堆石头就不可继续细分

1块石头的sg值 $sg(1) = mex\{sg(0)\} = mex{0} = 1$
`（mex表示不属于集合的最小非负整数，是minimum excludant的缩写。）`

同理 $sg(2) = mex\{0, 1\} = 2;$ $sg(n) = mex\{0,1,2...n-1\} = n;$

因此，子态sg并不需要主动计算。

最后再由所有子态求出基础状态的sg（等于所有子态sg值的异或和，子态sg就是每堆石头的块数）
### 代码

```cpp
class Solution {
public:
    bool nimGame(vector<int>& piles) {
        return accumulate(begin(piles), end(piles), int(), bit_xor());
    }
};
```

# 翻译 （如果你看到这个题是中文版，说明这个翻译过审了）
```
Alice 和 Bob 交替进行一个游戏，由 Alice 先手。

在游戏中，共有 n 堆石头。在每个玩家的回合中，玩家需要选择任一非空石头堆，从中移除任意非零数量的石头。如果不能移除任意的石头，就输掉游戏，同时另一人获胜。

给定一个整数数组 piles, piles[i] 为 第 i 堆石头的数量，如果Alice能获胜返回 true ，反之返回 false 。

Alice 和 Bob 都会采取最优策略。


示例 1:

输入：piles = [1]
输出：true
解释: 只有一种可能的情况:
- 第一回合，Alice 移除了第 1 堆中 1 块石头。piles = [0]。
- 第二回合，Bob 没有任何石头可以移除。Alice 获胜。

示例 2:

输入：piles = [1,1]
输出：false
解释: 可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。 piles = [0,1]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。

示例 3:

输入：piles = [1,2,3]
输出：false
解释: 可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 3 堆中 3 块石头。 piles = [1,2,0]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [1,1,0]。
- 第三回合，Alice 移除了第 1 堆中 1 块石头。piles = [0,1,0]。
- 第四回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。


提示：

n == piles.length
1 <= n <= 7
1 <= piles[i] <= 7


补充: 你能想出一个 线性时间 的解决方案吗？虽然这一答案可能超出了面试所需的范围，但了解它可能会很有趣。
```
# 4.Sprague-Grundy theorem. Nim

这个定理由Roland Sprague在1935和Patrick Michael Grundy在1939分别独立给出。
定理描述了所谓“公正的”双人回合制游戏，即玩家是否胜利仅取决于当前的状态。
此外我们假设这个游戏是：
1. 在有限步骤内一定可以决出胜负（无平局）；
2. 游戏双方的信息是公开透明的。

这类游戏可以用无圈有向图表示，每个状态表示一个点，状态A可以转换成状态B，则AB为一条有向边。若一个顶点没有出边，则该状态为losing。
因为我们要求无平局，因此每个顶点都可以对应到losing状态 或者 winning 状态。一个losing状态的下一状态一定都是winning状态，否则就可以选择一个对手的losing状态，也就是自己的winning状态。

用SG(A)表示状态A的输赢。若一个状态x可以从若干相互独立的状态x_1,x_2...x_k得到，则
SG(A) = SG(x_1)^SG(x_2)^...SG(x_k)
证明可以用归纳法，参考链接
https://cp-algorithms.com/game_theory/sprague-grundy-nim.html

例如此题为Nim游戏，若干堆石子的胜负可以转换成每一堆石子的胜负做异或。一堆石子只要大于0就是胜，等于0就是负。因此只需对所有堆石子的数量做异或。
设最后异或得到的结果是s，s的二进制最高非零位是d，选一个d位为1的石子堆x取s^x堆石子。依次进行。
代码：
```
bool nimGame(vector<int>& piles) {
        int win = 0;
        for(int x:piles){
            win = win^x;
        }
        return win!=0;
    }
```

