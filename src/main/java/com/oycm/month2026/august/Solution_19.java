package com.oycm.month2026.august;

import java.util.HashMap;
import java.util.Map;

public class Solution_19 {

    /**
     * 1386. <a href="https://leetcode.cn/problems/cinema-seat-allocation/description/">安排电影院座位</a>
     *
     * @param n             [1, 1e9]
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        /*
        电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10。
        给定一个二维数组 reservedSeats ，其中 reservedSeats[i] = [row, seat] 表示第 row 行的座位 seat 已经被预定。
        四人小组必须被安排在同一排的四个座位上。该小组可以坐在以下座位块之一：
            座位 2, 3, 4, 5
            座位 4, 5, 6, 7
            座位 6, 7, 8, 9
        只有当该块中的所有座位都 没有 被预订时，才能使用该块。每个座位 最多 只能分配给一个小组。
        返回一个整数，表示可以分配的 最大 四人小组数量。
         */
        /*
        reservedSeats 按行分组，计算安排座位的行的最大安排数量
        (n - 安排行) * 2 + 上面计算的最大值就是答案
         */
        Map<Integer, Integer> map = new HashMap<>();
        int b1 = ((1 << 4) - 1);
        int b2 = b1 << 2;
        int b3 = b2 << 2;

        for (int[] p : reservedSeats) {
            if (2 <= p[1] && p[1] <= 9) {
                map.merge(p[0], 1 << (p[1] - 2), (a, b) -> a | b);
            }
        }
        int ans = (n - map.size()) * 2;

        for (int b : map.values()) {
            if ((b & b1) == 0 || (b & b2) == 0 || (b & b3) == 0) {
                ans++;
            }
        }

        return ans;
    }


}
