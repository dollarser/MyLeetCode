# 1917Leetcodify 好友推荐
<p>表： <code>Listens</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| song_id     | int     |
| day         | date    |
+-------------+---------+
这个表没有主键，可能存在重复项。
表中的每一行表示用户 user_id 在 day 这一天收听的歌曲 song_id。
</pre>

<p>&nbsp;</p>

<p>表： <code>Friendship</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
(user1_id, user2_id) 是这个表的主键。
表中的每一行表示 user1_id 和 user2_id 是好友。
注意，user1_id &lt; user2_id。
</pre>

<p>&nbsp;</p>

<p>写出 SQL 语句，为 Leetcodify 用户推荐好友。我们将符合下列条件的用户 <code>x</code> 推荐给用户 <code>y</code> ：</p>

<ul>
	<li>用户 <code>x</code> 和 <code>y</code> 不是好友，且</li>
	<li>用户 <code>x</code> 和 <code>y</code> 在<strong>同一天</strong>收听了相同的三首或更多不同歌曲。</li>
</ul>

<p>注意，好友推荐是<strong>单向</strong>的，这意味着如果用户 <code>x</code> 和用户 <code>y</code> 需要互相推荐给对方，结果表需要将用户 <code>x</code> 推荐给用户 <code>y</code> 并将用户 <code>y</code> 推荐给用户 <code>x</code>。另外，结果表不得出现重复项（即，用户 <code>y</code> 不可多次推荐给用户 <code>x</code> ）。</p>

<p>按<strong>任意顺序</strong>返回结果表。</p>

<p>查询格式如下示例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入：</strong>
Listens 表：
+---------+---------+------------+
| user_id | song_id | day        |
+---------+---------+------------+
| 1       | 10      | 2021-03-15 |
| 1       | 11      | 2021-03-15 |
| 1       | 12      | 2021-03-15 |
| 2       | 10      | 2021-03-15 |
| 2       | 11      | 2021-03-15 |
| 2       | 12      | 2021-03-15 |
| 3       | 10      | 2021-03-15 |
| 3       | 11      | 2021-03-15 |
| 3       | 12      | 2021-03-15 |
| 4       | 10      | 2021-03-15 |
| 4       | 11      | 2021-03-15 |
| 4       | 13      | 2021-03-15 |
| 5       | 10      | 2021-03-16 |
| 5       | 11      | 2021-03-16 |
| 5       | 12      | 2021-03-16 |
+---------+---------+------------+
Friendship 表：
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
+----------+----------+
<strong>输出：</strong>
+---------+----------------+
| user_id | recommended_id |
+---------+----------------+
| 1       | 3              |
| 2       | 3              |
| 3       | 1              |
| 3       | 2              |
+---------+----------------+
<strong>解释</strong>
用户 1 和 2 在同一天收听了歌曲 10、11 和 12，但他们已经是好友了。
用户 1 和 3 在同一天收听了歌曲 10、11 和 12。由于他们不是好友，所以我们给他们互相推荐为好友。
用户 1 和 4 没有收听三首相同的歌曲。
用户 1 和 5 收听了歌曲 10、11 和 12，但不是在同一天收听的。

类似地，我们可以发现用户 2 和 3 在同一天收听了歌曲 10、11 和 12，且他们不是好友，所以我们给他们互相推荐为好友。
</pre>
































