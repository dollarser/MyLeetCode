# 2613Beautiful Pairs
<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of the same length. A pair of indices <code>(i,j)</code> is called <strong>beautiful</strong> if<code>|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]|</code> is the smallest amongst all possible indices pairs where <code>i &lt; j</code>.</p>

<p>Return <em>the beautiful pair. In the case that there are multiple beautiful pairs, return the lexicographically smallest pair.</em></p>

<p>Note that</p>

<ul>
	<li><code>|x|</code> denotes the absolute value of <code>x</code>.</li>
	<li>A pair of indices <code>(i<sub>1</sub>, j<sub>1</sub>)</code> is lexicographically smaller than <code>(i<sub>2</sub>, j<sub>2</sub>)</code> if <code>i<sub>1</sub> &lt; i<sub>2</sub></code> or <code>i<sub>1</sub> == i<sub>2</sub></code> and <code>j<sub>1</sub> &lt; j<sub>2</sub></code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
<strong>Output:</strong> [0,3]
<strong>Explanation:</strong> Consider index 0 and index 3. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
<strong>Output:</strong> [1,4]
<strong>Explanation:</strong> Consider index 1 and index 4. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= nums1<sub>i</sub><sub>&nbsp;</sub>&lt;= nums1.length</code></li>
	<li><code>0 &lt;= nums2<sub>i</sub>&nbsp;&lt;= nums2.length</code></li>
</ul>
































