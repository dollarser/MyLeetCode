# 2524Maximum Frequency Score of a Subarray
<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>frequency score</strong> of an array is the sum of the <strong>distinct</strong> values in the array raised to the power of their <strong>frequencies</strong>, taking the sum <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<ul>
	<li>For example, the frequency score of the array <code>[5,4,5,7,4,4]</code> is <code>(4<sup>3</sup> + 5<sup>2</sup> + 7<sup>1</sup>) modulo (10<sup>9</sup> + 7) = 96</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> frequency score of a <strong>subarray</strong> of size </em><code>k</code><em> in </em><code>nums</code>. You should maximize the value under the modulo and not the actual value.</p>

<p>A <strong>subarray</strong> is a contiguous part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,1,2], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The subarray [2,1,2] has a frequency score equal to 5. It can be shown that it is the maximum frequency score we can have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1,1], k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> All the subarrays of length 4 have a frequency score equal to 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>
































# 解题:
# 1.线性做法，不用快速幂（Python/Java/C++/Go）
长为 $k$ 的滑动窗口。

对每个 $x=\textit{nums}[i]$ 创建一个栈，每遇到一个 $x$ 就把 $x$ 与栈顶的乘积入栈，滑出窗口时同理。这样就可以 $O(1)$ 地计算分数的变化量了。

```py [sol1-Python3]
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        MOD = 10 ** 9 + 7
        ans = score = 0
        st_map = {}
        for i, x in enumerate(nums):
            if x not in st_map:
                score += x
                st_map[x] = [x]
            else:
                last = st_map[x][-1]
                cur = last * x % MOD
                score += cur - last
                st_map[x].append(cur)
            if i >= k - 1:
                ans = max(ans, score % MOD)
                x = nums[i - k + 1]
                st = st_map[x]
                score -= st.pop()
                if st: score += st[-1]
                else: del st_map[x]
        return ans
```

```java [sol1-Java]
class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int maxFrequencyScore(int[] nums, int k) {
        int ans = 0;
        long score = 0;
        var stMap = new HashMap<Integer, Deque<Integer>>();
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            if (!stMap.containsKey(x)) {
                score += x;
                var st = new ArrayDeque<Integer>();
                st.push(x);
                stMap.put(x, st);
            } else {
                var st = stMap.get(x);
                long last = st.peek(), cur = last * x % MOD;
                score += cur - last;
                st.push((int) cur);
            }
            if (i >= k - 1) {
                ans = Math.max(ans, (int) ((score % MOD + MOD) % MOD));
                x = nums[i - k + 1];
                var st = stMap.get(x);
                score -= st.pop();
                if (st.isEmpty()) stMap.remove(x);
                else score += st.peek();
            }
        }
        return ans;
    }
}
```

```cpp [sol1-C++]
class Solution {
    const int MOD = 1e9 + 7;
public:
    int maxFrequencyScore(vector<int> &nums, int k) {
        int ans = 0;
        long score = 0;
        unordered_map<int, stack<int>> st_map;
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i];
            auto it = st_map.find(x);
            if (it == st_map.end()) {
                score += x;
                stack<int> st;
                st.push(x);
                st_map.emplace(x, st);
            } else {
                long last = it->second.top(), cur = last * x % MOD;
                score += cur - last;
                it->second.push(cur);
            }
            if (i >= k - 1) {
                ans = max(ans, int(score % MOD + MOD) % MOD);
                x = nums[i - k + 1];
                auto &st = st_map[x];
                score -= st.top();
                if (st.size() == 1) {
                    st_map.erase(x);
                } else {
                    st.pop();
                    score += st.top();
                }
            }
        }
        return ans;
    }
};
```

```go [sol1-Go]
func maxFrequencyScore(nums []int, k int) (ans int) {
	const mod int = 1e9 + 7
	score := 0
	stMap := map[int][]int{}
	for i, x := range nums {
		st, ok := stMap[x]
		if !ok {
			stMap[x] = []int{x}
			score += x
		} else {
			last := st[len(st)-1]
			cur := last * x % mod
			score += cur - last
			stMap[x] = append(st, cur)
		}
		if i >= k-1 {
			ans = max(ans, (score%mod+mod)%mod)
			x = nums[i-k+1]
			st = stMap[x]
			score -= st[len(st)-1]
			if len(st) == 1 {
				delete(stMap, x)
			} else {
				score += st[len(st)-2]
				stMap[x] = st[:len(st)-1]
			}
		}
	}
	return
}

func max(a, b int) int { if b > a { return b }; return a }
```

### 复杂度分析

- 时间复杂度：$O(n)$，其中 $n$ 为 $\textit{nums}$ 的长度。
- 空间复杂度：$O(k)$。

---

最后，欢迎关注【bilibili@灵茶山艾府】，每周更新算法教学视频~

