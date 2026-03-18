package com.oycm.datastructure.binary_tree.n_tree;


public class Solution_5 {

    /**
     * 427. <a href="https://leetcode.cn/problems/construct-quad-tree/description/">建立四叉树</a>
     *
     * @param grid
     * @return
     */
    public Node construct(int[][] grid) {
        /*
        构建四叉树过程
        1. 如果当前网格的值相同, 将 isLeaf 设为 True, 将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止
        2. 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，将当前网格划分为四个子网格 左上/左下, 右上/右下 四个子网格
        3. 使用适当的子网格递归每个子节点
         */
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    public Node dfs(int[][] grid, int r0, int c0, int r1, int c1) {
        boolean same = true;
        for (int i = r0; i < r1; ++i) {
            for (int j = c0; j < c1; ++j) {
                if (grid[i][j] != grid[r0][c0]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }

        if (same) {
            return new Node(grid[r0][c0] == 1, true);
        }
        // 不相同, 父节点 设为任意值
        return new Node(
                true,
                false,
                dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
    }

}
