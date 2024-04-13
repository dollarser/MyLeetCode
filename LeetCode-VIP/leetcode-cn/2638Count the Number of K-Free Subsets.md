# 2638Count the Number of K-Free Subsets
<p>You are given an integer array <code>nums</code>,&nbsp;which contains <strong>distinct</strong> elements and an integer <code>k</code>.</p>

<p>A subset is called a <strong>k-Free</strong> subset if it contains <strong>no</strong> two elements with an absolute difference equal to <code>k</code>. Notice that the empty set is a <strong>k-Free</strong> subset.</p>

<p>Return <em>the number of <strong>k-Free</strong> subsets of </em><code>nums</code>.</p>

<p>A <b>subset</b> of an array is a selection of elements (possibly none) of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,6], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 valid subsets: {}, {5}, {4}, {6} and {4, 6}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,8], k = 5
<strong>Output:</strong> 12
<strong>Explanation:</strong> There are 12 valid subsets: {}, {2}, {3}, {5}, {8}, {2, 3}, {2, 3, 5}, {2, 5}, {2, 5, 8}, {2, 8}, {3, 5} and {5, 8}.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,5,9,11], k = 20
<strong>Output:</strong> 16
<strong>Explanation:</strong> All subsets are valid. Since the total count of subsets is 2<sup>4 </sup>= 16, so the answer is 16. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
</ul>
































# 解题:
# 1.按对k取模分组+线性DP
这道题和主站2597题是重复题目，但数据范围已足够卡掉一切暴力解法。相比2597题，这道题没有重复元素，但我还是认为这道题难度足够当周赛第三题。

如果两个元素的差值正好是k，那么这两个数对k一定是同余的。因此可以先对数组排序并按对k取模分组，不同组之间绝无冲突可能，对每组求出选取方案数之后，所有组的方案数相乘就是最终答案（乘法原理）。对于每一组而言，可以用动态规划的方法求有效方案数，设dp[j]为处理完下标j之前的方案数，dp[0]设为2，对应选或不选首项。对于大于0的下标j而言，grp[j]-grp[j-1]必然表示成a * k。如果a>1，那么不管选grp[j]，grp[j-1]都是可以选的，因此dp[j]=2*dp[j-1]。而如果a=1，如果grp[j-1]被选，那么grp[j]被迫放弃。如果想不放弃grp[j]，上一个被选的元素最大也只能是grp[j-2]（由于没有重复元素，grp[j-2]和grp[j]至少相差2k），因此dp[j]=dp[j-1]+dp[j-2]。注意如果j=1，那么j-2是非法下标，dp[j-2]应当视为1，这是因为空集也是一种方案。这个做法有一定的“打家劫舍”的思想，但思维难度更高一些。

由于这个做法是要求每组元素都是有序的，因此可以先对nums整体排序，这样每组元素自然就是有序的了。

实际上本题的数据范围完全可以给到数组长度10^5，值域10^9，并要求对方案数取模。如果真的如此，只要在下面的代码里保留被注释掉的3行即可（注意本题答案可以超过32位整数，删了注释会WA！）~

```
class Solution:
    def countTheNumOfKFreeSubsets(self, nums: List[int], k: int) -> int:

        nums.sort() #先对整个数组排序，这样每组就自然有序了
        grps = defaultdict(list) # Python也可二维列表，第一维长度为k，但用哈希表能适用值域和k很大的情形
        # mod = 10**9+7

        for num in nums: # 对k取模排序分组
            grps[num%k].append(num)

        ans = 1
        for grp in grps.values():
            dp = [0]*len(grp)
            dp[0]=2 #首项可以选或不选
            for j in range(1,len(grp)):
                if grp[j]-grp[j-1]==k:
                    dp[j]=dp[j-1]+dp[j-2] if j>1 else dp[j-1]+1 # 这时选当前项则不能选上一项，注意非法下标表示空集，方案数为1
                else:
                    dp[j]=2*dp[j-1] # 这时选不选当前项，上一项都能选
                # dp[j]%=mod
            ans*=dp[-1] #乘法原理
            # ans%=mod
        return ans
```

时间复杂度：O(nlogn)，n为原数组长度，DP的复杂度是线性的，本题主要复杂度门槛在排序
空间复杂度：O(n),分组和DP都需要和原数组同级别的额外空间。
# 2.python3动态规划
类似打家劫舍
```
dp = [1, 1]
pre = 1
for _ in range(49):
    dp.append(pre)
    pre += dp[-2]
for i in range(1, len(dp)):
    dp[i] += dp[i - 1]

class Solution:
    def countTheNumOfKFreeSubsets(self, nums: List[int], k: int) -> int:
        nums.sort()
        used = set()
        s = set(nums)
        count = 1
        for num in nums:
            if num not in used:
                cur = 1
                while num + k in s:
                    num += k
                    cur += 1
                    used.add(num)
                count *= dp[cur]
        return count
```

