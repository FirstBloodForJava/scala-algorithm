package com.oycm.week.lc2026.No517;

public class Solution_2 {

    /**
     * 解码值之和
     *
     * @param nums
     * @return
     */
    public int sumDecoded(long[] nums) {
        /*
        给你一个整数数组 nums。
        每个 nums[i] 都是一个 编码后的 整数，表示两个正整数 xi 和 yi。要解码 nums[i]，定义：
            widthi = nums[i] % 10。
            di = floor(nums[i] / 10)。
            xi 为由 di 的十进制表示中前 widthi 位数字组成的整数。
            yi 为由 di 的十进制表示中剩余所有数字组成的整数。
        保证 di 的十进制表示包含的数字位数大于 widthi。因此，xi 和 yi 都至少包含一位数字。
        nums[i] 的 解码值 为 xi^yi。
        返回 nums 中所有元素的解码值之和，并对 109 + 7 取模。
        floor() 函数返回除法结果的整数部分。
         */
        /*
        快速幂
         */
        long ans = 0;
        for (long x : nums) {
            int w = (int) (x % 10);
            // w 至多为 d 的前 9 位
            long d = x / 10;
            /*
            l = d 总长度 - w
            y = d % 10^l
            x = d / 10^l
             */
            long pow10 = pow10(10, String.valueOf(d).length() - w);
            long a = d / pow10;
            int b = (int) (d % pow10);
            ans = (ans + pow(a, b)) % MOD;
        }
        return (int) ans;
    }

    private static final int MOD = 1000000007;

    // 快速幂
    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    private long pow10(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x;
            }
            x = x * x;
        }
        return res;
    }

}
