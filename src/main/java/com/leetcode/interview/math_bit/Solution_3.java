package com.leetcode.interview.math_bit;

import java.util.HashMap;
import java.util.Map;

public class Solution_3 {

    /**
     * 分数到小数
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        /*
        给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
        如果小数部分为循环小数，则将循环的部分括在括号内。
        如果存在多个答案，只需返回 任意一个 。
        对于所有给定的输入，保证 答案字符串的长度小于 1e4 。
        注意，如果分数可以表示为有限长度的字符串，则 必须 返回它。
         */
        /*
        -2^31 <= numerator, denominator <= 2^31 - 1
         */
        /*
        模拟除法，当出现余数相同时，说明后面的就是循环小数
         */

        long x = numerator;
        long y = denominator;
        if (x % y == 0) return String.valueOf(x / y);
        StringBuilder ans = new StringBuilder(x < 0 ^ y < 0 ? "-" : "");
        x = Math.abs(x);
        y = Math.abs(y);
        ans.append(x / y).append(".");
        // 余数
        x %= y;
        // key 余数，
        Map<Long,Integer> map = new HashMap<>();
        while (x != 0) {
            map.put(x, ans.length());
            x *= 10;
            ans.append(x / y);
            x %= y;
            if (map.containsKey(x)) {
                int idx = map.get(x);
                return ans.substring(0, idx) + "(" + ans.substring(idx) + ")";
            }
        }
        return ans.toString();
    }


}
