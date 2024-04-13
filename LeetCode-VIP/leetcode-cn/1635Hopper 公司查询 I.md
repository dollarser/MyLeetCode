# 1635Hopper 公司查询 I
<p>表: <code>Drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| driver_id   | int     |
| join_date   | date    |
+-------------+---------+
driver_id是该表的主键。
该表的每一行均包含驾驶员的ID以及他们加入Hopper公司的日期。
</pre>

<p>&nbsp;</p>

<p>表: <code>Rides</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| ride_id      | int     |
| user_id      | int     |
| requested_at | date    |
+--------------+---------+
ride_id是该表的主键。
该表的每一行均包含行程ID(ride_id)，用户ID(user_id)以及该行程的日期(requested_at)。
该表中可能有一些不被接受的乘车请求。
</pre>

<p>&nbsp;</p>

<p>表: <code>AcceptedRides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ride_id       | int     |
| driver_id     | int     |
| ride_distance | int     |
| ride_duration | int     |
+---------------+---------+
ride_id是该表的主键。
该表的每一行都包含已接受的行程信息。
表中的行程信息都在“<code>Rides</code>”表中存在。
</pre>

<p>&nbsp;</p>

<p>编写SQL查询以报告2020年每个月的以下统计信息：</p>

<ul>
	<li>截至某月底，当前在Hopper公司工作的驾驶员数量（<code>active_drivers</code>）。</li>
	<li>该月接受的乘车次数（<code>accepted_rides</code>）。</li>
</ul>

<p>返回按<code>month</code> 升序排列的结果表，其中<code>month</code> 是月份的数字（一月是<code>1</code>，二月是<code>2</code>，依此类推）。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
表 Drivers:
+-----------+------------+
| driver_id | join_date  |
+-----------+------------+
| 10        | 2019-12-10 |
| 8         | 2020-1-13  |
| 5         | 2020-2-16  |
| 7         | 2020-3-8   |
| 4         | 2020-5-17  |
| 1         | 2020-10-24 |
| 6         | 2021-1-5   |
+-----------+------------+
表 Rides:
+---------+---------+--------------+
| ride_id | user_id | requested_at |
+---------+---------+--------------+
| 6       | 75      | 2019-12-9    |
| 1       | 54      | 2020-2-9     |
| 10      | 63      | 2020-3-4     |
| 19      | 39      | 2020-4-6     |
| 3       | 41      | 2020-6-3     |
| 13      | 52      | 2020-6-22    |
| 7       | 69      | 2020-7-16    |
| 17      | 70      | 2020-8-25    |
| 20      | 81      | 2020-11-2    |
| 5       | 57      | 2020-11-9    |
| 2       | 42      | 2020-12-9    |
| 11      | 68      | 2021-1-11    |
| 15      | 32      | 2021-1-17    |
| 12      | 11      | 2021-1-19    |
| 14      | 18      | 2021-1-27    |
+---------+---------+--------------+
表 AcceptedRides:
+---------+-----------+---------------+---------------+
| ride_id | driver_id | ride_distance | ride_duration |
+---------+-----------+---------------+---------------+
| 10      | 10        | 63            | 38            |
| 13      | 10        | 73            | 96            |
| 7       | 8         | 100           | 28            |
| 17      | 7         | 119           | 68            |
| 20      | 1         | 121           | 92            |
| 5       | 7         | 42            | 101           |
| 2       | 4         | 6             | 38            |
| 11      | 8         | 37            | 43            |
| 15      | 8         | 108           | 82            |
| 12      | 8         | 38            | 34            |
| 14      | 1         | 90            | 74            |
+---------+-----------+---------------+---------------+
<strong>输出：</strong>
+-------+----------------+----------------+
| month | active_drivers | accepted_rides |
+-------+----------------+----------------+
| 1     | 2              | 0              |
| 2     | 3              | 0              |
| 3     | 4              | 1              |
| 4     | 4              | 0              |
| 5     | 5              | 0              |
| 6     | 5              | 1              |
| 7     | 5              | 1              |
| 8     | 5              | 1              |
| 9     | 5              | 0              |
| 10    | 6              | 0              |
| 11    | 6              | 2              |
| 12    | 6              | 1              |
+-------+----------------+----------------+
<strong>解释：</strong>
截至1月底-&gt;两个活跃的驾驶员（10,8），没有被接受的行程。
截至2月底-&gt;三个活跃的驾驶员（10,8,5），没有被接受的行程。
截至3月底-&gt;四个活跃的驾驶员（10,8,5,7），一个被接受的行程（10）。
截至4月底-&gt;四个活跃的驾驶员（10,8,5,7），没有被接受的行程。
截至5月底-&gt;五个活跃的驾驶员（10,8,5,7,4），没有被接受的行程。
截至6月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一个被接受的行程（13）。
截至7月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一个被接受的行程（7）。
截至8月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一位接受的行程（17）。
截至9月底-&gt;五个活跃的驾驶员（10,8,5,7,4），没有被接受的行程。
截至10月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），没有被接受的行程。
截至11月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），两个被接受的行程（20,5）。
截至12月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），一个被接受的行程（2）。</pre>
































