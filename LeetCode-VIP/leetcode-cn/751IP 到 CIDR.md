# 751IP 到 CIDR
<p><strong>IP地址</strong> 是一个格式化的 32位 无符号整数，每组 8位 被打印为一个十进制数字和，点字符&nbsp;<code>'.'</code>&nbsp;起到了分组的作用。</p>

<ul>
	<li>例如，二进制数 <code>00001111 10001000 11111111 01101011</code>&nbsp;( 为清晰起见增加了空格)被格式化为IP地址将是 <code>“15.136.255.107”</code> 。</li>
</ul>

<p><strong>CIDR块</strong> 是一种用来表示一组特定IP地址的格式。字符串形式，由基础IP地址、斜杠和前缀长度 <code>k</code> 组成。它所覆盖的地址是所有IP地址的 <strong>前 <code>k</code> 位</strong> 与基础IP地址相同的IP地址。</p>

<ul>
	<li>例如， <code>“123.45.67.89/20”</code> 是一个前缀长度为 <code>20</code> 的&nbsp;<strong>CIDR块</strong>。任何二进制表示形式匹配 <code>01111011 00101101 0100xxxx xxxxxxxx</code> 的IP地址，其中 <code>x</code> 可以是 <code>0</code> 或 <code>1</code> ，都在CIDR块覆盖的集合中。</li>
</ul>

<p>给你一个起始IP地址&nbsp;<code>ip</code>&nbsp;和我们需要覆盖的IP地址数量 <code>n</code> 。你的目标是使用 <strong>尽可能少的CIDR块</strong> 来&nbsp;<strong>覆盖范围&nbsp;<code>[ip, ip + n - 1]</code>&nbsp;内的所有IP地址</strong>&nbsp;。不应该覆盖范围之外的其他IP地址。</p>

