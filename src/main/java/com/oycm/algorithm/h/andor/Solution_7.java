package com.oycm.algorithm.h.andor;

public class Solution_7 {

    /**
     * 3133. <a href="https://leetcode.cn/problems/minimum-array-end/description/">数组最后一个元素的最小值</a> 1935
     * <p>
     * 构建长为 n 的升序数组, 数组的与和 == x
     *
     * @param n >= 1
     * @param x 10^8
     * @return 求 nums[n-1] 的最小值
     */
    public long minEnd(int n, int x) {
        /*
        与和 不会超过最小值, 所以 nums[0] == x
        nums[1] & x == x 的最小值
        nums[2] & nums[1] == x 的最小值
        直到 nums[n-1]
        怎么快速求 > x 的最小值
        x 对应的 二进制最低位 变成1，次低位变成 1低位变成 0
        怎么填空位
        题解：怎么填空位
        100100
        n = 5,  = 100 就是把 100 填入 x 非1位置
         */
        n--;
        long ans = x;
        int i = 0, j = 0;
        while (n >> j > 0) {
            // 填入位是 0
            if ((ans >> i & 1) == 0) {
                ans |= (long) (n >> j & 1) << i;
                j++;
            }
            i++;
        }

        return ans;
    }
}
