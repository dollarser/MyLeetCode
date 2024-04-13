# 2548Maximum Price to Fill a Bag
<p>You are given a 2D integer array <code>items</code> where <code>items[i] = [price<sub>i</sub>, weight<sub>i</sub>]</code> denotes the price and weight of the <code>i<sup>th</sup></code> item, respectively.</p>

<p>You are also given a <strong>positive</strong> integer <code>capacity</code>.</p>

<p>Each item can be divided into two items with ratios <code>part1</code> and <code>part2</code>, where <code>part1 + part2 == 1</code>.</p>

<ul>
	<li>The weight of the first item is <code>weight<sub>i</sub> * part1</code> and the price of the first item is <code>price<sub>i</sub> * part1</code>.</li>
	<li>Similarly, the weight of the second item is <code>weight<sub>i</sub> * part2</code> and the price of the second item is <code>price<sub>i</sub> * part2</code>.</li>
</ul>

<p>Return <em><strong>the maximum total price</strong> to fill a bag of capacity</em> <code>capacity</code> <em>with given items</em>. If it is impossible to fill a bag return <code>-1</code>. Answers within <code>10<sup>-5</sup></code> of the <strong>actual answer</strong> will be considered accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[50,1],[10,8]], capacity = 5
<strong>Output:</strong> 55.00000
<strong>Explanation:</strong> 
We divide the 2<sup>nd</sup> item into two parts with part1 = 0.5 and part2 = 0.5.
The price and weight of the 1<sup>st</sup> item are 5, 4. And similarly, the price and the weight of the 2<sup>nd</sup> item are 5, 4.
The array items after operation becomes [[50,1],[5,4],[5,4]]. 
To fill a bag with capacity 5 we take the 1<sup>st</sup> element with a price of 50 and the 2<sup>nd</sup> element with a price of 5.
It can be proved that 55.0 is the maximum total price that we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[100,30]], capacity = 50
<strong>Output:</strong> -1.00000
<strong>Explanation:</strong> It is impossible to fill a bag with the given item.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, weight<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity &lt;= 10<sup>9</sup></code></li>
</ul>
































# 解题:
# 1.java 贪心
### 解题思路
java 贪心
### 代码
```java
class Solution {
    public double maxPrice(int[][] items, int capacity) {
        Arrays.sort(items,(a,b)->b[0]*a[1]-a[0]*b[1]);//用单价排序
        double ans=0;
        for(int []temp:items){
            if(temp[1]<=capacity){
                ans+=temp[0];
                capacity-=temp[1];
            }
            else{
                ans+=temp[0]*capacity*1.0/temp[1];
                capacity=0;
                break;
            }
        }
        return capacity==0?ans:-1;
    }
}
```
# 2.python3贪心
```
class Solution:
    def maxPrice(self, items: List[List[int]], capacity: int) -> float:
        items.sort(key=lambda x: x[1] / x[0])
        price = 0
        for p, c in items:
            if c >= capacity:
                return price + p * capacity / c
            price += p
            capacity -= c
        return -1
```

# 3.python3 贪心 + 遍历
### 解题思路
由于每个物品可以分解为质量任意的两个物品,显然只要物品总质量大于等于背包容量就一定可以得到一种合法分配方案;那么我们贪心地从大到小选取单位价值最高的物品即可
时间复杂度:O(nlogn)

### 代码

```python3
class Solution:
    def maxPrice(self, items: List[List[int]], cap: int) -> float:
        if sum(w for p,w in items) < cap:
            return -1
        ans = 0
        items.sort(key = lambda x:-(x[0] / x[1]))
        l,n = 0,len(items)
        while l < n:
            if items[l][1] <= cap:
                ans += items[l][0]
                cap -= items[l][1]
                l += 1
            else:
                ans += items[l][0] / items[l][1] * cap
                break
        return ans
```
