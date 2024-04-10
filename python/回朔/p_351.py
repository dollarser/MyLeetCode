# 参考：https://zhuanlan.zhihu.com/p/95618813
# 题目描述：
# 手机解锁界面是一个3 x 3的点绘制出来的网格，给你2个整数，分别是m和n，其中1<=m<=n<=9，
# 那么请你统计一下有多少种解锁手势，是至少经过m个点，但是最多经过不超过n个点。
# 有效解锁手势有以下4点规则：
# 1. 每一个解锁手势必须至少经过m个点，最多经过n个点。
# 2. 解锁手势不能经过重复的点。
# 3. 假如手势中有2个点是顺序经过点，那么这2个点的手势轨迹不能跨过任何未被经过的点。
# 4. 经过点点顺序不同则表示为不同的解锁手势。
# 解释：
# | 1 | 2 | 3 |
# | 4 | 5 | 6 |
# | 7 | 8 | 9 |
# 4 - 1 - 3 - 6 : 无效
# 4 - 1 - 9 - 2 : 无效
# 2 - 4 - 1 - 3 - 6 : 有效
# 6 - 5 - 4 - 1 - 9 - 2 : 有效

class Solution:
    def numberOfPatterns(self, m: int, n: int) -> int:
        self.res = 0
        all_set = set(range(1, 10))

        def backtrack(cur_set: set, prev):
            if len(cur_set) >= m:
                self.res += 1
                if len(cur_set) == n:
                    return
            # 减运算求差集
            for num in all_set - cur_set:
                abs_t = abs(num - prev)
                # 通过前后两个值的差值分类讨论
                if abs_t == 2 and min(num, prev) in {1, 4, 7} and (num + prev) // 2 not in cur_set: 
                    continue
                if abs_t == 4 and min(num, prev) == 3 and 5 not in cur_set: 
                    continue
                if abs_t == 6 and (num + prev) // 2 not in cur_set: 
                    continue
                if abs_t == 8 and 5 not in cur_set: 
                    continue
                # 或运算求并集
                backtrack(cur_set | {num}, num)

        for i in range(1, 10):
            backtrack({i}, i)

        return self.res


def check1(arr:list, m, n) -> int:
    
    cnt = 0
    if len(arr) >= m:
         cnt = 1
    
    # 递归终止条件
    if len(arr) == n:
        return 1

    for i in range(1, 10):
        if len(arr) == 0:
            arr.append(i)
            cnt = cnt + check1(arr, m, n)
            arr.pop()
            continue
        
        # 如果当前数包含在之前，说明重复跳过
        if i in arr:
            continue
        abs_t = abs(i - arr[-1])
        # 左右差两个
        if abs_t == 2 and min(i, arr[-1]) in {1, 4, 7} and (i + arr[-1])//2 not in arr:
            continue
        # 1和9
        if abs_t == 8 and 5 not in arr:
            continue
        # 3和7
        if abs_t == 4 and min(i, arr[-1]) == 3 and 5 not in arr:
            continue
        # 上下差两个
        if abs_t == 6 and (i+arr[-1])//2 not in arr:
            continue
        arr.append(i)
        cnt = cnt + check1(arr, m, n)
        # arr.remove(i)
        # arr.pop(0)
        arr.pop()
    return cnt


def check(arr:list, next:int, m:int, n:int) -> int:
    cnt = 0
    abs_t = 0
    
    if next in arr:
        return 0

    if len(arr) != 0:
        abs_t = abs(next- arr[-1])
    # 左右差两个
    if len(arr) != 0 and abs_t == 2 and min(next, arr[-1]) in {1, 4, 7} and (next + arr[-1])//2 not in arr:
        return 0
    # 1和9
    if len(arr) != 0 and abs_t == 8 and 5 not in arr:
        return 0
    # 3和7
    if len(arr) != 0 and abs_t == 4 and min(next, arr[-1]) == 3 and 5 not in arr:
        return 0
    # 上下差两个
    if len(arr) != 0 and abs_t == 6 and (next + arr[-1])//2 not in arr:
        return 0
    
    arr.append(next)
    if len(arr) >= n:
        arr.pop()
        return 1
    
    if len(arr) >= m:
        cnt = 1
    else:
        cnt = 0
    
    for i in range(1, 10):
        cnt = cnt + check(arr, i, m, n)
    arr.pop()
    return cnt

if __name__ == "__main__":
    cnt = 0
    m = 2
    n = 5
    cnt = cnt + check1(arr=[], m=m, n=n)
    print(cnt)

    cnt = 0
    for i in range(1, 10):
        cnt += check([], i, m, n)
    print(cnt)

    s = Solution()
    s.numberOfPatterns(m, n)
    print(s.res)
        