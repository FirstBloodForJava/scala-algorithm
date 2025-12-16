package com.oycm.algorithm.h.xor;

public class Solution_5 {

    /**
     * 2683. <a href="https://leetcode.cn/problems/neighboring-bitwise-xor/description/">相邻值的按位异或</a> 1518
     * <p>
     * derived 数组由同样长为 n 的 original 二进制数组 按以下规则派生而来
     * 如果 i = n - 1 ，那么 derived[i] = original[i] ⊕ original[0];
     * 否则 derived[i] = original[i] ⊕ original[i + 1]
     *
     * @param derived
     * @return  derived 是否由 original 数组派生而来
     */
    public boolean doesValidArrayExist(int[] derived) {
        /*
        derived[0] = original[0] ^ original[1]
        derived[1] = original[1] ^ original[2]
        derived[2] = original[0] ^ original[2]
        依据这个规则 derived 的异或和首先得是 0

         */
        int xor = 0;
        for (int i = 0; i < derived.length; i++) {
            xor ^= derived[i];
        }
        return xor == 0;
    }
}
