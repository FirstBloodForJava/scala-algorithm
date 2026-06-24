package com.oycm.week.lc2025.No469;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_1 {

    /**
     * 3697. <a href="https://leetcode.cn/problems/compute-decimal-representation/description/">计算十进制表示</a> 1251
     *
     * @param n
     * @return
     */
    public int[] decimalRepresentation(int n) {
        /*
        给你一个 正整数 n。
        如果一个正整数可以表示为 1 到 9 的单个数字和 10 的非负整数次幂的乘积，则称这个整数是一个 10 进制分量。例如，500、30 和 7 是 10 进制分量 ，而 537、102 和 11 则不是。
        请将 n 表示为若干 仅由 10 进制分量组成的和，且使用的 10 进制分量个数 最少。
        返回一个包含这些 10 进制分量 的数组，并按分量大小 降序 排列。
         */
        /*
        n % 10 != 0, 拆分出这个数，再反转结果
         */
        List<Integer> ans = new ArrayList<>();
        int k = 1;
        while (n > 0) {
            int mod = n % 10;
            if (mod != 0) {
                ans.add(mod * k);
            }
            k *= 10;
            n /= 10;
        }
        Collections.reverse(ans);
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

}
