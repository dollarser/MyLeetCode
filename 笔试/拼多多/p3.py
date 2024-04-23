# 3. 无重复字符的最长子串
# https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/

# 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

# 示例 1:
# 输入: s = "abcabcbb"
# 输出: 3 
# 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

# 示例 2:
# 输入: s = "bbbbb"
# 输出: 1
# 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

# 示例 3:
# 输入: s = "pwwkew"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
#      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 
# 提示：
# 0 <= s.length <= 5 * 104
# s 由英文字母、数字、符号和空格组成

class Solution:
    """
    使用一个
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        # None 和 "" 都等价 false
        if not s:
            return 0
        lookup = set()
        max_len = 0
        cur_len = 0
        # 记录上一次有重复字符的下标
        left = 0
        for i in range(len(s)):
            while s[i] in lookup:
                lookup.remove(s[left])
                left += 1
                cur_len -= 1
            
            lookup.add(s[i])
            cur_len += 1
            max_len = max(max_len, cur_len)
        return max_len
    
    """
    使用双指针法，尾指针作为主遍历指针
    将遍历的结果添加到队列
    如果队列中有重复的，就将重复的和之前的删除
    """
    def lengthOfLongestSubstring1(self, s: str) -> int:
        # None 和 "" 都等价 false
        if not s:
            return 0
        lookup = list()
        max_len = 0
        # 记录上一次有重复字符的下标
        for i in range(len(s)):
            for j in range(len(lookup)-1, -1, -1):
                if lookup[j] == s[i]:
                    lookup = lookup[j+1:]
                    break
            lookup.append(s[i])
            max_len = max(max_len, len(lookup))
            
        return max_len
    
    # 头指针作为主指针
    def lengthOfLongestSubstring2(self, s: str) -> int:
        if not s:
            return 0
        
        max_len = 1
        for i in range(len(s)):
            si = s[i] # a
            st = set()
            st.add(s[i]) # set("a")
            for j in range(i+1, len(s)):
                # j = 1
                if s[j] not in st: # str1[j] = b
                    si = si.join(s[j]) # ab
                    st.add(s[j]) # set("ab")
                    max_len = max(max_len, len(st)) # 2
                else:
                    max_len = max(max_len, len(st))
                    break
                
        return max_len
            
if __name__ == "__main__":
    s = Solution()
    string = "babc"
    ans = s.lengthOfLongestSubstring2(string)
    print(ans)
