# 2604Minimum Time to Eat All Grains
<p>There are <code>n</code> hens and <code>m</code> grains on a line. You are given the initial positions of the hens and the grains in two integer arrays <code>hens</code> and <code>grains</code> of size <code>n</code> and <code>m</code> respectively.</p>

<p>Any hen can eat a grain if they are on the same position. The time taken for this is negligible. One hen can also eat multiple grains.</p>

<p>In <code>1</code> second, a hen can move right or left by <code>1</code> unit. The hens can move simultaneously and independently of each other.</p>

<p>Return <em>the <strong>minimum</strong> time to eat all grains if the hens act optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> hens = [3,6,7], grains = [2,4,7,9]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
One of the ways hens eat all grains in 2 seconds is described below:
- The first hen eats the grain at position 2 in 1 second. 
- The second hen eats the grain at position 4 in 2 seconds. 
- The third hen eats the grains at positions 7 and 9 in 2 seconds. 
So, the maximum time needed is 2.
It can be proven that the hens cannot eat all grains before 2 seconds.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> hens = [4,6,109,111,213,215], grains = [5,110,214]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
One of the ways hens eat all grains in 1 second is described below:
- The first hen eats the grain at position 5 in 1 second. 
- The fourth hen eats the grain at position 110 in 1 second.
- The sixth hen eats the grain at position 214 in 1 second. 
- The other hens do not move. 
So, the maximum time needed is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hens.length, grains.length &lt;= 2*10<sup>4</sup></code></li>
	<li><code>0 &lt;= hens[i], grains[j] &lt;= 10<sup>9</sup></code></li>
</ul>
































# 解题:
# 1.二分猜答案，验证的细节还是不少的
根据题意，每一堆粮食都必须得被吃，但每只鸡并不一定都要吃粮食。所以看上去将鸡和粮食的位置排好序后，用双指针分别指向当前最左端的鸡和当前未被吃的最左端的粮食，就可以计算答案了。但这里有个问题，对于当前未被吃的最左边的粮食，是让当前这只鸡吃更好，还是留给后面的鸡更好，这是无法确定的。所以我们可以换个思路，使用LC平台常见的二分猜答案套路，先预设一个时间，然后验证这个时间能否吃完。这样只要在允许的时间范围之内，每只鸡当然是吃的越多越好，这就可以枚举每只鸡，让其在待验证的时间内吃尽可能多的粮食了。

具体实现时，要注意如果一只鸡的左边和右边都有待吃的粮食，他就必须要掉头（但也只会掉头1次，否则肯定不是最优），而且可以选择先向右直到吃到右边最远的再掉头向左，或者先向左直到吃到左边最远的再掉头向右。所以枚举每只鸡时，要维护2个变量l和r，分别表示这只鸡要吃的最左边的粮食，和最右边的粮食离这只鸡初始位置的距离。总移动时间就是min(2 * l + r,2 * r + l)，如果这个时间不超过t，说明这堆粮食可以吃，继续看下一堆，否则就只能尝试让下一只鸡来吃了。

答案的最小值显然是0。最大值是多少呢？实际上不会超过最右端的鸡的坐标加上最右端的粮食的坐标。因为让最右边的鸡先一路向左移动到0，然后再一路向右移动到最右端的粮食那里，所有的粮食一定都被他吃了。


```
class Solution:
    def minimumTime(self, hens: List[int], grains: List[int]) -> int:

        hens.sort() # 注意题目没说有序，必须先排序，要保证鸡和粮食都是从左到右枚举
        grains.sort()

        def check(t):
            p = 0
            for j in range(len(hens)):
                l = 0
                r = 0
                while p<len(grains):
                    if grains[p]>hens[j]: #位于鸡右边的粮食，更新r
                        r = max(r,grains[p]-hens[j])
                    else: # 位于鸡左边的粮食，更新l
                        l = max(l,hens[j]-grains[p])
                    if min(l*2+r,r*2+l)<=t: # 只要两种掉头策略的最小值满足条件，这堆就可以吃
                        p+=1
                    else:
                        break #这只鸡已经尽力了，换下一只
            return p==len(grains) # 只有所有粮食都被吃了，才能返回True
        
        return bisect_left(range(max(hens)+max(grains)+1),1,key=check)
```

时间复杂度：O(nlogn+mlogm+(m+n)logU),n为hens的长度，m为grains的长度，U为两个数组的值域。对两个数组分别排序需要O(nlogn+mlogm)的时间，二分验证需要logU次，每次验证的时间为O(m+n)。
空间复杂度：O(logn+logm)，验证函数并没有额外空间，主要空间门槛在于排序。这里忽略为了调用bisect库而使用的Python内置的range，如果手动二分查找，这里的额外空间复杂度可以避免。
# 2.python3 二分 + 双指针
### 解题思路
由于答案具有单调性，考虑对时间进行二分；将两个数组排序后，每次check时用双指针分别遍历两个数组，对于每个母鸡尽可能多地给它当前时间内能够获得的粮食即可。

### 代码

```python3
class Solution:
    def minimumTime(self, hens: List[int], grains: List[int]) -> int:
        hens.sort()
        grains.sort()
        n,m = len(hens),len(grains)
        l,r = 0,grains[-1] - grains[0] + abs(hens[0] - grains[0])
        def check(x):
            j = 0
            for i in range(n):
                if j == m:
                    return True
                indj = grains[j]
                indi = hens[i]
                if indj <= indi: 
                    d = indi - indj
                    if d > x:
                        continue
                    while j < m and grains[j] <= indi:
                        j += 1
                    while j < m and min(d,grains[j] - indi) + grains[j] - indj <= x:
                        j += 1
                else:
                    while j < m and grains[j] - indi <= x:
                        j += 1
            return j == m 
     
        while l <= r:
            mid = l + (r - l) // 2
            if check(mid):
                r = mid - 1
            else:
                l = mid + 1
        return r + 1
```
