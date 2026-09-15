"""
392. 判断子序列
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

如果有大量的输入 s，判断 s 是否为 t 的子序列，该怎么判断
"""
class Solution_1:
    def isSubsequence(self, s: str, t: str) -> bool:
        """
        定义 next[i][c] 表示 t 中下标大于等于 i 最近字母 c 的下标，如果 c 不存在，规则 next[i][c] = n

        """
        n = len(t)
        next = [[n] * 26 for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            next[i][:] = next[i + 1]
            next[i][ord(t[i]) - ord('a')] = i
        i = -1
        for c in s:
            i = next[i + 1][ord(c) - ord('a')]
            if i == n:
                return False
        return True
