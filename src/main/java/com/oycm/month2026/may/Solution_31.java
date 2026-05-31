package com.oycm.month2026.may;

import java.util.Arrays;

public class Solution_31 {

    /**
     * 2126. <a href="https://leetcode.cn/problems/destroying-asteroids/description/">摧毁小行星</a> 1335
     *
     * @param mass
     * @param asteroids
     * @return
     */
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        /*
        给你一个整数 mass ，它表示一颗行星的初始质量。再给你一个整数数组 asteroids ，其中 asteroids[i] 是第 i 颗小行星的质量。
        你可以按 任意顺序 重新安排小行星的顺序，然后让行星跟它们发生碰撞。
        如果行星碰撞时的质量 大于等于 小行星的质量，那么小行星被 摧毁 ，并且行星会 获得 这颗小行星的质量。否则，行星将被摧毁。
        如果所有小行星 都 能被摧毁，请返回 true ，否则返回 false 。
         */
        /*
        把 asteroids 升序排序，mass 优先选择最小质量发生碰撞，如果 mass >= asteroids[i]，则 mass += asteroids[i]；
        否则返回 false
         */
        /*
        O(n) 做法，按二进制长度分组，如果 mass >= 长为 k 的一组二进制的最小值，这一组都能碰撞并删除，否则返回 false
         */
        int mx = 0;
        for (int x : asteroids) {
            mx = Math.max(mx, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        long[] sum = new long[m];
        int[] mn = new int[m];
        Arrays.fill(mn, Integer.MAX_VALUE);
        for (int x : asteroids) {
            int i = 31 - Integer.numberOfLeadingZeros(x);
            sum[i] += x;
            mn[i] = Math.min(mn[i], x);
        }
        long k = mass;
        for (int i = 0; i < m; i++) {
            // 没有长为 k 的数
            if (mn[i] == Integer.MAX_VALUE) continue;
            if (k >= mn[i]) {
                k+= sum[i];
            } else {
                return false;
            }
        }

        return true;
    }

}
