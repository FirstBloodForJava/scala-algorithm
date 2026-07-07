package com.oycm.month2026.july;

public class Solution_7 {

    /**
     * 3754. 连接非零数字并乘以其数字和 I
     * <br>
     * 3754. <a href="https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/description/">连接非零数字并乘以其数字和 I</a> 1248
     *
     * @param n
     * @return
     */
    public long sumAndMultiply(int n) {
        /*
        给你一个整数 n。
        将 n 中所有的 非零数字 按照它们的原始顺序连接起来，形成一个新的整数 x。如果不存在 非零数字 ，则 x = 0。
        sum 为 x 中所有数字的 数字和 。
        返回一个整数，表示 x * sum 的值。
         */
        /*
        %10, /10 是倒序枚举数位，要遍历两次才能知道新的整数 x。
        可以在遍历的过程中记录右边数字情况 k，当前位置 d * pow(10, k) 就是 d 在 x 此时的值
         */
        int x = 0;
        int sum = 0;
        for (int pow10 = 1; n > 0; n /= 10) {
            int d = n % 10;
            if (d > 0) {
                x += pow10 * d;
                sum += d;
                pow10 *= 10;
            }
        }
        return (long) x * sum;
    }

}
