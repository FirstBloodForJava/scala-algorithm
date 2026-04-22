package com.oycm.algorithm.h.basic;


import java.util.ArrayList;
import java.util.List;

public class Solution_20 {

    /**
     * 3211. <a href="https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/description/">生成不含相邻零的二进制字符串</a> 1353
     * <p>
     * 有效字符串：一个二进制字符串 x 的所有长度为 2 的子字符串中包含至少一个 1，x 则是有效字符串
     *
     * @param n
     * @return
     */
    public List<String> validStrings(int n) {
        /*
        字符串中连续 0 的数量不能 大于等于 2
         */
        /*
        [0, 1 << n] 数字对应的二进制，有效位连续零小于等于 1
        把里面对应的数字取反，记为 x, 看 x 是否有连续 1， 是否等于 0，等于 0 则没有连续 1
        可以枚举取反后的值，如果 (x & (x > 1)) == 0
         */
        List<String> ans = new ArrayList<>();
        int mask = (1 << n) - 1;
        for (int x = 0; x < (1 << n); x++) {
            if ((x & (x >> 1)) == 0) {
                int i = x ^ mask;
                ans.add(Integer.toBinaryString((1 << n) | i).substring(1));
            }
        }
        return ans;
    }


}
