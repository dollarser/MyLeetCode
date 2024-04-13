# 158用 Read4 读取 N 个字符 II
<p>给你一个文件<meta charset="UTF-8" />&nbsp;<code>file</code>&nbsp;，并且该文件只能通过给定的&nbsp;<code>read4</code>&nbsp;方法来读取，请实现一个方法使其能够使&nbsp;<code>read</code> 读取 <code>n</code> 个字符。<strong>注意：你的</strong>&nbsp;<strong><code>read</code> 方法可能会被调用多次。</strong></p>

<p><strong>read4 的定义：</strong></p>

<p><code>read4</code> API 从文件中读取<strong> 4 个连续的字符</strong>，然后将这些字符写入缓冲区数组 <code>buf4</code> 。</p>

<p>返回值是读取的实际字符数。</p>

<p>请注意，<code>read4()</code> 有其自己的文件指针，类似于 C 中的 <code>FILE * fp</code> 。</p>

<pre>
    参数类型: char[] buf4
    返回类型: int

注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
</pre>

<p>下列是一些使用 <code>read4</code> 的例子：</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/07/01/157_example.png" style="height: 403px; width: 600px;" /></p>

<pre>
<code>File file("abcde"); // 文件名为 "abcde"， 初始文件指针 (fp) 指向 'a' 
char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf4); // read4 返回 4。现在 buf4 = "abcd"，fp 指向 'e'
read4(buf4); // read4 返回 1。现在 buf4 = "e"，fp 指向文件末尾
read4(buf4); // read4 返回 0。现在 buf4 = ""，fp 指向文件末尾</code></pre>

<p>&nbsp;</p>

<p><strong>read 方法：</strong></p>

<p>通过使用 <code>read4</code> 方法，实现&nbsp;<code>read</code> 方法。该方法可以从文件中读取 <code>n</code> 个字符并将其存储到缓存数组&nbsp;<code>buf</code> 中。您&nbsp;<strong>不能&nbsp;</strong>直接操作&nbsp;<code>file</code> 。</p>

<p>返回值为实际读取的字符。</p>

<p><strong>read&nbsp;的定义：</strong></p>

<pre>
    参数类型:  char[] buf, int n
    返回类型:  int

注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
</pre>

<p><strong>注意：</strong></p>

<ul>
	<li>你 <strong>不能</strong> 直接操作该文件，文件只能通过 <code>read4</code> 获取而 <strong>不能</strong> 通过 <code>read</code>。</li>
	<li><code>read</code>&nbsp; 函数可以被调用&nbsp;<strong>多次</strong>。</li>
	<li>请记得&nbsp;<strong>重置&nbsp;</strong>在 Solution 中声明的类变量（静态变量），因为类变量会&nbsp;<strong>在多个测试用例中保持不变</strong>，影响判题准确。请 <a href="https://support.leetcode-cn.com/hc/kb/section/1071534/" target="_blank">查阅</a> 这里。</li>
	<li>你可以假定目标缓存数组&nbsp;<code>buf</code> 保证有足够的空间存下 n 个字符。&nbsp;</li>
	<li>保证在一个给定测试用例中，<code>read</code> 函数使用的是同一个 <code>buf</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> file = "abc"， queries = [1,2,1]
<strong>输出：</strong>[1,2,0]
<strong>解释：</strong>测试用例表示以下场景:
File file("abc");
Solution sol;
sol.read (buf, 1); // 调用 read 方法后，buf 应该包含 “a”。我们从文件中总共读取了 1 个字符，所以返回 1。
sol.read (buf, 2); // 现在 buf 应该包含 "bc"。我们从文件中总共读取了 2 个字符，所以返回 2。
sol.read (buf, 1); // 我们已经到达文件的末尾，不能读取更多的字符。所以返回 0。
假设已经分配了 buf ，并保证有足够的空间存储文件中的所有字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>file = "abc"， queries = [4,1]
<strong>输出：</strong>[3,0]
<strong>解释：</strong>测试用例表示以下场景:
File file("abc");
Solution sol;
sol.read (buf, 4); // 调用 read 方法后，buf 应该包含 “abc”。我们从文件中总共读取了 3 个字符，所以返回 3。
sol.read (buf, 1); // 我们已经到达文件的末尾，不能读取更多的字符。所以返回 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code>&nbsp;由英语字母和数字组成</li>
	<li><code>1 &lt;= queries.length &lt;= 10</code></li>
	<li><code>1 &lt;= queries[i] &lt;= 500</code></li>
