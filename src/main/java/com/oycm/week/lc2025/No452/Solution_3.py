"""
3566. 等积子集的划分方案
"""


class Solution_3:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        # 由于多个限制的情况会出现在相邻位置来回走的情况，去掉 energy 维度，
        # 只有当 当前到达当前位置的能量比之前大时，才进入这个位置
        m, n = len(classroom), len(classroom[0])
        idx = [[0] * n for _ in range(m)]
        cnt_l = sx = sy = 0
        for i, row in enumerate(classroom):
            for j, c in enumerate(row):
                if c == 'L':
                    idx[i][j] = 1 << cnt_l
                    cnt_l += 1
                elif c == 'S':
                    sx, sy = i, j
        if cnt_l == 0:
            return 0
        DIRS = (-1, 0), (1, 0), (0, -1), (0, 1)
        max_energy = [[[-1] * (1 << cnt_l) for _ in range(n)] for _ in range(m)]
        max_energy[sx][sy][0] = energy
        q = [(sx, sy, energy, 0)]
        ans = 0
        u = (1 << cnt_l) - 1
        while q:
            tmp = q
            q = []
            for x, y, e, mask in tmp:
                if mask == u:
                    return ans
                if e == 0:
                    continue
                for dx, dy in DIRS:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < m and 0 <= ny < n and classroom[nx][ny] != 'X':
                        new_e = energy if classroom[nx][ny] == 'R' else e - 1
                        new_mask = mask | idx[nx][ny]
                        if new_e > max_energy[nx][ny][new_mask]:
                            max_energy[nx][ny][new_mask] = new_e
                            q.append((nx, ny, new_e, new_mask))
            ans += 1
        return -1
