# 1804实现 Trie （前缀树） II
<p>前缀树（<strong><a href="https://en.wikipedia.org/wiki/Trie" target="_blank">trie</a></strong> ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。</p>

<p>实现前缀树 Trie 类：</p>

<ul>
	<li><code>Trie()</code> 初始化前缀树对象。</li>
	<li><code>void insert(String word)</code> 将字符串 <code>word</code> 插入前缀树中。</li>
	<li><code>int countWordsEqualTo(String word)</code> 返回前缀树中字符串 <code>word</code> 的实例个数。</li>
	<li><code>int countWordsStartingWith(String prefix)</code> 返回前缀树中以 <code>prefix</code> 为前缀的字符串个数。</li>
	<li><code>void erase(String word)</code> 从前缀树中移除字符串 <code>word</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><b>输入</b>
["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
[[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
<b>输出</b>
[null, null, null, 2, 2, null, 1, 1, null, 0]

<b>解释</b>
Trie trie = new Trie();
trie.insert("apple");               // 插入 "apple"。
trie.insert("apple");               // 插入另一个 "apple"。
trie.countWordsEqualTo("apple");    // 有两个 "apple" 实例，所以返回 2。
trie.countWordsStartingWith("app"); // "app" 是 "apple" 的前缀，所以返回 2。
trie.erase("apple");                // 移除一个 "apple"。
trie.countWordsEqualTo("apple");    // 现在只有一个 "apple" 实例，所以返回 1。
trie.countWordsStartingWith("app"); // 返回 1
trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
trie.countWordsStartingWith("app"); // 返回 0
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> 和 <code>prefix</code> 只包含小写英文字母。</li>
	<li><code>insert</code>、 <code>countWordsEqualTo</code>、 <code>countWordsStartingWith</code> 和 <code>erase</code> <strong>总共</strong>调用最多 <code>3 * 10<sup>4</sup></code> 次。</li>
	<li>保证每次调用 <code>erase</code> 时，字符串 <code>word</code> 总是存在于前缀树中。</li>
</ul>
































# 解题:
# 1.C++使用unordered_map和string的find库函数，轻松完成此题
```
class Trie {
private:
    unordered_map<string, int> trieMap;
public:
    Trie() {
        trieMap.clear();
    }
    
    void insert(string word) {
        ++trieMap[word];
    }
    
    int countWordsEqualTo(string word) {
        if (trieMap.count(word)) {
            return trieMap[word];
        }
        return 0;
    }
    
    int countWordsStartingWith(string prefix) {
        int count = 0;
        for (unordered_map<string, int>::iterator it = trieMap.begin(); it != trieMap.end(); ++it) {
            if (it->first.find(prefix) == 0) { // 使用string的find库函数寻找前缀，即找到的字符串要从0开始
                count += it->second;
            }
        }
        return count;
    }
    
    void erase(string word) {
        --trieMap[word];
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```

![image.png](https://pic.leetcode-cn.com/1645344436-CCTAQm-image.png)


# 2.c++/python3 法1:字典  法2:数组
思路和心得：

在类中多2个统计变量
插入word时在每个结点做好统计

## （一）字典实现
![字典.PNG](https://pic.leetcode-cn.com/1619061290-PooEmi-%E5%AD%97%E5%85%B8.PNG)



```python3 []
class Trie:
    def __init__(self):
        self.children = dict()
        self.as_prefix = 0      #插入word时，每一个w结点+1
        self.as_word = 0        #插入word结束时，统计一下

    #### 插入一个单词word
    def insert(self, word: str) -> None:
        rt = self
        for w in word:
            if w not in rt.children:    #可以用defaultdict(Trie())省心，但是这种写法思路更清晰
                rt.children[w] = Trie()
            rt.children[w].as_prefix += 1
            rt = rt.children[w]
        rt.as_word += 1

    #### 统计word的个数
    def countWordsEqualTo(self, word: str) -> int:
        rt = self
        for w in word:
            if w not in rt.children:
                return 0
            else:
                rt = rt.children[w]
        return rt.as_word

    #### 统计前缀prefix的个数
    def countWordsStartingWith(self, prefix: str) -> int:
        rt = self
        for p in prefix:
            if p not in rt.children:
                return 0
            else:
                rt = rt.children[p]
        return rt.as_prefix

    #### 只擦除一个word
    def erase(self, word: str) -> None:
        rt = self
        for w in word:
            if w not in rt.children:
                return
            else:
                rt.children[w].as_prefix -= 1
                rt = rt.children[w]
        rt.as_word -= 1


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.countWordsEqualTo(word)
# param_3 = obj.countWordsStartingWith(prefix)
# obj.erase(word)
```
```c++ []
class Trie 
{
public:
    unordered_map<char, Trie *> children;
    int as_prefix;
    int as_word;

    Trie() 
    {
        as_prefix = 0;
        as_word = 0;
    }
    //// 插入一个单词word
    void insert(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            if (rt->children.count(w) == 0)
                rt->children[w] = new Trie();
            rt->children[w]->as_prefix ++;
            rt = rt->children[w]; 
        }
        rt->as_word ++;
    }
    //// 统计单词word的个数
    int countWordsEqualTo(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            if (rt->children.count(w) == 0)
                return 0;
            rt = rt->children[w];
        }
        return rt->as_word;
    }
    //// 统计前缀prefix的个数
    int countWordsStartingWith(string prefix) 
    {
        Trie * rt = this;
        for (char p: prefix)
        {
            if (rt->children.count(p) == 0)
                return 0;
            rt = rt->children[p];
        }
        return rt->as_prefix;
    }
    //// 只删除一个word
    void erase(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            if (rt->children.count(w) == 0)
                return;
            rt->children[w]->as_prefix --;
            rt = rt->children[w];
        }
        rt->as_word --;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```

## （二）数组实现
![数组.PNG](https://pic.leetcode-cn.com/1619061301-vrCLZu-%E6%95%B0%E7%BB%84.PNG)

```python3 []
class Trie:
    def __init__(self):
        self.children = [None for _ in range(26)]
        self.as_prefix = 0      #插入word时，每一个w结点+1
        self.as_word = 0        #插入word结束时，统计一下

    #### 插入一个单词word
    def insert(self, word: str) -> None:
        rt = self
        for w in word:
            ID = ord(w) - ord('a')
            if rt.children[ID] == None:    #可以用defaultdict(Trie())省心，但是这种写法思路更清晰
                rt.children[ID] = Trie()
            rt.children[ID].as_prefix += 1
            rt = rt.children[ID]
        rt.as_word += 1

    #### 统计word的个数
    def countWordsEqualTo(self, word: str) -> int:
        rt = self
        for w in word:
            ID = ord(w) - ord('a')
            if rt.children[ID] == None:
                return 0
            else:
                rt = rt.children[ID]
        return rt.as_word

    #### 统计前缀prefix的个数
    def countWordsStartingWith(self, prefix: str) -> int:
        rt = self
        for p in prefix:
            ID = ord(p) - ord('a')
            if rt.children[ID] == None:
                return 0
            else:
                rt = rt.children[ID]
        return rt.as_prefix

    #### 只擦除一个word
    def erase(self, word: str) -> None:
        rt = self
        for w in word:
            ID = ord(w) - ord('a')
            if rt.children[ID] == None:
                return
            else:
                rt.children[ID].as_prefix -= 1
                rt = rt.children[ID]
        rt.as_word -= 1


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.countWordsEqualTo(word)
# param_3 = obj.countWordsStartingWith(prefix)
# obj.erase(word)
```
```c++ []
class Trie 
{
public:
    Trie * children [26];
    int as_prefix;
    int as_word;

    Trie() 
    {
        memset(children, NULL, sizeof(children));
        as_prefix = 0;
        as_word = 0;
    }
    //// 插入一个单词word
    void insert(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            int ID = w - 'a';
            if (rt->children[ID] == nullptr)
                rt->children[ID] = new Trie();
            rt->children[ID]->as_prefix ++;
            rt = rt->children[ID]; 
        }
        rt->as_word ++;
    }
    //// 统计单词word的个数
    int countWordsEqualTo(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            int ID = w - 'a';
            if (rt->children[ID] == nullptr)
                return 0;
            rt = rt->children[ID];
        }
        return rt->as_word;
    }
    //// 统计前缀prefix的个数
    int countWordsStartingWith(string prefix) 
    {
        Trie * rt = this;
        for (char p: prefix)
        {
            int ID = p - 'a';
            if (rt->children[ID] == nullptr)
                return 0;
            rt = rt->children[ID];
        }
        return rt->as_prefix;
    }
    //// 只删除一个word
    void erase(string word) 
    {
        Trie * rt = this;
        for (char w: word)
        {
            int ID = w - 'a';
            if (rt->children[ID] == nullptr)
                return;
            rt->children[ID]->as_prefix --;
            rt = rt->children[ID];
        }
        rt->as_word --;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```
# 3.1804. 实现 Trie (前缀树) II-Python-哈希表实现，近乎双百
### 解题思路
和[208. 实现 Trie (前缀树)](/problems/implement-trie-prefix-tree/)的代码几乎一样，该题的不同之处在于，对于每个字符需要维护两个值prefix和end

prefix指的是以当前遍历的字符为前缀的字符总数量，在添加过程的每一步都需要+1，在删除过程的每一步都需要-1

end指的是当前遍历的字符的总数量，只在添加过程结束后+1，在删除过程结束后-1

### 代码

```python3
class Trie:

    def __init__(self):
        self.node = defaultdict(dict)

    def insert(self, word: str) -> None:
        cur = self.node[word[0]]
        for n in word[1:]:
            cur['prefix'] = cur['prefix'] + 1 if 'prefix' in cur else 1                
            if n in cur:
                cur = cur[n]
            else:
                cur[n] = {}
                cur = cur[n]
        cur['end'] = cur['end'] + 1 if 'end' in cur else 1
        cur['prefix'] = cur['prefix'] + 1 if 'prefix' in cur else 1           

    def countWordsEqualTo(self, word: str) -> int:
        cur = self.node
        for n in word:
            if n in cur:
                cur = cur[n]
            else:
                return 0
        return cur['end'] if 'end' in cur else 0

    def countWordsStartingWith(self, prefix: str) -> int:
        cur = self.node
        for n in prefix:
            if n in cur:
                cur = cur[n]
            else:
                return 0
        return cur['prefix']

    def erase(self, word: str) -> None:
        cur = self.node
        for n in word:
            cur = cur[n]
            if 'prefix' in cur:
                cur['prefix'] -= 1
        cur['end'] -= 1
```

![image.png](https://pic.leetcode-cn.com/1653385624-DJBroX-image.png)

# 4.Trie前缀树 C++
### 解题思路
在一般前缀树的基础上维护两个变量，一个变量nums记录词数量，一个变量prefixnums记录词前缀数量，可解此题

### 代码

```cpp
class Trie {
    struct TrieNode {
        int nums = 0;
        int prefixnums = 0;
        TrieNode* next[26] = {nullptr};
        ~TrieNode() {
            for(int i=0; i<26; ++i) {
                if(next[i]) delete next[i];
            }
        }
    };
public:
    TrieNode* root;
    Trie() {
        root = new TrieNode();
    }
    
    void insert(string word) {
        int n = word.size();
        TrieNode* p = root;
        for(int i=0; i<n; ++i) {
            int t = word[i]-'a';
            if(p->next[t]==nullptr) {
                p->next[t] = new TrieNode();
            }
            p = p->next[t];
            ++(p->prefixnums);
        }
        ++(p->nums);
    }
    
    int countWordsEqualTo(string word) {
        int n = word.size();
        TrieNode* p = root;
        for(int i=0; i<n&&p; ++i) {
            int t = word[i]-'a';
            p = p->next[t];
        }
        if(p==nullptr) return 0;
        return p->nums;
    }
    
    int countWordsStartingWith(string prefix) {
        int n = prefix.size();
        TrieNode* p = root;
        int f = INT_MIN;
        for(int i=0; i<n&&p; ++i) {
            p = p->next[prefix[i]-'a'];
        }
        if(p==nullptr) return 0;
        return p->prefixnums;
    }
    
    void erase(string word) {
        int n = word.size();
        TrieNode* p = root;
        for(int i=0; i<n&&p; ++i) {
            p = p->next[word[i]-'a'];
            --(p->prefixnums);
        }
        --(p->nums);
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```
