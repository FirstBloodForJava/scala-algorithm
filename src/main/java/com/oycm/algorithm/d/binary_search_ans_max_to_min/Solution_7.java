package com.oycm.algorithm.d.binary_search_ans_max_to_min;

public class Solution_7 {

    /**
     * 2528. <a href="https://leetcode.cn/problems/maximize-the-minimum-powered-city/description/">最大化城市的最小电量</a> 2236
     *
     * @param stations 表示第 i 座城市的供电站数目
     * @param r        供电站供电范围 |i-j| <= r, 0 <= j <= n-1
     * @param k        额外建造 k 座供电站
     * @return 返回所有城市中，最小电量的最大值是多少
     */
    public long maxPower(int[] stations, int r, int k) {
        /*
        一座城市的 电量 是所有能给它供电的供电站数目
         */
        /*
        二分答案
         */
        int n = stations.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stations[i];
        }

        // 初始电量 power[i] = stations[i-r] + ... + stations[i+r]; i-r 最小为 0; i+r 最大为 n
        long[] power = new long[n];
        long mn = Long.MAX_VALUE; // 最小电量
        for (int i = 0; i < n; i++) {
            power[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
            mn = Math.min(mn, power[i]);
        }

        // 开区间二分
        long left = mn + k / n;
        long right = mn + k + 1;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, power, r, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(long low, long[] power, int r, int k) {
        int n = power.length;
        // 差分数组, n + 1, 因为差分数组还原需要 -m,  n-1 会访问到, 不好判断有的超出边界的情况
        long[] diff = new long[n + 1];
        // 差分数组和
        long dSum = 0;
        long built = 0;
        for (int i = 0; i < n; i++) {
            dSum += diff[i]; // 累加差分值
            long m = low - (power[i] + dSum);
            if (m <= 0) {
                continue;
            }
            // 左边符合要求, 当前 i 不符合要求, 在 i + r 建供电站, [i, i + 2r] 增加 m
            built += m;
            if (built > k) { // 不满足要求
                return false;
            }
            // 把区间 [i, i+2r] 增加 m
            // i 已经访问过了 后面不会再访问, 所以 + m
            dSum += m;
            diff[Math.min(i + r * 2 + 1, n)] -= m;
        }
        return true;
    }

}
