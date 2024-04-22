# 43. 字符串相乘
# https://leetcode.cn/problems/multiply-strings/description/

# 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
# 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

# 示例 1:
# 输入: num1 = "2", num2 = "3"
# 输出: "6"

# 示例 2:
# 输入: num1 = "123", num2 = "456"
# 输出: "56088"

class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"
        
        m, n = len(num1), len(num2)
        # 设置一个最长的数组
        ansArr = [0] * (m + n)
        for i in range(m - 1, -1, -1):
            x = int(num1[i])
            # 直接按位乘保存在对应位置，之后再进行进位
            for j in range(n - 1, -1, -1):
                ansArr[i + j + 1] += x * int(num2[j])
        
        # 有高位进行进位
        for i in range(m + n - 1, 0, -1):
            ansArr[i - 1] += ansArr[i] // 10
            ansArr[i] %= 10
        
        # 判断结果是n + m位还是n + m - 1位
        index = 1 if ansArr[0] == 0 else 0
        ans = "".join(str(x) for x in ansArr[index:])
        return ans

    # 直接做法
    def multiply1(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"
        
        ans = "0"
        m, n = len(num1), len(num2)
        for i in range(n - 1, -1, -1):
            add = 0
            y = int(num2[i])
            # 低位补零
            curr = ["0"] * (n - i - 1)
            for j in range(m - 1, -1, -1):
                product = int(num1[j]) * y + add
                curr.append(str(product % 10))
                add = product // 10
            
            if add > 0:
                curr.append(str(add))
            # 列表转字符串
            curr = "".join(curr[::-1])
            ans = self.addStrings(ans, curr)
        
        return ans
    
    # 两个字符串相加
    def addStrings(self, num1: str, num2: str) -> str:
        i, j = len(num1) - 1, len(num2) - 1
        add = 0
        ans = list()
        while i >= 0 or j >= 0 or add != 0:
            x = int(num1[i]) if i >= 0 else 0
            y = int(num2[j]) if j >= 0 else 0
            result = x + y + add
            ans.append(str(result % 10))
            add = result // 10
            i -= 1
            j -= 1
        return "".join(ans[::-1])

    # 自己的做法
    def multiply2(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"

        ans_l = []
        for i in  range(len(num1)-1, -1, -1):
            h = 0
            ans_tm = [0] * (len(num1) - 1 - i)

            for j in range(len(num2)-1, -1, -1):
                a = int(num1[i]) * int(num2[j]) + h
                h = a // 10
                l = a % 10
                ans_tm.append(l)
            
            if h != 0:
                ans_tm.append(h)
            
            # 计算两个数相加
            h = 0
            add = []
            i, j = 0, 0
            while i < len(ans_l) or j < len(ans_tm) or h != 0:
                a = 0 if j >= len(ans_tm) else ans_tm[j]
                b = 0 if i >= len(ans_l) else ans_l[i]
                res = a + b + h
                add.append(res % 10)
                h = res // 10
                i += 1
                j += 1
            ans_l = add
            
        ans_l.reverse()

        return "".join([str(s) for s in ans_l])

if __name__ == "__main__":
    s = Solution()
    num1 = "123"
    num2 = "456"
    ans = s.multiply2(num1, num2)
    print(ans)
