package com.oycm.dualweek2022.No71;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 2160. <a href="https://leetcode.cn/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/">拆分数位后四位数字的最小和</a> 1314
     *
     * @param num
     * @return
     */
    public int minimumSum(int num) {
        /*
        给你一个四位 正 整数 num 。请你使用 num 中的 数位 ，将 num 拆成两个新的整数 new1 和 new2。
        new1 和 new2 中可以有 前导 0 ，且 num 中 所有 数位都必须使用。
        比方说，给你 num = 2932 ，你拥有的数位包括：两个 2 ，一个 9 和一个 3 。一些可能的 [new1, new2] 数对为 [22, 93]，[23, 92]，[223, 9] 和 [2, 329] 。
        请你返回可以得到的 new1 和 new2 的 最小 和。
         */
        /*
        两个数一组，两个最小值作为开始
         */
        int[] a = new int[4];
        for (int i = 0; num != 0; i++, num /= 10) {
            a[i] = num % 10;
        }
        Arrays.sort(a);
        return (a[0] + a[1]) * 10 + a[2] + a[3];
    }

}
