package com.oycm.algorithm.g;

public class Solution_6 {

    /**
     * 1094. <a href="https://leetcode.cn/problems/car-pooling/description/">拼车</a> 1441
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        /*
        维持一个 from to 的差分数组 d[i] 差分数组本身都是 0
        trips[i][1] += capacity[i][0]
        trips[i][2] -= capacity[i][0]
         */
        int[] d = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int num = trips[i][0], from = trips[i][1], to = trips[i][2];
            d[from] += num;
            d[to] -= num;
        }
        int s = 0;
        for (int i = 0; i < d.length; i++) {
            s += d[i];
            if (s > capacity) {
                return false;
            }
        }

        return true;
    }
}
