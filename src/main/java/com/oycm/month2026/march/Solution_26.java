package com.oycm.month2026.march;

import com.oycm.utils.DataCreateUtils;
import com.oycm.utils.ReadFileUtils;

import java.util.HashMap;
import java.util.Map;

public class Solution_26 {

    /**
     * 3548. <a href="https://leetcode.cn/problems/equal-sum-grid-partition-ii/description/">等和矩阵分割 II</a> 2245
     *
     * @param grid
     * @return
     */
    public boolean canPartitionGrid(int[][] grid) {
        /*
        是否存在 一条水平或一条垂直分割线 将矩阵分割成两部分,
            两个部分中所有元素的和 相等，或者总共 最多移除一个单元格 （从其中一个部分中）的情况下可以使它们相等
            如果移除某个单元格，剩余部分必须保持 连通（每个单元格都可以通过向上、向下、向左或向右移动到达同一部分中的其他单元格）
         */
        /*
        连通的条件 分割后的矩形, 如果只有 一列/一行 则只能是 行/列的 头尾, 否则不连通, 其它情况都是连通的
        hash 表记录 下标, 如果相同元素太多, 需要循环判断
        提示: 特殊处理 一行/一列, 其它情况只看是否存在一个数 满足 两边和相等
         */
        Map<Integer, Integer> map = new HashMap<>();
        int m = grid.length, n = grid[0].length;

        long sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                map.merge(grid[i][j], 1, Integer::sum);
            }
        }
        // 行判断
        long rows = 0;
        Map<Integer, Integer> preMap = new HashMap<>(map.size(), 1);
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                preMap.merge(grid[i][j], 1, Integer::sum);
                rows += grid[i][j];
            }
            long diffL = sum - rows * 2;
            int diff = diffL > 100000 || diffL < -100000 ? 100001 : (int) (sum - rows * 2);
            if (diff == 0) {
                return true;
            } else if (diff > 0) {
                // 下面一行更大,
                if (i == m - 2 || n == 1) {
                    // 只有一列的情况
                    if (grid[i + 1][0] == diff || grid[i + 1][n - 1] == diff || grid[m - 1][0] == diff) {
                        return true;
                    }
                } else {
                    if (map.getOrDefault(diff, 0) - preMap.getOrDefault(diff, 0) > 0) {
                        return true;
                    }
                }
            } else {
                if (i == 0 || n == 1) {
                    if (grid[i][0] + diff == 0 || grid[i][n - 1] + diff == 0 || grid[0][0] + diff == 0) {
                        return true;
                    }
                } else {
                    if (preMap.containsKey(-diff)) {
                        return true;
                    }
                }
            }
        }
        // 列判断
        preMap.clear();
        long cols = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                cols += grid[i][j];
                preMap.merge(grid[i][j], 1, Integer::sum);
            }
            long diffL = sum - cols * 2;
            int diff = diffL > 100000 || diffL < -100000 ? 100001 : (int) diffL;
            if (diff == 0) {
                return true;
            } else if (diff > 0) {
                // 右边大于左边
                if (j == n - 2 || m == 1) {
                    // 只有一行的情况
                    if (grid[0][j + 1] == diff || grid[m - 1][j + 1] == diff || grid[0][n - 1] == diff) {
                        return true;
                    }
                } else {
                    if (map.getOrDefault(diff, 0) - preMap.getOrDefault(diff, 0) > 0) {
                        return true;
                    }
                }
            } else {
                if (j == 0 || m == 1) {
                    if (grid[0][j] + diff == 0 || grid[m - 1][j] + diff == 0 || grid[0][0] + diff == 0) {
                        return true;
                    }
                } else {
                    if (preMap.containsKey(-diff)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_26().canPartitionGrid(DataCreateUtils.twoDimensionInts(ReadFileUtils.readFile("data/data.txt"))));
    }

}
