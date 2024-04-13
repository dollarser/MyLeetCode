# 1651Hopper 公司查询 III
<p>Table: <code>Drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| driver_id   | int     |
| join_date   | date    |
+-------------+---------+
driver_id是该表的主键。
该表的每一行均包含驾驶员的ID以及他们加入Hopper公司的日期。</pre>

<p>&nbsp;</p>

<p>Table: <code>Rides</code></p>

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

<p>Table: <code>AcceptedRides</code></p>

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
表中的行程信息都在“<code>Rides</code>”表中存在。</pre>

<p>&nbsp;</p>

<p>编写SQL查询以计算从&nbsp;<strong>2020年1月至3月至2020年10月至12月&nbsp;</strong>的每三个月窗口的&nbsp;<code>average_ride_distance</code>&nbsp;和&nbsp;<code>average_ride_duration</code>&nbsp;。将&nbsp;<code>average_ride_distance</code>&nbsp;和&nbsp;<code>average_ride_duration</code>&nbsp;四舍五入至 <strong>小数点后两位</strong> 。<br />
通过将三个月的总&nbsp;<code>ride_distance</code>&nbsp;相加并除以 <code>3</code> 来计算&nbsp;<code>average_ride_distance</code>&nbsp;。<code>average_ride_duration</code>&nbsp;的计算方法与此类似。<br />
返回按&nbsp;<code>month</code>&nbsp;升序排列的结果表，其中&nbsp;<code>month</code>&nbsp;是起始月份的编号（一月为 1，二月为 2 ...）。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入:</strong> 
Drivers table:
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
Rides table:
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
AcceptedRides table:
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
<strong>输出:</strong> 
+-------+-----------------------+-----------------------+
| month | average_ride_distance | average_ride_duration |
+-------+-----------------------+-----------------------+
| 1     | 21.00                 | 12.67                 |
| 2     | 21.00                 | 12.67                 |
| 3     | 21.00                 | 12.67                 |
| 4     | 24.33                 | 32.00                 |
| 5     | 57.67                 | 41.33                 |
| 6     | 97.33                 | 64.00                 |
| 7     | 73.00                 | 32.00                 |
| 8     | 39.67                 | 22.67                 |
| 9     | 54.33                 | 64.33                 |
| 10    | 56.33                 | 77.00                 |
+-------+-----------------------+-----------------------+
<strong>解释:</strong> 
到1月底--&gt;平均骑行距离=（0+0+63）/3=21，平均骑行持续时间=（0+0+38）/3=12.67
到2月底--&gt;平均骑行距离=（0+63+0）/3=21，平均骑行持续时间=（0+38+0）/3=12.67
到3月底--&gt;平均骑行距离=（63+0+0）/3=21，平均骑行持续时间=（38+0+0）/3=12.67
到4月底--&gt;平均骑行距离=（0+0+73）/3=24.33，平均骑行持续时间=（0+0+96）/3=32.00
到5月底--&gt;平均骑行距离=（0+73+100）/3=57.67，平均骑行持续时间=（0+96+28）/3=41.33
到6月底--&gt;平均骑行距离=（73+100+119）/3=97.33，平均骑行持续时间=（96+28+68）/3=64.00
到7月底--&gt;平均骑行距离=（100+119+0）/3=73.00，平均骑行持续时间=（28+68+0）/3=32.00
到8月底--&gt;平均骑行距离=（119+0+0）/3=39.67，平均骑行持续时间=（68+0+0）/3=22.67
9月底--&gt;平均骑行距离=（0+0+163）/3=54.33，平均骑行持续时间=（0+0+193）/3=64.33
到10月底--&gt;平均骑行距离=（0+163+6）/3=56.33，平均骑行持续时间=（0+193+38）/3=77.00</pre>
































# 解题:
# 1.【95.52%】10行-递归CTE+两次左连接
![image.png](https://pic.leetcode-cn.com/1664375848-jOtgLb-image.png)
### 解题思路
1. 递归CTE生成1-10数列表t；
2. t左连接Rides，以t.n between month(Rides.requested_at) -2 and month(Rides.requested_at)为条件选出t.n月及向下两个月的数据(注意年份也要加上)；
3. 继续左连接AcceptedRides，以Rides.ride_id = Accepted Rides.ride_id为条件筛选接受订单，ifnull(round(sum()))完成计算。

### 代码

```mysql
# Write your MySQL query statement below
with recursive t(n) as(
    select 1 n 
    union all
    select n+1 from t where n<10
)

select t.n as month, ifnull(round(sum(AcceptedRides.ride_distance)/3,2),0) as average_ride_distance, ifnull(round(sum(AcceptedRides.ride_duration)/3,2),0) as average_ride_duration
from t
left join Rides on t.n between month(Rides.requested_at)-2 and month(Rides.requested_at) and year(Rides.requested_at) = 2020
left join AcceptedRides on Rides.ride_id = AcceptedRides.ride_id
group by t.n
```
# 2.1651. Hopper Company Queries III
这个系列里面比较简单的一道题了
```
# Write your MySQL query statement below
with recursive m(n) as(
select 1 
union all 
select n+1 from m 
where n<12
)

select m.n month,
ifnull(round(sum(ride_distance)/3,2),0) as average_ride_distance,
ifnull(round(sum(ride_duration)/3,2),0) as average_ride_duration
from acceptedrides a left join rides r 
using(ride_id)
right join m 
on year(requested_at)=2020 
and month(requested_at) between m.n and m.n+2 #这样写不错哦！
group by m.n
order by m.n
limit 10 #用limit控制
```

# 3.递归造表，接双开窗函数！想好连接算法就可以了！
### 解题思路
此处撰写解题思路
### 代码
```mysql
# Write your MySQL query statement below
with recursive g as (
    select 1 as 'month'
    union all
    select g.month+1 from g where g.month<12)
select g.month,
round(avg(ifnull(sum(e.ride_distance),0))over(order by g.month range between current row and 2 following ),2) as average_ride_distance,
round(avg(ifnull(sum(e.ride_duration),0))over(order by g.month range between current row and 2 following),2) as average_ride_duration
 from g left join (
select b.month,a.ride_distance,a.ride_duration
from AcceptedRides a join (select c.ride_id, month(c.requested_at)  as month from Rides c where year(c.requested_at)='2020') b on a.ride_id=b.ride_id) e using(month) group by g.month order by 1 asc limit 10
```
# 4.1651. Hopper Company Queries III(Oracle单百解法)
```
select *
from (
         select a.months as month ,
                round(SUM(NVL(b.ride_distance, 0)) over (order by a.months rows between current row and 2 following) /
                      3, 2) average_ride_distance,
                round(SUM(NVL(b.ride_duration, 0)) over (order by a.months rows between current row and 2 following) /
                      3, 2) average_ride_duration
         from (
                  select level months, add_months(DATE'2020-01-01', level - 1) months2
                  from dual connect by level<13
              ) A
                  LEFT JOIN
              (select trunc(a.requested_at, 'mm') requested_at,
                      SUM(b.ride_distance)        ride_distance,
                      SUM(b.ride_duration)        ride_duration
               from Rides a,
                    AcceptedRides b
               where to_char(a.requested_at, 'yyyy') = '2020'
                 AND a.ride_id = b.ride_id
               group by trunc(a.requested_at, 'mm')) B
              ON A.months2 = B.requested_at
     ) A
WHERE A.month  < 11
```
