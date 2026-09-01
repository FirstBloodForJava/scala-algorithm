from cmath import inf


class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        # 暴力枚举
        m, n = len(grid), len(grid[0])
        ans = [[0] * (n - k + 1) for _ in range(m - k + 1)]
        for i in range(m - k + 1):
            sub_grid = grid[i: i + k]
            for j in range(n - k + 1):
                a = []
                for row in sub_grid:
                    a += row[j: j + k]
                a.sort()
                res = inf
                for x, y in pairwise(a):
                    if x < y:
                        res = min(res, y - x)
                if res < inf:
                    ans[i][j] = res
        return ans
