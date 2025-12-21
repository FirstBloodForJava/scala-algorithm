package com.oycm.algorithm.h.mind;

public class Solution_1 {

    /**
     * 2546. <a href="https://leetcode.cn/problems/apply-bitwise-operations-to-make-strings-equal/description/">执行逐位运算使字符串相等</a> 1605
     *
     * 进行下面操作任意次，使字符串 s 和 target 相等
     * 不同下标 i, j
     * 将 s[i] 变成 s[i] or s[j], s[j] = s[i] ^ s[j]
     * @param s
     * @param target
     * @return
     */
    public boolean makeStringsEqual(String s, String target) {
        /*
        题解思路:
            选 0 和 1 可以把 0 变成 1
            选 1 和 1 可以把 1 变成 0
        只要都至少有一个 1, s 和 target 就可以互转
        都是 0 的情况下，只能都一样
         */
        return s.contains("1") == target.contains("1");
    }

}
