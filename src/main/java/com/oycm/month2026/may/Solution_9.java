package com.oycm.month2026.may;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_9 {

    /**
     * 1914. <a href="https://leetcode.cn/problems/cyclically-rotating-a-grid/description/">循环轮转矩阵</a> 1766
     *
     * @param grid m x n 的整数矩阵,  m 和 n 都是 偶数
     * @param k
     * @return
     */
    public int[][] rotateGrid(int[][] grid, int k) {
        /*
        矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。
        返回执行 k 次循环轮转操作后的矩阵。
         */
        /*
        循环轮转有什么规律？
        top, down, left, right 分别表示一层 起始行，结束行，起始列，结束列 下标。
        一层的元素个数 = 2 * (down - top + right - left)
        top < down && left < right 成立时，才会有一层
        上 (top, left) -> (top, right)
        右 (top, right) -> (down, right)
        下 (down, right) -> (down, left)
        左 (down, left) -> (top, left)
        元素个数记为 size，k % size 就是取 (i, j) 顺时针放行遍历，选择前面第几个数
         */
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        int top = 0, down = m - 1;
        int left = 0, right = n - 1;
        while (top < down && left < right) {
            int w = right - left, h = down - top;
            int size = 2 * (w + h);
            int v = k % size;
            int[] xy = {0, 0};
            if (v <= w) {
                xy[0] = top;
                xy[1] = left + v;
            } else if (v <= w + h) {
                xy[0] = top + v - w;
                xy[1] = right;
            } else if (v <= 2 * w + h) {
                xy[0] = down;
                xy[1] = right - (v - w - h);
            } else {
                xy[0] = down - (v - 2 * w - h);
                xy[1] = left;
            }
            int[] ij = {top, left};
            for (int t = 0; t < size; t++) {
                ans[ij[0]][ij[1]] = grid[xy[0]][xy[1]];
                // 下一个 (i, j), (x, y)
                next(ij, top, down, left, right);
                next(xy, top, down, left, right);
            }

            top++;
            down--;
            left++;
            right--;
        }
        return ans;
    }

    public void next(int[] xy, int top, int down, int left, int right) {
        if (xy[0] == top) {
            if (xy[1] == right) {
                xy[0]++;
            } else {
                xy[1]++;
            }
        } else if (xy[0] == down) {
            if (xy[1] == left) {
                xy[0]--;
            } else {
                xy[1]--;
            }
        } else if (xy[1] == right) {
            xy[0]++;
        } else {
            xy[0]--;
        }
    }

    public int[][] rotateGrid_2(int[][] grid, int k) {
        /*
        通过向量数组控制环的遍历
         */
        // 上右下左 指的边
        // 右下左上 移动方向
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = grid.length, n = grid[0].length;
        // 最外层最多数量
        List<Integer> a = new ArrayList<>(2 * (m + n - 2));
        // 由最短决定层数
        for (int i = 0; i < Math.min(m, n) / 2; i++) {
            int m0 = m - 2 * i, n0 = n - 2 * i;
            // 左上角坐标
            int x = i, y = i;
            a.clear();
            /*
            移动 n-1，再移动 m-1,再移动 n-1，再移动 m-1
            n m 来回切换
             */
            for (int[] dir : dirs) {
                for (int j = 0; j < n0 - 1; j++) {
                    a.add(grid[x][y]);
                    x += dir[0];
                    y += dir[1];
                }
                // 来回切换
                int temp = n0;
                n0 = m0;
                m0 = temp;
            }
            /*
            a 是 环 顺时针遍历的结果
            现在要把 a 向左移动 t 次
             */
            int t = k % a.size();
            Collections.rotate(a, -t);

            int l = 0;
            for (int[] dir : dirs) {
                for (int j = 0; j < n0 - 1; j++) {
                    grid[x][y] = a.get(l++);
                    x += dir[0];
                    y += dir[1];
                }
                // 来回切换
                int temp = n0;
                n0 = m0;
                m0 = temp;
            }
        }

        return grid;
    }


    public static void main(String[] args) {
        Solution_9 solution = new Solution_9();
        solution.rotateGrid(DataCreateUtils.twoDimensionInts("[[3970,1906,3608,298,3072,3546,1502,773,4388,3115,747,3937],[2822,304,4179,1780,1709,1058,3645,681,2910,2513,4357,1038],[4471,2443,218,550,2766,4780,1997,1672,4095,161,4645,3838],[2035,2350,3653,4127,3208,4717,4347,3452,1601,3725,3060,2270],[188,2278,81,3454,3204,1897,2862,4381,3704,2587,743,3832],[996,4499,66,2742,1761,1189,608,509,2344,3271,3076,108],[3274,2042,2157,3226,2938,3766,2610,4510,219,1276,3712,4143],[744,234,2159,4478,4161,4549,4214,4272,701,4376,3110,4896],[4431,1011,757,2690,83,3546,946,1122,2216,3944,2715,2842],[898,4087,703,4153,3297,2968,3268,4717,1922,2527,3139,1516],[1086,1090,302,1273,2292,234,3268,2284,4203,3838,2227,3651],[2055,4406,2278,3351,3217,2506,4525,233,3829,63,4470,3170],[3797,3276,1755,1727,1131,4108,3633,1835,1345,1293,2778,2805],[1215,84,282,2721,2360,2321,1435,2617,1202,2876,3420,3034]]"), 405548684);
    }


}