# 解题:
# 1.【1621/65.68%】11行-自联结+配对函数
![image.png](https://pic.leetcode-cn.com/1664510336-hIqbyI-image.png)
### 解题思路
1. 自联结Listens表，取出每天听到3首或以上歌曲的用户对；
2. 利用配对函数（本题是基于Cantor pairing function写的）过滤已有好友（取代union方法）；

### 代码

```mysql
select distinct user1 as user_id, user2 as recommended_id
from
    (select l1.day, l1.user_id as user1, l2.user_id as user2, l1.song_id
    from Listens l1 
    join Listens l2
    on l1.user_id != l2.user_id and l1.day = l2.day and l1.song_id = l2.song_id 
    group by l1.user_id, l2.user_id, l1.day, l1.song_id) a 
where 1/2*(user1+user2)*(user1+user2+1)+power(user1*user2,2) not in (select 1/2*(user1_id+user2_id)*(user1_id+user2_id+1)+power(user1_id*user2_id,2) from Friendship)
group by day, user1, user2 
having count(distinct song_id)>=3
order by 1,2
```
# 2.自连接和反连接
### 解题思路
1.先将listens表自连接，条件为user_id不相等day和song_id相等，可以得到在同一天听了同一首歌的不同一对客户，count() over()可以直接计算得到每一对客户在同一天听的歌数cnt，这里注意得去重
2.得到的上述结果筛选出歌数cnt大于等于3的客户对，但是要剔除已经是朋友的记录，所以和Friendship反连接，因为Firendship的user1_id和user2_id是严格1小于2的，但是我们结果表一对客户会有两条记录，user1_id可能在左也可能在有右，所以反连接两次剔除两种情况

方法不算简单，抛砖引玉~

### 代码

```mysql
# Write your MySQL query statement below
SELECT DISTINCT t.user1_id AS user_id,t.user2_id AS recommended_id
  FROM 
(SELECT a.user_id AS user1_id
       ,b.user_id AS user2_id
       ,a.song_id
       ,a.day
       ,COUNT(a.song_id) OVER (PARTITION BY a.day,a.user_id,b.user_id) AS cnt 
  FROM (SELECT DISTINCT * FROM Listens) a 
 INNER JOIN (SELECT DISTINCT * FROM Listens) b  
    ON a.user_id <> b.user_id
   AND a.song_id = b.song_id 
   AND a.day = b.day) t  
  LEFT JOIN Friendship t1 
    ON t.user1_id = t1.user1_id AND t.user2_id = t1.user2_id
  LEFT JOIN Friendship t2
    ON t.user1_id = t2.user2_id AND t.user2_id = t2.user1_id
 WHERE t.cnt >= 3 AND t1.user1_id IS NULL AND t2.user1_id IS NULL 
```
# 3.1917. sql的中规中矩的直白解法
![WX20210809-232550@2x.png](https://pic.leetcode-cn.com/1628522807-SQPpWZ-WX20210809-232550@2x.png)

### 代码

```mysql
# Write your MySQL query statement below

SELECT DISTINCT a.user_id, b.user_id AS recommended_id
FROM Listens a, Listens b
WHERE a.user_id <> b.user_id
	AND a.song_id = b.song_id
	AND a.day = b.day
	AND NOT EXISTS (
		SELECT 1
		FROM (
			SELECT a.user1_id, a.user2_id
			FROM Friendship a
			UNION ALL
			SELECT b.user2_id, b.user1_id
			FROM Friendship b
		) C
		WHERE a.user_id = C.User1_Id
			AND b.user_id = C.User2_Id
	)
GROUP BY a.user_id, b.user_id, a.day
HAVING COUNT(DISTINCT b.song_id) >= 3


```
# 4.MySQL题解：条件自连接+分组过滤
这个题的难点在于表如何连接，记录如何过滤。我们根据题目要求来一步一步拆解。

题目要求是存在一个双向推荐，如果有$x \to y$必须也有$y \to x$。由这里我们想到对朋友表进行一个union的操作，使得表Friend的记录存在一个双向关系，另外，我们也需要用到听歌记录Listen表进行自连接，才能够出现这样的双向关系。

其次，题目还要求必须是相同的日期，听了三首及以上相同的歌曲才能进行推荐。根据这一点，可以想到用Listen表进行自连接的时候，需要使得日期相同（在同一天听歌），用户不同（不同的用户才能互相推荐）。连接后再用相同歌曲id进行过滤（听了相同的歌曲），接着再按日期，用户1，用户2进行分组，过滤掉相同歌曲数量低于3首的记录。

最后的时候，需要去除掉存在朋友关系的记录，这里只需要用f表跟前面的结果做一个连接，过滤掉是朋友的记录再用distinct去重即可。

完整代码如下：
```
with f as (
    select user1_id u1, user2_id u2 from Friendship
    union
    select user2_id u1, user1_id u2 from Friendship
),
l as (
     select distinct * from Listens
),
t as (
    select l1.day,l1.user_id user_id,l2.user_id recommended_id
    from l l1 left join l l2 on l1.day = l2.day and l1.user_id != l2.user_id
    where l1.song_id = l2.song_id
    group by l1.day,l1.user_id, l2.user_id
    having sum(l1.song_id=l2.song_id) >= 3
)
select distinct user_id,recommended_id
from t left join f on t.user_id = f.u1 and t.recommended_id = f.u2
where u1 is null and u2 is null
```

