package com.oycm.month2026.april;

import com.oycm.utils.DataCreateUtils;

public class Solution_27 {

    /**
     * 1391. <a href="https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/description/">检查网格中是否存在有效路径</a> 1746
     *
     * @param grid m x n 的网格 grid, grid[i][j] [1, 6]
     * @return
     */
    public boolean hasValidPath(int[][] grid) {
        /*
        1 表示连接左单元格和右单元格的街道。
        2 表示连接上单元格和下单元格的街道。
        3 表示连接左单元格和下单元格的街道。
        4 表示连接右单元格和下单元格的街道。
        5 表示连接左单元格和上单元格的街道。
        6 表示连接右单元格和上单元格的街道。
        你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
        如果网格中存在有效的路径，则返回 true，否则返回 false 。
         */
        /*
        (i, j) 的值，可以决定当前移动方向，map 记录所有枚举的移动方向
        vis[i][j] 表示当前是否访问过，下一个 (x, y) 要和 (i, j) 相通，要对上
        0, -1, 往上走，新的 grid[x][y] 对应的要有 (0, 1) 方向
         */
        int m = grid.length;
        int n = grid[0].length;
        return dfs(0, 0, new boolean[m][n], grid);
    }


    public static int[][][] dirs = {
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
    };


    public static boolean enable(int[] dir, int[][] nextDirs) {
        for (int[] nextDir : nextDirs) {
            if (nextDir[0] + dir[0] == 0 && nextDir[1] + dir[1] == 0) {
                return true;
            }
        }

        return false;
    }

    public boolean dfs(int i, int j, boolean[][] vis, int[][] grid) {
        vis[i][j] = true;
        if (i == vis.length - 1 && j == vis[0].length - 1) return true;
        for (int[] dir : dirs[grid[i][j] - 1]) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (0 <= x && x < grid.length && 0 <= y && y < grid[i].length
                    && !vis[x][y]
                    && enable(dir, dirs[grid[x][y] - 1])
                    && dfs(x, y, vis, grid)
            ) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution_27 solution = new Solution_27();
        System.out.println(solution.hasValidPath(DataCreateUtils.twoDimensionInts("[[2,4,3],[6,5,2]]")));
    }
}
