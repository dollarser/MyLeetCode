# 157用 Read4 读取 N 个字符
<p>给你一个文件，并且该文件只能通过给定的&nbsp;<code>read4</code>&nbsp;方法来读取，请实现一个方法使其能够读取 n 个字符。</p>

<p><strong>read4 方法：</strong></p>

<p>API&nbsp;<code>read4</code>&nbsp;可以从文件中读取 4 个连续的字符，并且将它们写入缓存数组&nbsp;<code>buf</code>&nbsp;中。</p>

<p>返回值为实际读取的字符个数。</p>

<p>注意&nbsp;<code>read4()</code> 自身拥有文件指针，很类似于 C 语言中的 <code>FILE *fp</code> 。</p>

<p><strong>read4 的定义：</strong></p>

<pre>参数类型: char[] buf4
返回类型: int

注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
</pre>

<p>下列是一些使用 <code>read4</code> 的例子：</p>

<p><img style="width: 600px;"></p>

<pre><code>File file(&quot;abcde&quot;); // 文件名为 &quot;abcde&quot;， 初始文件指针 (fp) 指向 &#39;a&#39; 
char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf4); // read4 返回 4。现在 buf4 = &quot;abcd&quot;，fp 指向 &#39;e&#39;
read4(buf4); // read4 返回 1。现在 buf4 = &quot;e&quot;，fp 指向文件末尾
read4(buf4); // read4 返回 0。现在 buf = &quot;&quot;，fp 指向文件末尾</code></pre>

<p><strong>read 方法：</strong></p>

<p>通过使用 <code>read4</code> 方法，实现&nbsp;<code>read</code> 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组&nbsp;<code>buf</code> 中。您&nbsp;<strong>不能&nbsp;</strong>直接操作文件。</p>

<p>返回值为实际读取的字符。</p>

<p><strong>read&nbsp;的定义：</strong></p>

<pre>参数类型:   char[] buf, int n
返回类型:   int

注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
</pre>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入： </strong>file = &quot;abc&quot;, n = 4
<strong>输出： </strong>3
<strong>解释：</strong> 当执行你的 read 方法后，buf 需要包含 &quot;abc&quot;。 文件一共 3 个字符，因此返回 3。 注意 &quot;abc&quot; 是文件的内容，不是 buf 的内容，buf 是你需要写入结果的目标缓存区。 </pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入： </strong>file = &quot;abcde&quot;, n = 5
<strong>输出： </strong>5
<strong>解释： </strong>当执行你的 read 方法后，buf 需要包含 &quot;abcde&quot;。文件共 5 个字符，因此返回 5。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入： </strong>file = &quot;abcdABCD1234&quot;, n = 12
<strong>输出： </strong>12
<strong>解释： </strong>当执行你的 read 方法后，buf 需要包含 &quot;abcdABCD1234&quot;。文件一共 12 个字符，因此返回 12。
</pre>

<p><strong>示例 4:</strong></p>

<pre><strong>输入： </strong>file = &quot;leetcode&quot;, n = 5
<strong>输出： </strong>5
<strong>解释：</strong> 当执行你的 read 方法后，buf 需要包含 &quot;leetc&quot;。文件中一共 5 个字符，因此返回 5。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>你 <strong>不能</strong> 直接操作该文件，文件只能通过 <code>read4</code> 获取而 <strong>不能</strong> 通过 <code>read</code>。</li>
	<li><code>read</code>&nbsp; 函数只在每个测试用例调用一次。</li>
	<li>你可以假定目标缓存数组&nbsp;<code>buf</code> 保证有足够的空间存下 n 个字符。&nbsp;</li>
</ul>
