# 解题:
# 1.[Python3/C++] 平面最近点对问题
**思路**
- 参考了[平面最近点对问题](http://oi-wiki.com/geometry/nearest-points/)的解法
- 额外处理了重复点对的情况

```Python3 []
from sortedcontainers import SortedList

class Solution:
    def beautifulPair(self, nums1: List[int], nums2: List[int]) -> List[int]:
        n = len(nums1)
        ans = [n, n]
        h = {}
        a = []
        for i, (x, y) in enumerate(zip(nums1, nums2)):
            if (x, y) in h:
                i0, i1 = min(h[(x, y)], i), max(h[(x, y)], i)
                if i0 < ans[0] or i0 == ans[0] and i1 < ans[1]:
                    ans[0] = i0
                    ans[1] = i1
            else:
                h[(x, y)] = i
            a.append((x, y, i))
        
        if ans[0] < n and ans[1] < n:
            return ans
        
        a.sort()
        mn = n * 3
        s = SortedList(key=lambda x: x[1])
        l = 0
        for i in range(n):
            while l < i and a[i][0] - a[l][0] > mn:
                s.remove(a[l])
                l += 1
            idx = s.bisect_left((a[i][0], a[i][1] - mn, -1))
            while idx < len(s) and s[idx][1] - a[i][1] <= mn:
                d = abs(s[idx][0] - a[i][0]) + abs(s[idx][1] - a[i][1])
                i0, i1 = min(s[idx][2], a[i][2]), max(s[idx][2], a[i][2])
                if d < mn or d == mn and i0 < ans[0] or d == mn and i0 == ans[0] and i1 < ans[1]:
                    mn = d
                    ans[0] = i0
                    ans[1] = i1
                idx += 1
            s.add(a[i])
        return ans
```
```C++ []
struct point {
    int x, y, i;
    point(int x = 0, int y = 0) : x(x), y(y) {}
};

struct cmp_x {
    bool operator()(const point &a, const point &b) const {
        return a.x < b.x || (a.x == b.x && a.y < b.y);
    }
};

struct cmp_y {
    bool operator()(const point &a, const point &b) const { return a.y < b.y; }
};

class Solution {
public:
    vector<int> beautifulPair(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> ans(2, n);
        map<pair<int, int>, int> mp;
        vector<point> a(n);
        for (int i = 0; i < n; i++) {
            if (mp.find(make_pair(nums1[i], nums2[i])) != mp.end()) {
                auto i0 = mp[{nums1[i], nums2[i]}], i1 = i;
                if (i0 < ans[0] || i0 == ans[0] && i1 < ans[1]) {
                    ans[0] = i0;
                    ans[1] = i1;
                }
            } else {
                mp[{nums1[i], nums2[i]}] = i;
            }
            a[i].x = nums1[i];
            a[i].y = nums2[i];
            a[i].i = i;
        }
        if (ans[0] < n && ans[1] < n) {
            return ans;
        }

        sort(a.begin(), a.end(), cmp_x());
        int mn = INT_MAX;
        multiset<point, cmp_y> s;
        for (int i = 0, l = 0; i < n; i++) {
            while (l < i && a[i].x - a[l].x > mn) s.erase(s.find(a[l++]));
            for (auto it = s.lower_bound(point(a[i].x, a[i].y - mn)); it != s.end() && it->y - a[i].y <= mn; it++) {
                auto p1 = *it, p2 = a[i];
                auto d = abs(p1.x - p2.x) + abs(p1.y - p2.y);
                auto i0 = min(p1.i, p2.i), i1 = max(p1.i, p2.i);
                if (d < mn || d == mn && i0 < ans[0] || d == mn && i0 == ans[0] && i1 < ans[1]) {
                    mn = d;
                    ans[0] = i0;
                    ans[1] = i1;
                }
            }
            s.insert(a[i]);
        }
        return ans;
    }
};
```

**复杂度**
- 时间复杂度：$O(nlogn)$
- 空间复杂度：$O(n)$
# 2.golang 曼哈顿距离平面最近点对
### 解题思路
参考 [平面最近点对分治求法](https://github.com/EndlessCheng/codeforces-go/blob/a707a2c9af5063a42fae95bcd38a0be21ea600cc/copypasta/geometry.go#L766)
注意特殊处理存在多个相等的点对的情况

### 代码

```golang
package main

import (
	"sort"
)

func beautifulPair(nums1 []int, nums2 []int) []int {
	points := make([][2]int, len(nums1))
	mp := make(map[[2]int][]int)
	for i := range points {
		points[i] = [2]int{nums1[i], nums2[i]}
		mp[points[i]] = append(mp[points[i]], i)
	}

	samePair := [][]int{}
	for _, ps := range mp {
		if len(ps) > 1 {
			samePair = append(samePair, []int{ps[0], ps[1]})
		}
	}

	if len(samePair) > 0 {
		// 字典序最小的点对
		sort.Slice(samePair, func(i, j int) bool {
			if samePair[i][0] != samePair[j][0] {
				return samePair[i][0] < samePair[j][0]
			}
			return samePair[i][1] < samePair[j][1]
		})
		return samePair[0]
	}

	_, id1, id2 := ClosestPair(points)
	return []int{id1, id2}
}

const INF int = 1e18

// 计算距离的平方的公式.
func calDist2(x1, y1, x2, y2 int) int {
	res := (abs(x1-x2) + abs(y1-y2))
	return res * res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

// 平面最近点对.返回(最近点对距离的平方, 点1的id, 点2的id).
//  如果存在多个相等的点对，返回字典序最小的对.
//  !调用 closestPair 前需保证没有重复的点.
//  https://github.dev/EndlessCheng/codeforces-go/tree/master/copypasta
func ClosestPair(points [][2]int) (dist2, pid1, pid2 int) {
	if len(points) <= 1 {
		return INF, 0, 0
	}
	pWithId := make([][3]int, len(points))
	for i, p := range points {
		pWithId[i] = [3]int{p[0], p[1], i}
	}
	sort.Slice(pWithId, func(i, j int) bool { return pWithId[i][0] < pWithId[j][0] })
	return _closestPair(pWithId)
}

func _closestPair(ps [][3]int) (dist, pid1, pid2 int) {
	n := len(ps)
	if n <= 1 {
		return INF, -1, -1
	}
	m := n >> 1
	x := ps[m][0]
	d1, pid1, pid2 := _closestPair(ps[:m])
	d2, pid3, pid4 := _closestPair(ps[m:])
	if d1 > d2 {
		d1, pid1, pid2 = d2, pid3, pid4
	} else if d1 == d2 && (pid1 > pid3 || (pid1 == pid3 && pid2 > pid4)) { // 字典序最小的点对
		pid1, pid2 = pid3, pid4
	}
	copy(ps, merge(ps[:m], ps[m:]))
	checkPs := [][3]int{}
	for _, pi := range ps {
		if (pi[0]-x)*(pi[0]-x) > d1 {
			continue
		}
		for j := len(checkPs) - 1; j >= 0; j-- {
			pj := checkPs[j]
			dy := pi[1] - pj[1]
			if dy*dy >= d1 { // > ?
				break
			}
			dx := pi[0] - pj[0]
			cand := calDist2(0, 0, dx, dy)
			if cand < d1 {
				d1, pid1, pid2 = cand, pi[2], pj[2]
			} else if cand == d1 && (pid1 > pi[2] || (pid1 == pi[2] && pid2 > pj[2])) { // 字典序最小的点对
				pid1, pid2 = pi[2], pj[2]
			}
		}
		checkPs = append(checkPs, pi)
	}
	if pid1 > pid2 {
		pid1, pid2 = pid2, pid1
	}
	return d1, pid1, pid2
}

func merge(a, b [][3]int) [][3]int {
	i, n := 0, len(a)
	j, m := 0, len(b)
	res := make([][3]int, 0, n+m)
	for {
		if i == n {
			return append(res, b[j:]...)
		}
		if j == m {
			return append(res, a[i:]...)
		}
		if a[i][1] < b[j][1] {
			res = append(res, a[i])
			i++
		} else {
			res = append(res, b[j])
			j++
		}
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```
