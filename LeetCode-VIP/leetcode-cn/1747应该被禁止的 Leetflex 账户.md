# 1747应该被禁止的 Leetflex 账户
<p>表: <code>LogInfo</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| account_id  | int      |
| ip_address  | int      |
| login       | datetime |
| logout      | datetime |
+-------------+----------+
该表是没有主键的，它可能包含重复项。
该表包含有关Leetflex帐户的登录和注销日期的信息。 它还包含了该账户用于登录和注销的网络地址的信息。
题目确保每一个注销时间都在登录时间之后。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询语句，查找那些应该被禁止的Leetflex帐户编号 <code>account_id</code> 。 如果某个帐户在某一时刻从两个不同的网络地址登录了，则这个帐户应该被禁止。</p>

<p>可以以 <strong>任何顺序 </strong>返回结果。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
LogInfo table:
+------------+------------+---------------------+---------------------+
| account_id | ip_address | login               | logout              |
+------------+------------+---------------------+---------------------+
| 1          | 1          | 2021-02-01 09:00:00 | 2021-02-01 09:30:00 |
| 1          | 2          | 2021-02-01 08:00:00 | 2021-02-01 11:30:00 |
| 2          | 6          | 2021-02-01 20:30:00 | 2021-02-01 22:00:00 |
| 2          | 7          | 2021-02-02 20:30:00 | 2021-02-02 22:00:00 |
| 3          | 9          | 2021-02-01 16:00:00 | 2021-02-01 16:59:59 |
| 3          | 13         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
| 4          | 10         | 2021-02-01 16:00:00 | 2021-02-01 17:00:00 |
| 4          | 11         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
+------------+------------+---------------------+---------------------+
<strong>输出：
</strong>+------------+
| account_id |
+------------+
| 1          |
| 4          |
+------------+
<strong>解释：</strong>
Account ID 1 --&gt; 该账户从 "2021-02-01 09:00:00" 到 "2021-02-01 09:30:00" 在两个不同的网络地址(1 and 2)上激活了。它应该被禁止.
Account ID 2 --&gt; 该账户在两个不同的网络地址 (6, 7) 激活了，但在不同的时间上.
Account ID 3 --&gt; 该账户在两个不同的网络地址 (9, 13) 激活了，虽然是同一天，但时间上没有交集.
Account ID 4 --&gt; 该账户从 "2021-02-01 17:00:00" 到 "2021-02-01 17:00:00" 在两个不同的网络地址 (10 and 11)上激活了。它应该被禁止.</pre>
































# 解题:
# 1.【鲸析】1747. 应该被禁止的 Leetflex 账户（自连接）
![1747.png](https://pic.leetcode.cn/1669881155-MGtlcS-1747.png)

公众号：鲸析
小红书：鲸鲸说数据
SQL刷题笔记：https://docs.qq.com/sheet/DRUtTaE5wUHVLcVNN?tab=BB08J2

#### 思路

首先，本题目不好找突破口，乍一看看不出来用的什么方法。

那么，根据题意，我们要寻找同一个账户id里面的不同ip地址下的登录和登出时间是否有交集。

也就等于我们要用相同列，但是不同行的login或者logout来去比较大小。

这个是典型的【自连接】的题目！

- 613. 直线上的最近距离
- 612. 平面上的最近距离

这两道题的思路是一样的！

视频解析：https://www.bilibili.com/video/BV1gR4y1y7sm?t=3.2

#### 答案

```sql
select distinct a.account_id
from LogInfo a,LogInfo b
where a.account_id = b.account_id
and a.ip_address != b.ip_address
and a.logout between b.login and b.logout 
```

# 2.简单逻辑 自联结
该方法的逻辑十分简单直接
```
SELECT DISTINCT a.account_id AS account_id  -- 封他！
FROM LogInfo a, LogInfo b
WHERE a.account_id = b.account_id  -- 某个用户
    AND a.ip_address != b.ip_address -- 异地登陆
    AND a.logout <= b.logout  -- 其中一个还没下线
    AND b.login <= a.logout  -- 另一个就登上来了
;
```
# 3.INNER JOIN + 逻辑判断
### 解题思路
看似麻烦 但只要通过自联结 + 判断login or logout between 另外一个区间即可

### 代码

```mysql
# Write your MySQL query statement below
SELECT DISTINCT a.account_id
FROM LogInfo a
INNER JOIN LogInfo b
ON a.account_id = b.account_id AND a.ip_address <> b.ip_address AND ((a.login between b.login and b.logout) OR (a.logout between b.login and b.logout))
```
# 4.SQL-自连接
```sql
select distinct l1.account_id from loginfo l1
join loginfo l2
on 
    l1.account_id = l2.account_id and 
    l1.ip_address != l2.ip_address and 
    ( l1.login >= l2.login and l1.login <= l2.logout )
```

