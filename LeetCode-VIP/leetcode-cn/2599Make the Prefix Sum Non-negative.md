# 2599Make the Prefix Sum Non-negative
<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You can apply the following operation any number of times:</p>

<ul>
	<li>Pick any element from <code>nums</code> and put it at the end of <code>nums</code>.</li>
</ul>

<p>The prefix sum array of <code>nums</code> is an array <code>prefix</code> of the same length as <code>nums</code> such that <code>prefix[i]</code> is the sum of all the integers <code>nums[j]</code> where <code>j</code> is in the inclusive range <code>[0, i]</code>.</p>

<p>Return <em>the minimum number of operations such that the prefix sum array does not contain negative integers</em>. The test cases are generated such that it is always possible to make the prefix sum array non-negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-5,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> we do not need to do any operations.
The array is [2,3,-5,4]. The prefix sum array is [2, 5, 0, 4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,-5,-2,6]
<strong>Output:</strong> 1
<strong>Explanation:</strong> we can do one operation on index 1.
The array after the operation is [3,-2,6,-5]. The prefix sum array is [3, 1, 7, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>
































# 解题:
# 1.贪心+优先队列模拟，每当前缀和为负时就把已经见过的最小负数移到最后
这道题和 https://leetcode.cn/problems/p0NxJO/ 是一样的题目，所以如果当做周赛第二题而不是会员题，这场周赛恐怕又要美国站正常结算，中国站unrate了。

这道题有一种比较直观的错误的贪心做法，就是每当前缀和为负时，就把当前这个负数移到最后。但这样做不见得最优，反例很明显，如[4,-4,-1,-1,-1,3],实际上只要把-4放到最后就可以了，只需要操作1次，但按照这个错误的贪心做法，却不得不把3个-1都放到最后，多操作了2次。但是只要前缀和出现负数，一定是需要操作的，所以出现这种情况的时候，应该把已经累加到前缀和里的最小的一个负数移到最后，对于这个例子，当遍历到-1时发现前缀和是负数时，把已经遍历过的-4移到后面，这样以后就再不会出现负数了。具体实现时，除了维护一个前缀和变量外，需要一个最小堆（优先队列）存放已经遍历过的元素。由于保证有解，出现负数时，从最小堆里弹出最小值，并让前缀和减去这个最小值就可以了。

这道题保证有解，而那个LCP题目不保证有解，怎么判断有解呢？其实只要整个数组的和非负就一定有解，大不了把所有负数全移到最后。

```
class Solution:
    def makePrefSumNonNegative(self, nums: List[int]) -> int:

        # if sum(nums)<0: return -1  #加上这一行，就可以通过LCP30了
        heap = []
        pre = 0
        ans = 0
        for num in nums:
            pre+=num
            heapq.heappush(heap,num) #因为不能排除需要移走的就是当前值，所以先push如果出问题了再pop
            if pre<0: 
                pre-=heapq.heappop(heap)
                ans+=1
        return ans
```

时间复杂度：O(nlogn)，n为数组长度，每个元素最多进堆和出堆各1次
空间复杂度：O(n)，当答案为0时，一个元素都弹不出去，时空复杂度就同时达到了上限

# 2.2599. Make the Prefix Sum Non-negative
```
class Solution {
public:
    int makePrefSumNonNegative(vector<int>& nums) {
        long long sum = 0LL;
        multiset<int> ms;
        int ops = 0;
        for(auto &x : nums){
            sum += x;
            if(x < 0){
                ms.insert(x);
            }
            if(sum < 0 && ms.size()){
                sum -= *ms.begin();
                ms.erase(ms.begin());
                ops++;
            }
        }
        return ops;
    }
};
```

# 3.贪心+堆
```
class Solution:
    def makePrefSumNonNegative(self, nums: List[int]) -> int:
        heap = list()
        s = 0
        ans = 0
        for i in range(len(nums)):
            s += nums[i]
            # 遍历过的负数可能需要放到最后
            if nums[i] < 0:
                heapq.heappush(heap, nums[i])
            # 前缀和小于0, 从堆拿出最小的数放到最后
            while heap and s < 0:
                x = heapq.heappop(heap)
                s -= x
                ans += 1
        return ans
```

# 4.python3 贪心 + 堆
### 解题思路
有点像加油次数那道题,遍历数组将所有不影响前缀和非负的负数入堆,如果当前负数使得前缀和小于0,判断能否将之前堆中的最小负数替换掉使得前缀和大于等于0,如果可以更新前缀和且对之前的最小负数执行操作,否则对当前负数进行操作。

### 代码

```python3
class Solution:
    def makePrefSumNonNegative(self, nums: List[int]) -> int:
        h = []
        ans = 0
        pre = 0
        for c in nums:
            if c >= 0:
                pre += c
            else:
                if pre + c < 0:
                    if not h or pre - h[0] + c < 0:
                        ans += 1
                    else:
                        ans += 1
                        pre -= heapq.heappop(h)
                        pre += c
                        heapq.heappush(h,c)
                else:
                    heapq.heappush(h,c)
                    pre += c
        return ans
```