# 解题:
# 1.【87.58%】ifnull+max() over解法
![image.png](https://pic.leetcode-cn.com/1664354778-eINYdC-image.png)

### 解题思路
这道题的考点我觉得就两个：
1. 递归CTE；
2. 对于不连续月份取继承上月active_drivers

### 代码

```mysql
# Write your MySQL query statement below

with recursive t(n) as (
    select 1 n 
    union all
    select n+1 from t where n < 12
)
select t.n as month, ifnull(max(active_drivers), ifnull(max(max(active_drivers)) over(order by t.n),0)) as active_drivers, count(distinct AcceptedRides.ride_id) as accepted_rides
from
    t
left join
    (select *, count(driver_id) over(order by join_date) as active_drivers from Drivers) a 
on t.n = month(a.join_date) and year(join_date) = 2020
left join Rides on t.n = month(requested_at) and year(requested_at) = 2020
left join AcceptedRides on Rides.ride_id = AcceptedRides.ride_id
group by t.n


```
# 2.【鲸析】1635. Hopper 公司查询 I（考点：cte、递归、时间转换、窗口）
```
【鲸析】SQL面经大全
https://docs.qq.com/doc/DRU5Pa0pManNvSXJH
```

##### 1. 思路

表很多比较复杂，首先想到cte拆分简化。分为两部分：
1. 计算2020年每个月累积的`active_drivers`（稍微难点）
2. 计算2020年每个月接受的乘车次数（简单）

那么先来看`active_drivers`：

`active_drivers`可以从Drivers表计算，难点在于

1. 累积的计算逻辑
![image.png](https://pic.leetcode-cn.com/1656504876-YvVBIM-image.png)
2. 生成连续数字的方法（MySQL 8.0 With递归方法）
```sql
with recursive table_name(n) AS  -- n代表该列的列名
(
select 1
union ALL
select n + 1
from c
where n < 100                    -- 生成1-100的连续数字
)
select * from table_name;   
```
3. 多表连接以及null的处理

##### 2. 答案

```sql

-- 生成1-12的连续月份，叫做12_month
with recursive 12_month(m) AS
(
select 1
union ALL
select m + 1
from 12_month
where m < 12
),

-- 通过Drivers表计算每个月对应的driver个数
-- 这里没计算累积
active_drivers_table as
(
    select 
        month(join_date) as m,
        count(1) as cnt
    from
    (
    select
        driver_id,
        case 
            when year(join_date)<2020 then '2020-1-1'
        else join_date end as join_date 
    from Drivers) tmp
    where join_date like '2020%'
    group by 1
),

-- 通过Rides和AcceptedRides表计算每个月接受的乘车次数
-- 这里不涉及累积，所以不需要考虑2020以外的任何情况！
accepted_rides_table as 
(
    select 
        month(requested_at) as m, 
        ifnull(count(*),0) as accepted_rides
    from AcceptedRides a 
    left join Rides b 
    using(ride_id)
    where requested_at like '2020%'
    group by 1
)

-- 最后，把三个表left join（注意null）
-- active_drivers使用窗口函数累加
-- ifnull填充
select
    m as month,
    sum(ifnull(cnt,0)) over(order by m) as active_drivers,
    ifnull(accepted_rides,0) as accepted_rides 
from 12_month t1
left join active_drivers_table t2 
using(m)
left join accepted_rides_table t3
using(m)
```

- 小红书：鲸鲸说数据
- 公众号：鲸析

# 3.思路：先求累计注册司机人数，再求每月接单数量，然后两者连接。
**代码：**
![图片.png](https://pic.leetcode-cn.com/1604364838-OkXmdu-%E5%9B%BE%E7%89%87.png)
**步骤：**
1、求累计注册司机人数
1.1、生成月份表A。
1.2、表A和Drivers表做左连接，以注册年月份小于等于2020年每个月为连接条件，以月份为分组条件，COUNT(driver_id)则为所求。连接条件这里，写法用的是“202000 + month >= DATE_FORMAT(join_date,'%Y%m')”。注意这里要加上IFNULL()来包含无注册人员的例外情况。
2、求每月接单数量
SELECT MONTH(requested_at) month,COUNT(*) accepted_rides
FROM Rides
JOIN AcceptedRides
USING(ride_id)
WHERE YEAR(requested_at) = 2020
GROUP BY MONTH(requested_at)
3、连接以求两表，注意也有加上IFNULL()来包含无接单量的例外情况。
**其他：**
YEAR()求年份。
MONTH()求月份。
DATE_FORMAT(join_date,'%Y%m')提取注册日期的年月份，“%Y%m”定义了输出格式是YYYYMM且为数值型。
EXTRACT(YEAR_MONTH FROM join_date)，也可以提取注册日期的年月份。
# 4.1635. Hopper Company Queries I
认真理清思路，表有点多，别乱
```
# # Write your MySQL query statement below
#不要试图三个合并 然后直接求两个统计量，还是乖乖两两合并 分别统计

#1.构建连续月份数列
with recursive m(n) as(
    select 1 
    union all 
    select n+1 from m
    where n<12
)

select n as month,active_drivers,accepted_rides
from
m left join
#2.表1统计active_drivers
(select m.n,
    sum(year(join_date)<=2019)+
    sum(year(join_date)=2020 and month(join_date)<=m.n) as active_drivers
from drivers d join m 
group by m.n) temp1
using(n)
left join 
#3.表2统计accepted_rides
(select m.n,
    sum(year(requested_at)=2020 and month(requested_at)=m.n and ride_distance is not null) accepted_rides
from rides r left join acceptedrides a 
using(ride_id)
join m
group by m.n) temp2
using(n)
#4.将表1 表2 leftjoin到完整月份数列上
```

