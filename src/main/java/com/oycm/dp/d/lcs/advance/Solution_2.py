"""
115. 不同的子序列

给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
测试用例保证结果在 32 位有符号整数范围内。
"""


class Solution_2:
    def numDistinct(self, s: str, t: str) -> int:
        n, m = len(s), len(t)
        if n < m:
            return 0
        # 递归边界 dfs(i, -1) = 1, f[i][0] = 1
        f = [[1] + [0] * m for _ in range(n + 1)]
        for i, x in enumerate(s):
            # j 最大为 min(i, m-1), 当 i < m 时，无法得到比 i + 1 还长的子序列；
            # j 最小为 max(m-n+i, 0), m-j <= n-i => m-m+i <= j
            for j in range(max(m - n + i, 0), min(i + 1, m)):
                f[i + 1][j + 1] = f[i][j + 1]
                if x == t[j]:
                    f[i + 1][j + 1] += f[i][j]
        return f[n][m]