</ul>
































# 解题:
# 1.c++模拟
### 解题思路
题目比较难以理解：大概是这样
- 有一个方法叫read4，你传入一个指针（char数组），函数会读取文件并保存到这个数组中，并返回保存的字符数目（最多为4）
- 现在要求你用read4方法提供一个具有类似功能但是可以读取任意数目字符的函数read
由于会多次读取，使用一个双向队列存储上次用read4读取到的多余字符
### 代码

```cpp
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char *buf4);
 */

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        int sum=0;//表示返回结果的位数
        while(!dq.empty()&&sum<n)//优先使用剩余字符
        {
            buf[sum]=dq.front();
            dq.pop_front();
            ++sum;
        }
        while(sum<n)
        {
            char*temp=new char[4];//用于保存read4读取的结果
            int num=read4(temp);
            if(num==0)break;//表示读取到尽头了
            int i=0;
            for(;sum+i<n&&i<num;i++)
                buf[sum+i]=temp[i];//保存到结果
            sum+=i;
            for(;i<num;i++)
                dq.push_back(temp[i]);//保存多余字符
        }
        return sum;
    }
    deque<char>dq;
};
```
# 2.【微扰理论】核心在于缓存上次多读的char
### 解题思路
用一个全局变量，缓存每次read4读出的值。还需要t_idx和t_size用于标记缓冲区中读取的位置。
每次read时，如果n个元素没有读取完毕，就先从缓冲区中取数，不足则触发read4，调整t_idx和t_size大小；循环进行这一过程即可完成读取N个字符的任务。

### 代码

```cpp
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char *buf4);
 */

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char *tmp = new char[4];
    int t_idx = 0;
    int t_size = 0;

    int read(char *buf, int n) {
        int idx = 0;
        while (idx < n) {
            while(t_idx < t_size && idx < n) {
                buf[idx++] = tmp[t_idx++];
            }
            if (idx < n) {
                t_size = read4(tmp);
                t_idx = 0;
                if (t_size == 0) break;
            }
        }
        return idx;
    }
};
```
# 3.C++简洁代码
代码很简单，有问题可以讨论，看懂求个赞~
```C++ []
class Solution {
public:
    char *buf4 = new char[4];
    int buf4Index = 0;
    int buf4Size = 0;
    int read(char *buf, int n) {
        int index = 0;
        while(index<n){
            while(buf4Index<buf4Size && index<n){
                buf[index++] = buf4[buf4Index++];
            }
            if(index<n){
                buf4Size = read4(buf4);
                buf4Index = 0;
                if(buf4Size==0) break;
            }
        }
        return index;
    }
};
```


# 4.注释的部分是第一次写的过程 后面是优化后的结果
### 解题思路
![MZEN~O9AL)LYXK$T\])$Q\]68.png](https://pic.leetcode-cn.com/1661007674-BFriTz-MZEN~O9AL\)LYXK$T%5D\)$Q%5D68.png)


### 代码

```java
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int ptr = 0;
    int cap = 0;
    char [] temp = new char[4];
    public int read(char[] buf, int n) {
        // //本轮最终读取的数据
        // int res = 0;
        // //如果当前 temp中还有数据 则先读 temp中的
        // while(cap > 0 && res < n){
        //     cap--;
        //     buf[res++] = temp[ptr++];
        //     System.out.print(" : res="+res);
        // }
        // if(cap == 0){
        //     ptr =-1;
        // }
        // while(res < n){
        //     //如果还没有达到n个数据则并且文件还有 则继续读
        //     int size = read4(temp);
        //     //如果 没有文件了 则直接返回
        //     if(size == 0){
        //         return res;
        //     }
        //     //否则还有文件
        //     cap = size;
        //     ptr =0;
        //     for(int i =0;i < size&&res < n;i++){
        //         buf[res++] = temp[i];
        //         //同时更新ptr指针的位置 和cap大小
        //         ptr++;
        //         cap--;
        //     }
        // }
        // return res;


        int res = 0;
        while(res < n){
            for(;cap >0&&res < n;cap--){
                buf[res++] = temp[ptr++];
            }
            if(res == n){
                return res;
            }
            cap = read4(temp);
            ptr = 0;
            if(cap == 0){
                return res;
            }
        }
        return res;

        
        

    }
}
```
