package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_6 {

    /**
     * 2850. <a href="https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/description/">将石头分散到网格图的最少移动次数</a> 2001
     *
     * @param grid 二维整数矩阵, 3 * 3, grid[i][j] 表示格子中的石头数量。总共恰好有 9 个石头，一个格子里可能会有 多个 石头。
     * @return
     */
    public int minimumMoves(int[][] grid) {
        /*
        每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子（可上下左右移动）。
        请你返回每个格子恰好有一个石头的 最少移动次数 。
         */
        /*
        考虑 所有 grid[i][j] = 0 从 grid[i'][j'] > 1 的地方移动过来 abs(i' - i) + abs(j' - j)
        枚举还剩哪些 0 可以选哪些 grid[i][j] > 1 可选
         */
        List<Integer> zero = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    zero.add(i * 3 + j);
                }
            }
        }
        dfs(zero, grid, 0);
        return ans;
    }

    int ans = 100;

    public void dfs(List<Integer> zero, int[][] grid, int d) {
        if (zero.isEmpty()) {
            ans = Math.min(d, ans);
        }
        if (d >= ans) {
            return;
        }
        for (Integer cur : zero) {
            // 枚举所有可用的 enable
            int x = cur / 3, y = cur % 3;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] > 1) {
                        grid[i][j]--;
                        List<Integer> temp = new ArrayList<>(zero);
                        temp.remove(cur);
                        dfs(temp, grid, d + Math.abs(i - x) + Math.abs(j - y));
                        // 回溯
                        grid[i][j]++;
                    }
                }
            }

        }
    }

}

class Solution_6_Permutation {

    public int minimumMoves(int[][] grid) {
        // 记录所有 > 1 的坐标，有重复值
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        // from 的全排列和 to 计算记录
        for (List<int[]> from2 : permutations(from)) {
            int total = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                total += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            ans = Math.min(ans, total);
        }
        return ans;
    }

    // 返回 arr 集合的全排列
    private List<List<int[]>> permutations(List<int[]> arr) {
        List<List<int[]>> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    private void permute(List<int[]> arr, int start, List<List<int[]>> result) {
        if (start == arr.size()) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < arr.size(); i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            // 回溯
            swap(arr, start, i);
        }
    }

    private void swap(List<int[]> arr, int i, int j) {
        int[] temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
