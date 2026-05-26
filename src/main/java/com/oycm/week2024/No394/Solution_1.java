package com.oycm.week2024.No394;

public class Solution_1 {

    /**
     * 3120. <a href="https://leetcode.cn/problems/count-the-number-of-special-characters-i/description/">统计特殊字母的数量 I</a> 1206
     *
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        /*
        给你一个字符串 word。如果 word 中同时存在某个字母的小写形式和大写形式，则称这个字母为 特殊字母。
        返回 word 中 特殊字母 的数量。
         */
        /*
        位运算解决：
        大小写字母对应 ASCII 值特性：
            大写字母从右往左第 6 个比特一定是 0；
            小写字母从右往左第 6 个比特一定是 1；
            大小写字母的低 5 位二进制一样
         */
        int[] cnt = new int[2];

        for (char c : word.toCharArray()) {
            cnt[c >> 5 & 1] |= 1 << (c & 31);
        }

        return Integer.bitCount(cnt[0] & cnt[1]);
    }

}
