# 631设计 Excel 求和公式
<p>你的任务是实现 Excel 的求和功能，具体的操作如下：</p>

<p><code>Excel(int H, char W):</code> 这是一个构造函数，输入表明了 Excel 的高度和宽度。H 是一个正整数，范围从 1 到 26，代表高度。W 是一个字符，范围从 'A' 到 'Z'，宽度等于从 'A' 到 W 的字母个数。Excel 表格是一个高度 * 宽度的二维整数数组，数组中元素初始化为 0。第一行下标从 1 开始，第一列下标从 'A' 开始。</p>

<p> </p>

<p><code>void Set(int row, char column, int val):</code> 设置 <code>C(row, column)</code> 中的值为 val。</p>

<p> </p>

<p><code>int Get(int row, char column):</code> 返回 <code>C(row, column)</code> 中的值。</p>

<p> </p>

<p><code>int Sum(int row, char column, List of Strings : numbers):</code> 这个函数会将计算的结果放入 <code>C(row, column)</code> 中，计算的结果等于在 <code>numbers</code> 中代表的所有元素之和，这个函数同时也会将这个结果返回。求和公式会一直计算更新结果直到这个公式被其他的值或者公式覆盖。</p>

<p><code>numbers</code> 是若干字符串的集合，每个字符串代表单个位置或一个区间。如果这个字符串表示单个位置，它的格式如下：<code>ColRow</code>，例如 "F7" 表示位置 (7, F) 。如果这个字符串表示一个区间，它的格式如下：<code>ColRow1:ColRow2</code>。区间就是左上角为 ColRow1 右下角为 ColRow2 的长方形。</p>

<p> </p>

