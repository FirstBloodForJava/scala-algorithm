"""
3983. 一次替换后的子序列

给你两个由小写英文字母组成的字符串 s 和 t。
你最多可以选择 s 中的一个下标，并将该下标处的字符 替换 为任意小写英文字母。
如果可以使 s 成为 t 的一个 子序列，则返回 true；否则返回 false。
子序列 是指通过删除另一个字符串中的某些字符或不删除任何字符，并且不改变剩余字符相对顺序后得到的字符串。
"""


class Solution:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        """
        前后缀分解：枚举 t 前后缀匹配 s 的前后缀情况
        状态机：两种状态
            j0 表示 s 的前缀 [0, j0) 是 t 前缀的子序列，且 j0 尽量大
            j1 表示 s 在修改一次的情况下，s 的前缀是 [0, j1) 是 t 前缀的子序列，且 j1 尽量大
        """
        n = len(s)
        if n > len(t):
            return False
        j0 = j1 = 0
        for c in t:
            if s[j1] == c:
                # 修改后匹配
                j1 += 1
            # 直接强制修改匹配
            j1 = max(j1, j0 + 1)
            if s[j0] == c:
                j0 += 1
            if j1 == n:
                return True

        return False
