# 2519Count the Number of K-Big Indices
<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a positive integer <code>k</code>.</p>

<p>We call an index <code>i</code> <strong>k-big</strong> if the following conditions are satisfied:</p>

<ul>
	<li>There exist at least <code>k</code> different indices <code>idx1</code> such that <code>idx1 &lt; i</code> and <code>nums[idx1] &lt; nums[i]</code>.</li>
	<li>There exist at least <code>k</code> different indices <code>idx2</code> such that <code>idx2 &gt; i</code> and <code>nums[idx2] &lt; nums[i]</code>.</li>
</ul>

<p>Return <em>the number of k-big indices</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,6,5,2,3], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are only two 2-big indices in nums:
- i = 2 --&gt; There are two valid idx1: 0 and 1. There are three valid idx2: 2, 3, and 4.
- i = 3 --&gt; There are two valid idx1: 0 and 1. There are two valid idx2: 3 and 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1], k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no 3-big indices in nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li>
</ul>
































# 解题:
# 1.统计K-Big切片数量--树状数组
### 解题思路
树状数组模板

### 代码

```cpp
class BIT{
    int n;
    vector<int> tree;
    int lowbit(int idx){
        return idx & -idx;
    }

public:
    BIT(int n){
        this->n = n;
        tree.resize(n);
    }

    void add(int idx, int x){
        for(int i = idx + 1; i <= n; i += lowbit(i)){
            tree[i - 1] += x;
        }
    }

    int get(int idx){
        int ans = 0;
        for(int i = idx + 1; i > 0; i -= lowbit(i)){
            ans += tree[i - 1];
        }
        return ans;
    }
};

class Solution {
public:
    int kBigIndices(vector<int>& nums, int k) {
        int n = nums.size();
        if(k * 2 >= n){
            return 0;
        }
        BIT left(n + 1), right(n + 1);
        for(auto x : nums){
            right.add(x, 1);
        }
        int ans = 0;
        for(auto x : nums){
            right.add(x, -1);
            if(left.get(x - 1) >= k && right.get(x - 1) >= k){
                ans++;
            }
            left.add(x, 1);
        }
        return ans;
    }
};
```
# 2.python3+树状数组模板
```
class BIT:
    def __init__(self, n):
        self.n = n
        self.a = [0] * (n + 1)

    def lowbit(x):
        return x & -x

    def update(self, x):
        while x <= self.n:
            self.a[x] += 1
            x += BIT.lowbit(x)

    def query(self, x):
        res = 0
        while x:
            res += self.a[x]
            x -= BIT.lowbit(x)
        return res


class Solution:
    def kBigIndices(self, nums: List[int], k: int) -> int:
        bit = BIT(n := len(nums))
        ans, sm = 0, 0
        c = defaultdict(list)
        for i, x in enumerate(nums): c[x].append(i + 1)
        for x in sorted(c):
            for i in c[x]:
                a = bit.query(i)
                ans += int(sm - k >= a >= k)
            for i in c[x]:
                bit.update(i)
            sm += len(c[x])
        return ans
```
# 3.python3+排序列表+二分
```
from sortedcontainers import SortedList


class Solution:
    def kBigIndices(self, nums: List[int], k: int) -> int:
        sl = SortedList()
        arr = sorted(nums)
        ans = 0
        for x in nums:
            a = sl.bisect_left(x)
            b = bisect_left(arr, x)
            if a >= k and b - a >= k:
                ans += 1
            sl.add(x)
        return ans
```
# 4.python3树状数组
```
class BinaryIndexTree:
    def __init__(self, lst):
        self.lst = [0] + lst
        for i in range(1, len(self.lst)):
            j = i + self.lowbit(i)
            if j < len(self.lst):
                self.lst[j] += self.lst[i]

    @staticmethod
    def lowbit(x):
        return x & - x

    def _query(self, index):
        res = 0
        index += 1
        while index > 0:
            res += self.lst[index]
            index -= self.lowbit(index)
        return res

    def query(self, start, end):
        return self._query(end) - self._query(start - 1)

    def add(self, index, value):
        index += 1
        while index < len(self.lst):
            self.lst[index] += value
            index += self.lowbit(index)

class Solution:
    def kBigIndices(self, nums: List[int], k: int) -> int:
        max_num = max(nums)
        left_dict = {}
        right_dict = {}
        bit = BinaryIndexTree([0] * (max_num + 1))
        for i, num in enumerate(nums):
            left_dict[i] = bit.query(1, num - 1)
            bit.add(num, 1)
        bit = BinaryIndexTree([0] * (max_num + 1))
        for i in range(len(nums) - 1, -1, -1):
            num = nums[i]
            right_dict[i] = bit.query(1, num - 1)
            bit.add(num, 1)
        count = 0
        for i in range(len(nums)):
            if left_dict[i] >= k and right_dict[i] >= k:
                count += 1
        return count
```

