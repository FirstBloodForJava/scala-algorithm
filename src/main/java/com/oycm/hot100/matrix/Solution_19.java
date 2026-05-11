package com.oycm.hot100.matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution_19 {

    /**
     * 54. <a href="https://leetcode.cn/problems/spiral-matrix/description/">螺旋矩阵</a>
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        /*
        给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
         */
        /*
        标记法
        定义能走的 4 个方向 右下左上，当碰到边界或已经走过的点时，就需要拐弯
         */
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        int size = m * n;
        List<Integer> ans = new ArrayList<>(size);
        int di = 0;
        int i = 0;
        int j = 0;
        for (int k = 0; k < size; k++) {
            ans.add(matrix[i][j]);
            // 标记为访问
            matrix[i][j] = Integer.MAX_VALUE;
            // 计算下一步位置
            int x = i + dirs[di][0];
            int y = j + dirs[di][1];
            // 看位置是否需要转弯
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4;
            }
            i += dirs[di][0];
            j += dirs[di][1];
        }

        return ans;
    }

    public List<Integer> spiralOrder_1(int[][] matrix) {
        /*
        [
            [1,2,3,4],
            [5,6,7,8],
            [9,10,11,12]
        ]
        右 1 -> 2 -> 3 -> 4 移动 4 次
        下 8 -> 12 移动 2 次
        左 11 -> 10 -> 9 移动 3 次
        上 5 移动 1 次
        右 6 -> 7 移动 2 次
        可以发现规律，再移动多少次后换方向，次数也是交替减少 4 -> 3 -> 2; 2 -> 1
         */
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        int size = m * n;
        List<Integer> ans = new ArrayList<>(size);
        int i = 0;
        // 先移动，再加入答案
        int j = -1;
        for (int di = 0; ans.size() < size; di = (di + 1) % 4) {
            for (int k = 0; k < n; k++) {
                i += dirs[di][0];
                j += dirs[di][1];
                ans.add(matrix[i][j]);
            }
            int temp = n;
            n = m - 1;
            m = temp;
        }
        return ans;
    }


    /**
     * @param matrix
     * @param k
     * @return
     */
    public int[] spiralOrder(int[][] matrix, long k) {
        /*
        给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序走，计算走 k 步后的坐标
        m, n [1, 1e9]
         */
        /*
        解题思路，要从外到内，根据步数，找到 k 在第几层
        根据前面的定义，从 [0, -1] 走外层一圈的步数为 n + m-1 + n-1 + m-2 = 2*(n + m) - 4
        如果是从 (0, 0) 出发，则 k++ 后才是按上述步骤走 k 步的坐标。
        每一层的步数，有以下规律
            第一层，2 * (m + n) - 4
            第二层，2 * (m + n - 4) - 4
            是一个以 8 递减的等比数列
            层数为 min(n, m) / 2 上取整
        先要找出 k 在第几层
         */
        /*
        等差数列求和，已知首项 a1, 公差 d，求前 n 项和
            Sn = n*a1 + n(n-1)d / 2
        n * L + n(n-1)d / 2 <= k
        一元二次方程求最大 n，或二分查找
        */
        int m = matrix.length, n = matrix[0].length;
        long a = 2L * (m + n - 2);
        int d = -8;
        int r = Math.min(m - 1, n - 1) / 2 + 2;
        int layer = lowerBound(a, 0, r, k, d);
        int x = layer, y = layer;
        long pre = layer * a + (long) layer * (layer - 1) * d / 2;
        k -= pre;
        int m0 = m - 2 * layer;
        int n0 = n - 2 * layer;

        if (k < n0) {
            // 右 最多 n0 - 1 布
            y += k;
        } else if (k <= n0 + m0 - 2) {
            // 下
            x += layer + n0 - 1;
            y = (int) (layer + k - n0 + 1);
        } else if (k <= 2L * n0 + m0 - 3) {
            // 左
            x += 2 * n0 + m0 - 3 - k;
            y = layer + m0 - 1;
        } else {
            x = layer;
            y += 2 * (n0 + m0) - 4 - k;
        }
        return new int[]{x, y};
    }

    public int lowerBound(long a, int l, int r, long k, int d) {
        /*
        求最大的 n，满足 Sn <= k
        Sn = n * a + n * (n-1) * d/ 2
         */
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            long s = mid * a + (long) mid * (mid - 1) * d / 2;
            if (s <= k) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        Solution_19 solution = new Solution_19();

        int[] ints = solution.spiralOrder(new int[10][10], 36);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

}