# 2.滑动窗口+哈希表+快速幂，这道题目有2个坑点
这道题标为Hard是言过其实的，本身属于比较常规的滑动窗口+哈希表计数的题目，只是分数更新需要用到快速幂的技巧。本题解所用的语言有自带快速幂，对于其他主流语言，需要自行实现快速幂，如果不会，可以对照 https://leetcode.cn/problems/powx-n/solution/powx-n-by-leetcode-solution/ 进行学习~

我们维护一个长度始终k的滑动窗口，然后用一个哈希表记录这个滑动窗口内的所有元素计数，显然当前分数就等于哈希表里所有键值对的键值乘积之和。每次滑动而更新分数时，要先读取到当前元素的计数，根据这个计数决定增加或减少多少。例如当元素4的计数为6时，此时再增加一个4，就需要把原有的4^6换成4^7，即当前分数增量为(4-1) * 4^6，如果删除一个4，则需要把原有的4^6换成4^5，即当前分数减少量为(4-1) * 4^5。滑动窗口每滑动一步，都需要增加一个新元素，删除一个旧元素。由于数组中的元素可能重复非常多次，需要用到快速幂的技巧，否则总时间复杂度就会不可接受。

但注意有两个地方需要特判：
1.如果新增的元素不在哈希表中，那么应该加的分数是这个元素本身，如果要删的元素在哈希表里计数为1（即删了就没了），应该减的分数也是这个元素本身。但如果按照(a-1) * a^table[a] 的默认公式来计算，这种情况就会出错。换句话说，这道题目应该视任何数的0次方为0而不是1。
2.如果新增的元素和删除的元素相同，直接跳过不要做任何处理。还以元素4的计数为6时，如果滑动窗口走的这一步，要增加和要删除的元素都是4，按照默认公式计算，当前分数会增加4^7+4^5并减少2 * 4^6，而这两个数并不相等，事实上不难证明a>1时，a^(n+2)+a^n> 2 * a^(n+1)。

```
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:

        mod = 10**9+7

        table = defaultdict(int)
        for j in range(k):
            table[nums[j]]+=1

        cur = 0 #先计算出初始滑动窗口（从nums[0]到nums[k-1]）的分数
        for ke in table:
            cur+=pow(ke,table[ke],mod)
        cur%=mod

        ans = cur
        r = k #定长滑动窗口只要一个右指针即可
        while r<len(nums):
            numr = nums[r]
            numl = nums[r-k]
            if numr!=numl: # 如果这两个数相等，什么都不用做，否则会出错！
                cur+=(numr-1)*pow(numr,table[numr],mod) if table[numr] else numr #增加新元素，注意特判table里原来没有元素
                cur-=(numl-1)*pow(numl,table[numl]-1,mod) if table[numl]>1 else numl #删除旧元素，注意特判删了就没了的元素
                table[numr]+=1
                table[numl]-=1
            cur%=mod
            ans = max(ans,cur) 
            r+=1
        return ans
```

时间复杂度：O(nlogn)，n为数组长度，滑动窗口的滑动次数是O(n)级，如果重复现象严重，每次计算快速幂最多可能需要logn的时间
空间复杂度：本代码使用defaultdict为O(n)，可以优化到O(k)
# 3.滑动窗口+计数，13行极简附翻译（2524. Maximum Frequency Score of a Subarray）
### 题目翻译
给一个正数数组nums和一个正整数k，返回最大的k长度子串的分数。对子串中每一个不同的数v而言，其个数（频率）为m，则该数的分数为v^m，所有不同数的分数和对1000000007取余的值，即为子串的分数。
### 解题思路
利用滑动窗口计算所有k长度子串，对于某子串nums[i-k:i]的下一子串nums[i-k+1:i+1]，增加了1个v0=nums[i]，减少了1个v1=nums[i-k]，若两个数一样则分数不变，若二者不一样且不是第一次出现则前者贡献的分数由v0^c[v0]变成v0^(c[v0]+1)（c[x]表示x的个数），增加了(v0-1)\*v0^c[v0]，后者由v1^c[v1]变为v1^(c[v1]-1)，减少了(v1-1)\*v1^(c[v1]-1)。特别的，当一个数在子串中第一次加入时，计算结果为(v0-1)，修正额外加1，同理当一个数彻底从子串消失，额外减1。

### 代码

```python3
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        n, sm, c = len(nums), 0, Counter()
        for i in range(k):
            v0 = nums[i]
            sm = (sm + (v0-1) * pow(v0,c[v0],1000000007)) % 1000000007
            c[v0] += 1
        res = sm = sm + len(c)

        for i in range(k,n):
            v0, v1 = nums[i], nums[i-k]
            if v0 != v1:
                sm = (sm + (c[v0] == 0) + (v0-1) * pow(v0,c[v0],1000000007) - (c[v1] == 1) - (v1-1) * pow(v1,c[v1]-1,1000000007)) % 1000000007
                c[v0], c[v1] = c[v0] + 1, c[v1] - 1
                res = max(res, sm)     
        return res
```
