package com.oycm.week2024.No385;

import java.util.HashMap;
import java.util.Map;

public class Solution_3 {

    /**
     * 3044. <a href="https://leetcode.cn/problems/most-frequent-prime/description/">出现频率最高的质数</a> 1737
     *
     * @param mat mat[i][j] [1, 9]; 1 <= m, n <= 6
     * @return
     */
    public int mostFrequentPrime(int[][] mat) {
        /*
        给你一个大小为 m x n 、下标从 0 开始的二维矩阵 mat 。在每个单元格，你可以按以下方式生成数字：
            最多有 8 条路径可以选择：东，东南，南，西南，西，西北，北，东北。
            选择其中一条路径，沿着这个方向移动，并且将路径上的数字添加到正在形成的数字后面。
            注意，每一步都会生成数字，例如，如果路径上的数字是 1, 9, 1，那么在这个方向上会生成三个数字：1, 19, 191。
        返回在遍历矩阵所创建的所有数字中，出现频率最高的、大于 10 的质数；如果不存在这样的质数，则返回 -1 。如果存在多个出现频率最高的质数，那么返回其中最大的那个。
         */
        /*
        预处理哪些是质数，最大的数是 999999，质数数组为 1000000
        使用 埃氏筛法，非 1 最小质因子，就是本身的就是质数
        使用向量法，表示一个路径的移动方向，生成数组，如果是质数，则记录 key 和 其次数
         */
        init();
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int d = mat[i][j];
                    int x = i + dir[0], y = j + dir[1];
                    while (x >= 0 && x < m && y >= 0 && y < n) {
                        d = d * 10 + mat[x][y];
                        if (isPrime(d)) {
                            cnt.merge(d, 1, Integer::sum);
                        }
                        x += dir[0];
                        y += dir[1];
                    }
                }
            }
        }
        if (cnt.isEmpty()) return -1;
        int maxCnt = 0;
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            if (entry.getValue() > maxCnt) {
                ans = entry.getKey();
                maxCnt = entry.getValue();
            } else if (entry.getValue() == maxCnt && entry.getKey() > ans) {
                ans = entry.getKey();
            }
        }

        return ans;
    }

    private static boolean initialized = false;
    private static int[] lpf = new int[1000000];
    // 东, 南, 西, 北, 东南, 东北, 西北, 西南
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        for (int i = 2; i < lpf.length; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j < lpf.length; j += i) {
                    if (lpf[j] == 0) {
                        lpf[j] = i;
                    }
                }
            }
        }

    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