# 解题:
# 1.看题解，没一个写的特别清晰的，java 100%,详细到死讲解，附while循环讲解
```
read(char[] buf, int n)
```
这道题的需要实现的是，从文件里面读取n个字符，放到buf里面，文件具体多少个字符，我们是不知道的，我们只能通过read4方法来不断的读取文件，直到read4方法返回为0的时候，才知道文件读取完了。但是这道题不希望我们将文件全部读取完，而是读取n个字符就可以了，这个n可能大于等于文件里面字符的长度，也可能小于文件里面字符的长度。所以就需要分几种情况来考虑：
1. 当n小于文件字符长度的时候，我们不需要等到read4返回0，才停止读取，而是通过read4读取的字符累加长度=n的时候，就停止读取，因为read4每次固定读取4个，所以累加的长度不会刚好等于n，比如当n=5，表示我们需要读取5个字符，文件字符长度为12，第一次调用read4的时候，read4返回4，这个时候n-4>=0，所以需要将这四个字符全部拷贝到buf中，并且n更新为n-4=1，这个时候我们还只需要读取1个字符；第二次调用read4的时候，read4还是返回4，但是这时n=1,1-4<0，所以我们不需要全部读取4个字符，而是只拷贝n=1个字符就可以了。这时n = n-1 = 0,我们可以停止读取了。
2. 当n大于等于文件字符长度的时候，我们等到read4返回0，就停止读取就可以了，对虽然这个时候n仍然大于0，当时我们已经没有可以读取的字符了。
综上，我们不断对文件进行读取，停止读取的条件是当read4返回0，或者当n==0的时候我们就中断读取，代码如下：
```
/**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int size = 0;
        char[] buf4 = new char[4];
        int readSize = 0;
        while (n > 0) {
            readSize = read4(buf4);
            readSize = n >= readSize ? readSize : n; 
            n -= readSize;
            System.arraycopy(buf4, 0, buf, size, readSize);
            size += readSize;
            if (readSize == 0) {
                break;
            }
        }
        return size;
    }
```
这里延伸一下while循环结构的写法：
这题我们有三种方式写while循环：
1. 在while的block中进行n和readSize的条件判断：
while (n > 0 && readSize != 0)，这样我们不需要在while的body中进行break,但是就需要修改readSize的初始值，但是我还是觉得readSize初始值定义为0是最准确的，并且在n>0的情况下，至少让read4运行一次才是合理的逻辑，所以这里我觉得将readSize放在while的block里面判断并不合适。
1. while(true)，然后在body中判断当n == 0 || readSize == 0再进行break，这种方法的问题在于，当n初始值为0的时候，也进入了循环体，进行了无效了计算，所以并不合适。
1. while(n > 0),然后在body中判断当readSize == 0再进行break，这样是最符合题目逻辑的，因为如果n的初始值小于等于0，则可以避免走入循环做无用功，当n > 0时，至少进行一次read4读取，如果read4返回0，再break。
所以我们在当不知道具体循环几次的时候，需要使用while循环，但是while循环的灵活性注定了它会有多种写法，具体采用哪一种写法，取决于我们希望哪种条件下不进入循环体，哪些至少要进行一次循环再进行条件判断，这些考虑清楚之后再进行编码。

# 2.Java 普通思路（100%，97.73%）
### 解题思路
只需要注意是否超过了真实的边界 `n` 就好了。

### 代码

```java
public class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        int idx = 0;
        char[] buf4 = new char[4];
        int size = read4(buf4);
        while (size > 0 && idx < n) {
            for (int i = 0; i < size && idx < n; ++i) buf[idx++] = buf4[i];
            size = read4(buf4);
        }
        return idx;
    }
}
```
# 3.c++/python3 存进buf，返回实际存了几个字母
思路和心得：

1.题意太难懂

其实就是让：存进buf，返回实际存了几个字母

python3比较麻烦一些

要给buf找个buf过渡一下


```python3 []
"""
The read4 API is already defined for you.

    @param buf4, a list of characters
    @return an integer
    def read4(buf4):

# Below is an example of how the read4 API can be called.
file = File("abcdefghijk") # File is "abcdefghijk", initially file pointer (fp) points to 'a'
buf4 = [' '] * 4 # Create buffer with enough space to store characters
read4(buf4) # read4 returns 4. Now buf = ['a','b','c','d'], fp points to 'e'
read4(buf4) # read4 returns 4. Now buf = ['e','f','g','h'], fp points to 'i'
read4(buf4) # read4 returns 3. Now buf = ['i','j','k',...], fp points to end of file
"""

class Solution:
    def read(self, buf, n):
        """
        :type buf: Destination buffer (List[str])
        :type n: Number of characters to read (int)
        :rtype: The number of actual characters read (int)
        """
        bi = 0
        for _ in range(0, n, 4):
            tmp = [None] * 4        #必须先开辟出4个空间
            cur_len = read4(tmp)    #先读入到tmp
            for j in range(cur_len):
                buf[bi] = tmp[j]    #从tmp复制到buf
                bi += 1
        return min(bi, n)
```

```c++ []
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char *buf4);
 */

class Solution 
{
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) 
    {
        int res = 0;
        for (int i = 0; i < n; i += 4)
        {
            res += read4(buf);      //指针后移
            buf += 4;
        }

        return min(res, n);
    }
};
```

# 4.题意：返回实际读取字符个数，不超过n个字符
```
//返回实际读取字符个数，不超过n个字符
int _read(char* buf, int n) {
    int i, len;                         //累计字符个数不超过n
    for (i = 0; i < n; i += len){
        len = read4(buf + i);           //每次读取字符个数
        if (len == 0) break;            //没有字符可读取时，结束循环
    }
    return i < n ? i : n;               //返回实际读取字符个数，不超过n个字符
}
```