<p>返回 <em>包含IP地址范围的 <strong>CIDR块</strong> 的 <strong>最短</strong> 列表。如果有多个答案，返回其中 <strong>任何</strong> 一个&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ip = "255.0.0.7", n = 10
<strong>输出：</strong>["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
<strong>解释：
</strong>需要覆盖的IP地址有:
- 255.0.0.7 -&gt; 11111111 00000000 00000000 00000111
- 255.0.0.8 -&gt; 11111111 00000000 00000000 00001000
- 255.0.0.9 -&gt; 11111111 00000000 00000000 00001001
- 255.0.0.10 -&gt; 11111111 00000000 00000000 00001010
- 255.0.0.11 -&gt; 11111111 00000000 00000000 00001011
- 255.0.0.12 -&gt; 11111111 00000000 00000000 00001100
- 255.0.0.13 -&gt; 11111111 00000000 00000000 00001101
- 255.0.0.14 -&gt; 11111111 00000000 00000000 00001110
- 255.0.0.15 -&gt; 11111111 00000000 00000000 00001111
- 255.0.0.16 -&gt; 11111111 00000000 00000000 00010000
CIDR区块“255.0.0.7/32”包含第一个地址。
CIDR区块255.0.0.8/29包含中间的8个地址(二进制格式为11111111 00000000 00000000 00001xxx)。
CIDR区块“255.0.0.16/32”包含最后一个地址。
请注意，虽然CIDR区块“255.0.0.0/28”覆盖了所有的地址，但它也包括范围之外的地址，所以我们不能使用它。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>ip = "117.145.102.62", n = 8
<b>输出：</b>["117.145.102.62/31","117.145.102.64/30","117.145.102.68/31"]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>7 &lt;= ip.length &lt;= 15</code></li>
	<li><code>ip</code>&nbsp;是一个有效的&nbsp;<strong>IPv4</strong>&nbsp;，形式为&nbsp;<code>"a.b.c.d"</code>&nbsp;，其中&nbsp;<code>a</code>,&nbsp;<code>b</code>,&nbsp;<code>c</code>,&nbsp;&nbsp;<code>d</code>&nbsp;是&nbsp;<code>[0, 255]</code>&nbsp;范围内的整数</li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li>每个隐含地址&nbsp;<code>ip + x</code>&nbsp;(&nbsp;<code>x &lt; n</code>) 都是有效的 IPv4 地址</li>
</ul>
































# 解题:
# 1.IP 到 CIDR
####  方法一：
- 若是手写计算答案则相对简单，写代码棘手的部分是所涉及的位操作。
- 我们思考一个问题：对于所需的 `n` 个 `ip` 地址，以及从起始 `ip` 地址开始且在范围内的 `ip` 地址，哪些 `CIDR` 块能够表示在范围内的大部分 `ip` 地址？用贪心的思想是可行的，我们可以一直重复这个过程，直到我们包括 `n` 个 `ip` 地址，所以我们应该尽可能的使用一个大的 `CIDR` 块。

**算法：**
- 我们需要将 `ip` 地址转换为长整数，通过一些基本的操作来实现这个功能--具体看代码实现方式。
- 然后，将 `255.0.0.24` 这样的 `ip` 地址转换为 `start`，它以二进制 `00011000` 结尾。如果 `n>=8`，那么我们应该使用整个块 `255.0.0.24/29`。否则，我们只取满足 $2^x>=n$ `x` 的最小值 。
- 在一般情况下啊，我们使用 `n` 和 `start&-start`（`start` 的最低位）的位长度来计算能表示 $2^{32 - \text{mask}}$ 个 `ip` 地址的掩码。然后，我们动态调整 `start` 和 `n`。
- 在 Java 和 C++ 中，我们应该小心使用长整数来表示转换后的 IP 地址，因为该数字可能超过 $2^{31}$。

```Python [ ]
class Solution(object):
    def ipToInt(self, ip):
        ans = 0
        for x in ip.split('.'):
            ans = 256 * ans + int(x)
        return ans

    def intToIP(self, x):
        return ".".join(str((x >> i) % 256)
                        for i in (24, 16, 8, 0))

    def ipToCIDR(self, ip, n):
        start = self.ipToInt(ip)
        ans = []
        while n:
            mask = max(33 - (start & -start).bit_length(),
                       33 - n.bit_length())
            ans.append(self.intToIP(start) + '/' + str(mask))
            start += 1 << (32 - mask)
            n -= 1 << (32 - mask)
        return ans
```

```Java [ ]
class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();
        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                                33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }
    private long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }
    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
            x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }
}
```

**复杂度分析**

* 时间复杂度：$O(N)$。其中 $N$ 表示的是 `nums` 的长度
* 空间复杂度：$O(1)$。
# 2.【Æ题解】【C++】【双百0ms通过】【黑魔法】【log2(n)】没有比这更简洁高效的代码了吧！
> Problem: [751. IP 到 CIDR](https://leetcode.cn/problems/ip-to-cidr/description/)

[TOC]

# 思路
> 将ip转化为10进制，以2的指数作为被除数进行求余。
> 余数为0的时候，即满足[curIP, curIP + mod)可以被curIP/(32 - log2(mod))全部覆盖。 

# 解题方法
> 描述你的解题方法

# 复杂度
- 时间复杂度: 
$log_2(n)$

- 空间复杂度: 
$O(n)$

# Code
```C++ []

class Solution {
public:
    vector<string> ipToCIDR(const string& ip, unsigned n) {
        vector<string> ans{};
        unsigned start = ip2uint(ip);
        for (unsigned i = 0, mod; i < n; i += mod) {
            mod = 1;
            unsigned cur = start + i;
            while (true) {
                if (i + mod > n || cur % mod != 0) {
                    mod >>= 1;
                    break;
                }
                mod <<= 1;
            }
            ans.emplace_back(uint2ip(cur) + "/" + to_string(32 - (unsigned)log2(mod)));
        }
        return ans;
    }
private:
    inline unsigned ip2uint(const string& ip) {
        char dot;
        istringstream iss(ip);
        for (auto i = num.begin(); i != num.end(); ++i) {
            if (i != num.begin()) iss >> dot; iss >> *i;
        }
        unsigned ret{0};
        for (unsigned i = 0; i < 4; ++i) ret |= (num[i] << (3 - i) * 8); 
        return ret;
    }
    inline string uint2ip(unsigned ip) {
        for (unsigned i = 0; i < 4; ++i) {
            num[3 - i] = ip & 0xff;
            ip >>= 8;
        }
        char dot{'.'};
        ostringstream oss;
        for (auto i = num.begin(); i != num.end(); ++i) {
            if (i != num.begin()) oss << dot; oss << *i;
        }
        return oss.str();
    }
private:
    vector<unsigned> num{0, 0, 0, 0};
};
```

# 3.简单 全面 易懂，效率击败所有 [java 2ms] [100%,100%]
可能因为是会员题目的缘故，这道题被浏览的量不多，题解不但少，质量更是堪忧。借着做题的机会为没有看懂其他题解的朋友提供一些帮助。

这道题目的难点有两个，一是理解题意，二是在此基础上实现算法。我们来仔细看一下


# 1. 题意解读
  
## 1.1 什么是CIDR

CIDR，Classless Inter-Domain Routing 即无类别域间路由，也叫无分类编址。如果计算机网络知识深厚的朋友可能看了标题就知道是什么意思，不懂的我们就只能跟着题目给出的例子使劲研究一下，此为难点之一。

简单来讲就是使用一个**前缀**（可以理解是一个起点ip）加上可变长的**子网掩码**（即mask）来表示ip段的东西。

举个例子，CIDR 255.0.0.8/29 包含如下8个地址

```

255.0.0.8 -> 11111111 00000000 00000000 00001000

255.0.0.9 -> 11111111 00000000 00000000 00001001

...

255.0.0.15 -> 11111111 00000000 00000000 00001111

```

为什么是8个地址？ 这个是因为mask有29位，没有被遮掩的地址是`2^(32-29) == 8`个。再比如若mask是32，那只包含1个地址了。


## 1.2 题目到底想让我干嘛？

题目要求的“返回用列表（最小可能的长度）表示的 CIDR块的范围” 这句不太读得懂的中文的实际意思就是，请你**把起始 IP 以及后面的 n 个地址用CIDR段表示，同时用尽量少的CIDR段**。

  
尽量少这个要求比较好理解，因为尽量多的话，你只需要返回一个个的`ip/32`的组合，可以实质上无视mask/CIDR。

  
所以题目是想让我们把 start 及其后面的 n 个ip给变成CIDR段的形式表示。注意里面不能包含 n 之外的ip。


# 2 解法

## 2.1 找CIDR就是找 start 和 mask

官方题解的思路是没有问题的，奈何描述稍微有些模糊。

结合一些子网掩码mask的工作原理知识（mask与运算，如果不明白的同学可以自行了解一下），题目中隐含的最关键的一个点在于，**CIDR段的起始ip的末尾必须是由 （32 - mask） 个 0 组成**，来看 `CIDR 255.0.0.8/29 `：

```

255.0.0.8 -> 11111111 00000000 00000000 00001000

```

  

若必须以`255.0.0.9`这样的地址作为**起点地址**

```

255.0.0.9 -> 11111111 00000000 00000000 00001001

```

你只能使用 `255.0.0.9/32` 来表示它一个，没有其他能以它**开头**的段。



## 2.2 贪心解法

明白了上面的规则，我们才能真正开始将一堆连续ip 转化为 CIDR段的贪心寻找：


1. 拿当前的起点ip做`start`

2. 想要用的段数少，那我们必须用尽量大的`mask`

- 通过start末尾0的数量来判断可以使用的mask长度上限

- mask长度上限同时还受剩余ip数 n 的影响，因为我们不能包含多余的ip

3. 用它们组成可以组成的最大 CIDR

4. 根据组成的CIDR段中的ip数量将n缩小，把start改为CIDR之后的第一个ip

5. 如果n还不为0，重复上述过程。

  

这样我们就可以较为直接的穷举出题目的解。相信有了上面的分析，下面的代码应该不难理解。

btw这题由于题意比较难理解比一般的简单题复杂，新手看不懂也不需要气馁。加油。

```java
    public List<String> ipToCIDR1(String ip, int n) {
        public List<String> ipToCIDR (String ip,int n){
        int start = toInt(ip);//将ip转化为整数
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int trailingZeros = Integer.numberOfTrailingZeros(start);
            System.out.println(trailingZeros);
            int mask = 0, bitsInCIDR = 1;
            //计算mask
            while (bitsInCIDR < n && mask < trailingZeros) {
                bitsInCIDR <<= 1;
                mask++;
            }
            if (bitsInCIDR > n) {
                bitsInCIDR >>= 1;
                mask--;
            }

            ans.add(toString(start, 32 - mask));
            n -= bitsInCIDR;
            start += (bitsInCIDR);
        }
        return ans;
    }

    private String toString(int number, int range) {
        final int WORD_SIZE = 8;
        StringBuilder buffer = new StringBuilder();
        for (int i = 3; i >= 0; --i) {
            buffer.append(((number >> (i * WORD_SIZE)) & 255));
            buffer.append(".");
        }
        buffer.setLength(buffer.length() - 1);
        buffer.append("/").append(range);
        return buffer.toString();
    }

    private int toInt(String ip) {
        String[] strs = ip.split("\\.");
        int sum = 0;
        for (String str : strs) {
            sum *= 256;
            sum += Integer.parseInt(str);
        }
        return sum;
    }
```
题目描述中n 小于1000，所以使用32位的int表示地址即可，即使计算过程中高位溢出，也不影响低位的运算（0，1判断）。


最后，我有一个记录自己题解的小仓库 

https://github.com/Fanlu91/FanluLeetcode

喜欢这道题解的朋友不妨给我点个星鼓励一下。感谢！
# 4.二进制计算+ Trie树遍历
### 解题思路
1. 把所有的IP地址编码成一棵Trie树， cnt[u], 表示ip地址中，以从根到u为前缀的数量。
2. bfs遍历Trie树，如果有一个结点cnt[u] == 以u为根的叶子结点数量，
那么就应该把这个结点作为答案。因为这个是最长前缀。
3. 最后需要对mask进行补0，直到32位为止。然后统计答案就行了。

### 代码

```python3
class Solution:
    def ipToCIDR(self, ip: str, n: int) -> List[str]:
        maxn = n * 50
        tr = [] * maxn
        for i in range(maxn):
            tr.append([0] * 2)
        cnt = [0] * maxn
        idx = 0
        
        nums = [int(x) for x in ip.split('.')]
        bg = 0
        for x in nums :
            bg = bg * 256 + x
        ed = bg + n
        for x in range(bg, ed) :
            # 插入trie树
            cur = 0
            for i in range(31, -1, -1):
                c = (x >> i) & 1
                if not tr[cur][c] :
                    idx += 1
                    tr[cur][c] = idx
                cur = tr[cur][c]
                cnt[cur] += 1
            
        # for i in range(idx + 1) :
        #     print(cnt[i])
        # 统计trie树中的结点中单词数目 等于 地址数目的
        vec = []
        from collections import deque
        que = deque()
        que.append((0, 0, 0))
        while que:
            u, mask, step = que.pop()
            if pow(2, 32 - step) == cnt[u] :
                # print(cnt[u])
                vec.append((mask, step))
                continue
            for c in range(2) :
                if tr[u][c] :
                    que.append((tr[u][c], (mask << 1) | c, step + 1))
        ans = []
        vec = [(mask << 32 - bit, bit) for mask, bit in vec]
        vec.sort(key=lambda tup:tup[0])
        for mask, bit in vec:
            nums = [0] * 4
            for i in range(4) :
                nums[3 - i] = mask % 256
                mask //= 256
            print(nums, bit)
            tmp = []
            for i, x in enumerate(nums):
                if i != 3 :
                    tmp.append(str(nums[i]) + '.')
                else :
                    tmp.append(str(nums[i]))
            tmp.append('/' + str(bit))
            tmp = "".join(tmp)
            ans.append(tmp)
        return ans
```
