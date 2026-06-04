package com.oycm.month2026.june;

public class Solution_4 {

    /**
     * 3751. <a href="https://leetcode.cn/problems/total-waviness-of-numbers-in-range-i/description/">范围内总波动值 I</a> 1404
     *
     * @param num1
     * @param num2
     * @return
     */
    public int totalWaviness(int num1, int num2) {
        /*
        给你两个整数 num1 和 num2，表示一个 闭 区间 [num1, num2]。
        一个数字的 波动值 定义为该数字中 峰 和 谷 的总数：
            如果一个数位 严格大于 其两个相邻数位，则该数位为 峰。
            如果一个数位 严格小于 其两个相邻数位，则该数位为 谷。
            数字的第一个和最后一个数位 不能 是峰或谷。
            任何少于 3 位的数字，其波动值均为 0。
        返回范围 [num1, num2] 内所有数字的波动值之和。
         */
        /*
        1 <= num1 <= num2 <= 1e5
         */
        /*
        预处理 [1, 1e5] 范围中的波动值
         */
        init();
        return f[num2 + 1] - f[num1];
    }

    public static int[] f = new int[100001];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int x = 101; x < f.length - 1; x++) {
            f[x + 1] += f[x] + getWaviness(x);
        }

    }

    public int getWaviness(int k) {
        int cnt = 0;
        int pre = -1, cur = -1, next = -1;
        while (k > 0) {
            int mod = k % 10;
            k /= 10;
            if (pre == -1) {
                pre = mod;
            } else if (cur == -1) {
                cur = mod;
            } else {
                next = mod;
                if (cur > pre && cur > next || (cur < pre && cur < next)) {
                    cnt++;
                }
                pre = cur;
                cur = next;
            }
        }
        return cnt;
    }
}