<p><strong>注意: </strong>你可以认为不会出现循环求和的定义，比如说：<code>mat[1]['A'] == sum(1, "B")</code> 和 <code>mat[1]['B'] == sum(1, "A")</code>.</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>
["Excel", "set", "sum", "set", "get"]
[[3, "C"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
<b>输出:</b>
[null, null, 4, null, 6]

<b>解释:</b>
Excel excel = new Excel(3, "C");
 // 构造一个 3*3 的二维数组，初始化全是 0。
 //   A B C
 // 1 0 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.set(1, "A", 2);
 // 设置 C(1,"A") 为 2。
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.sum(3, "C", ["A1", "A1:B2"]); // return 4
 // 将 C(3,"C") 的值设为 C(1,"A") 单点以及左上角为 C(1,"A") 右下角为 C(2,"B") 的长方形两者之和。返回值 4。 
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 4
excel.set(2, "B", 2);
// 将 C(2,"B") 设为 2。 注意 C(3, "C") 的值也同时改变。
 //   A B C
 // 1 2 0 0
 // 2 0 2 0
 // 3 0 0 6
excel.get(3, "C"); // 返回 6</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= height <= 26</code></li>
	<li><code>'A' <= width <= 'Z'</code></li>
	<li><code>1 <= row <= height</code></li>
	<li><code>'A' <= column <= width</code></li>
	<li><code>-100 <= val <= 100</code></li>
	<li><code>1 <= numbers.length <= 5</code></li>
	<li><code>numbers[i]</code> 的格式为 <code>"ColRow"</code> 或 <code>"ColRow1:ColRow2"</code>.</li>
	<li><code>set</code>, <code>get</code>, and <code>sum</code> 操作数不超过 100 次</li>
</ul>

<p> </p>

<ol>
</ol>

<p> </p>
































# 解题:
# 1.设计 Excel 求和公式
#### 方法一：拓扑排序

假设一个求和公式为 $C1 = C2 + C3$，如果我们更改了 $C2$ 或 $C3$ 的值，那么 $C1$ 的值也会被改变。进一步而言，如果有另一个求和公式 $D1 = C1 + D2$，那么更改 $C2$ 的值也会导致 $D1$ 的值被改变。

因此我们在更改了 $C2$ 的值后，应当找到所有相关的格子，对它们的值进行更改。在公式 $C1 = C2 + C3$ 中，我们称 $C1$ 依赖于 $C2$ 和 $C3$；在公式 $D1 = C1 + D2$ 中，我们称 $D1$ 依赖于 $C1$ 和 $D2$，也间接依赖于 $C2$ 和 $C3$。我们在更改了 $C2$ 后，需要找到所有直接或间接依赖于 $C2$ 的那些格子。

我们把每个格子看成有向图中的一个节点，如果 `x` 依赖于 `y`，那么我们从 `y` 到 `x` 连一条有向边。由于题目保证不会有循环求和，因此这个有向图是一个有向无环图。对于某个节点 `xc`，当它的值被更改后，所有它可以走到的节点 `xp[1..k]` 都需要被更改。同时，这 `k` 个可以走到的节点需要指定修改顺序，例如上面的例子 $C1 = C2 + C3$ 与 $D1 = C1 + D2$，我们应该先修改前者 $C1$ 的值，再修改后者 $D1$ 的值，因此我们可以使用拓扑排序来解决这个问题。例如下图的一种拓扑排序为 `5 4 2 3 1 0`，那么如果我们修改了节点 `5` 的值，按照 `5 4 2 3 1 0` 的顺序依次修改节点的值是合理的。

![Topological_Sort_Graph](https://pic.leetcode-cn.com/Figures/631/631_Design_Excel.PNG){:width=400}
{:align="center"}

在本题中，我们使用深度优先搜索的方法，借助一个栈得到拓扑排序，方法如下：

- 从被修改的节点开始进行深度优先搜索；

- 对当前节点所有未访问过的节点，递归地进行搜索；

- 将当前节点入栈；

- 在搜索结束后，从栈顶到栈底的序列就是一种可能的拓扑排序序列。

<![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide1.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide2.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide3.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide4.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide5.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide6.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide7.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide8.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide9.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide10.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide11.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide12.PNG),![1000](https://pic.leetcode-cn.com/Figures/631/631_TopologicalSlide13.PNG)>

此外，我们还需要在图中额外记录每条边的权值，因为求和公式可能出现 $C1 = C2 + C3 + C2$ 的情况，此时 $C2$ 出现了两次，因此对于图中的每条边 `x -> y`，权值为 `z`，表示 `y` 的求和公式直接依赖于 `x`，并且依赖的次数为 `z`。我们可以用如下的方式实现题目中要求的所有操作：

* `Excel(int H, char W)`：建立一个 `H * W` 的表格；

* `set(int row, char column, int val)`：我们将 `(row, column)` 位置的数值改为 `val`，如果在此之前 `(row, column)` 的位置是一个求和公式，我们还需要覆盖这个求和公式，即从图中删除一系列表示依赖的有向边。随后我们从 `(row, column)` 开始，通过深度优先搜索得到拓扑排序，并依次重新计算这些格子的新值；

* `sum(int row, char column, List of Strings : numbers)`：该操作与 `set()` 类似，首先如果在此之前 `(row, column)` 的位置是一个求和公式，我们需要进行覆盖。随后我们在图中添加一系列表示这条求和公式中的依赖的有向边，再从 `(row, column)` 开始，得到拓扑排序并计算新值；

* `get(int row, char column)`：由于每次修改（无论是 `set()` 还是 `sum()`）操作后，我们都会将所有需要更新的值进行重新计算，因此 `get()` 操作只需要直接获取 `(row, column)` 位置的值即可。

```Java [sol1]
public class Excel {
    Formula[][] Formulas;
    class Formula {
        Formula(HashMap < String, Integer > c, int v) {
            val = v;
            cells = c;
        }
        HashMap < String, Integer > cells;
        int val;
    }
    Stack < int[] > stack = new Stack < > ();
    public Excel(int H, char W) {
        Formulas = new Formula[H][(W - 'A') + 1];
    }

    public int get(int r, char c) {
        if (Formulas[r - 1][c - 'A'] == null)
            return 0;
        return Formulas[r - 1][c - 'A'].val;
    }
    public void set(int r, char c, int v) {
        Formulas[r - 1][c - 'A'] = new Formula(new HashMap < String, Integer > (), v);
        topologicalSort(r - 1, c - 'A');
        execute_stack();
    }

    public int sum(int r, char c, String[] strs) {
        HashMap < String, Integer > cells = convert(strs);
        int summ = calculate_sum(r - 1, c - 'A', cells);
        set(r, c, summ);
        Formulas[r - 1][c - 'A'] = new Formula(cells, summ);
        return summ;
    }

    public void topologicalSort(int r, int c) {
        for (int i = 0; i < Formulas.length; i++)
            for (int j = 0; j < Formulas[0].length; j++)
                if (Formulas[i][j] != null && Formulas[i][j].cells.containsKey("" + (char)('A' + c) + (r + 1))) {
                    topologicalSort(i, j);
                }
        stack.push(new int[] {r,c});
    }

    public void execute_stack() {
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            if (Formulas[top[0]][top[1]].cells.size() > 0)
                calculate_sum(top[0], top[1], Formulas[top[0]][top[1]].cells);
        }
    }

    public HashMap < String, Integer > convert(String[] strs) {
        HashMap < String, Integer > res = new HashMap < > ();
        for (String st: strs) {
            if (st.indexOf(":") < 0)
                res.put(st, res.getOrDefault(st, 0) + 1);
            else {
                String[] cells = st.split(":");
                int si = Integer.parseInt(cells[0].substring(1)), ei = Integer.parseInt(cells[1].substring(1));
                char sj = cells[0].charAt(0), ej = cells[1].charAt(0);
                for (int i = si; i <= ei; i++) {
                    for (char j = sj; j <= ej; j++) {
                        res.put("" + j + i, res.getOrDefault("" + j + i, 0) + 1);
                    }
                }
            }
        }
        return res;
    }

    public int calculate_sum(int r, int c, HashMap < String, Integer > cells) {
        int sum = 0;
        for (String s: cells.keySet()) {
            int x = Integer.parseInt(s.substring(1)) - 1, y = s.charAt(0) - 'A';
            sum += (Formulas[x][y] != null ? Formulas[x][y].val : 0) * cells.get(s);
        }
        Formulas[r][c] = new Formula(cells, sum);
        return sum;
    }
}
```

**复杂度分析**

* 时间复杂度：

    - `set`：$O\big((r* c)^2\big)$，其中 $r$ 和 $c$ 是表格的高和宽。在修改某一个格子后，我们在最坏情况下需要重新计算所有格子的值，时间复杂度为 $O(r* c)$，并且对于每一个格子，它最多可能依赖于 $O(r* c)$ 个格子。

    - `sum` $O\big((r*c)^2\big)$，和 `set` 的时间复杂度相同；

    - `get`：$O(1)$，我们可以直接得到值；

* 空间复杂度：$O\big((r*c)^2\big)$，用来对于每一个格子存储依赖关系对应的有向边。
# 2.哈希表递归100%
### 解题思路
此处撰写解题思路

### 代码

```java
class Excel {

    public int [][] matrix;//装载数据
    public HashMap<Integer,String[]> map=new HashMap<>();//哈希表


    public Excel(int height, char width) {
        this.matrix=new int[height+1 ][width-'A'+1];//构造数组
    }
    
    public void set(int row, char column, int val) {
        this.matrix[row][column-'A']=val;//赋值
        int index=row*30+column-'A';//快速找到索引
        if(map.containsKey(index)){//赋值
            map.remove(index);//删除
        }

    }
    
    public int get(int row, char column) {
        int index=row*30+column-'A';//取得索引
        if(!map.containsKey(index)){//赋值
            return  this.matrix[row][column-'A'];//返回数据
        }
        int ret=0;//结果
        for(String s:map.get(index)){//遍历字符串
            if(s.length()==2){//A1
                int r=s.charAt(1)-'0';//1
                char c=s.charAt(0);//A
                ret+=get(r,c);//递归计算

            }else{
                // "A1:B2"
                int p=0;
                while(s.charAt(p)!=':'){
                    p++;//记录：的位置
                }
                String a=s.substring(0,p);//截取前段
                String b=s.substring(p+1);//截取后段

                char c1=a.charAt(0);//A,B
                char c2=b.charAt(0);

                int r1=Integer.parseInt(a.substring(1));//转换1，2
                int r2=Integer.parseInt(b.substring(1));

                for(int r=r1;r<=r2;r++){
                    for(char c=c1;c<=c2;c++){
                        ret+=get(r,c);//叠加
                    }
                }


            }

        }



        return ret;//返回

    }
    
    public int sum(int row, char column, String[] numbers) {
        int index=row*30+column-'A';//取得索引
        map.put(index,numbers);//插入数据
        return get(row,column);//返回数据
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(height, width);
 * obj.set(row,column,val);
 * int param_2 = obj.get(row,column);
 * int param_3 = obj.sum(row,column,numbers);
 */
```
# 3.调get才实时计算, 不需要拓扑排序, 击败100%, python3
### 解题思路
抽象出两类cell, 一类是只保存值的ValueCell, 一类是有公式的SumCell. 
初始化都是ValueCell
调用set方法时, 赋值row, column为ValueCell, 可能会覆盖SumCell
调用sum方法时, 赋值row, column为SumCell, 并返回SumCell的计算结果
调用get方法时, 如果是ValueCell, 直接返回值, 否则计算SumCell结果. ValueCell复杂度O(1), SumCell为

这里说明下SumCell的计算逻辑, 对应函数 _calc_sum:
SumCell有dependencies属性, 每个信息是是个元组(左上角坐标, 右下角坐标), 计算的时候需要遍历所有依赖的格子的值, 加和一起. 如果是SumCell, 则递归调用计算, 因为不会有环, 所以递归是会终止于全部都是ValueCell的情况. 

复杂度分析
set方法复杂度$O(1)$
sum方法复杂度$O(L*W*H)$, 其中L是numbers的长度, W, H是长宽, 最坏情况每个依赖都是整个表, 此时sumcell在最右下角.
get方法复杂度$O(1)$ 或者 $O(L*W*H)$

### 代码

```python3
Location = Tuple[int, int] # [x, y]

class Excel:
    class SumCell(object):
        def __init__(self, dependencies: List[Tuple[Location, Location]]):
            self.dependencies = dependencies

    class ValueCell(object):
        def __init__(self, value: int):
            self.value = value

    def __init__(self, height: int, width: str):
        rows, cols = self._get_grid_loc(height, width)
        self.grid = [[Excel.ValueCell(0) for _ in range(cols+1)] for _ in range(rows+1)]
            
    def set(self, row: int, column: str, val: int) -> None:
        x, y = self._get_grid_loc(row, column)
        self.grid[x][y] = Excel.ValueCell(val)

    def get(self, row: int, column: str) -> int:
        x, y = self._get_grid_loc(row, column)
        return self._get_by_loc(x, y)

    def sum(self, row: int, column: str, numbers: List[str]) -> int:
        x, y = self._get_grid_loc(row, column)
        dependencies = []
        def trans_colrow_to_loc(colrow: str) -> Location:
            col, row = colrow[0], colrow[1:]
            return self._get_grid_loc(int(row), col)
        for expression in numbers:
            if ':' not in expression:
                cur_x, cur_y = trans_colrow_to_loc(expression)
                dependencies.append(((cur_x, cur_y), (cur_x, cur_y)))
            else:
                start, end = expression.split(':')
                start_x, start_y = trans_colrow_to_loc(start)
                end_x, end_y = trans_colrow_to_loc(end)
                dependencies.append(((start_x, start_y), (end_x, end_y)))
        self.grid[x][y] = Excel.SumCell(dependencies)
        return self._get_by_loc(x, y)

    def _get_grid_loc(self, row: int, column: str) -> Location:
        return row - 1, ord(column) - ord('A')

    def _get_by_loc(self, x: int, y: int) -> int:
        if isinstance(self.grid[x][y], Excel.ValueCell):
            return self.grid[x][y].value
        else:
            return self._calc_sum(self.grid[x][y])

    def _calc_sum(self, cell: 'Excel.SumCell') -> int:
        """
        calc recursively, some cell may be another sum formula,
        and there's can't be a cycle
        """
        cur_sum = 0
        for (sx, sy), (ex, ey) in cell.dependencies:
            for x in range(sx, ex+1):
                for y in range(sy, ey+1):
                    cur_sum += self._get_by_loc(x, y)
        return cur_sum

# Your Excel object will be instantiated and called as such:
# obj = Excel(height, width)
# obj.set(row,column,val)
# param_2 = obj.get(row,column)
# param_3 = obj.sum(row,column,numbers)
```
# 4.数据范围小，直接map
### 解题思路
由于sum标记过的格子是实时更新的，所以在set之前要一直保留标记到map里

### 代码

```java
class Excel {
    private Map<String,String[]>map;
    private int[][]num;

    public Excel(int h, char w) {
        map=new HashMap<>();
        num=new int[h][w-'A'+1];
    }

    public void set(int r, char c, int v) {
        map.remove(r+" "+c);
        num[r-1][c-'A']=v;
    }

    public int get(int r, char c) {
        String key=r+" "+c;
        if(map.containsKey(key))return sum(r,c,map.get(key));
        return num[r-1][c-'A'];
    }

    public int sum(int r, char c, String[] strs) {
        int sum=0;
        for(String s:strs) {
            int i=s.indexOf(":");
            if(i==-1)sum+=get(Integer.parseInt(s.substring(1)),s.charAt(0));
            else{//数据范围较小，A~Z，1~26
                String a=s.substring(0,i),b=s.substring(i+1);
                int h1=Integer.parseInt(a.substring(1));char w1=a.charAt(0);
                int h2=Integer.parseInt(b.substring(1));char w2=b.charAt(0);
                for(int h=h1;h<=h2;h++)for(char w=w1;w<=w2;w++)sum+=get(h,w);
            }
        }
        map.put(r+" "+c,strs);
        return sum;
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */
```
