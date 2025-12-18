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

    public long answer(int n, int x) {
        /*
        题解思路: 需要从 x 的最低位 0 开始填, 可以将 x 反转, 找 x 的最低位的 1 填 n 的比特
        x = 100100, ~x = 011011, n = 6, n-- = 101
        lowbit = 1,
        只要 n 的第 j 个比特是 1 就 加上 lowbit,可以使用 乘法代替 if else
         */
        n--;
        long ans = x;
        long rev = ~ans;
        long lowbit = rev & -rev;
        int j = 0;
        while (n >> j > 0) {
            ans |= ((n >> j & 1) * lowbit);
            // 去掉 lowbit
            rev ^= lowbit;
            // 更新 lowbit
            lowbit = rev & -rev;
            j++;
        }

        return ans;
    }
}
