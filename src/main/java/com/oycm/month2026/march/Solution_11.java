package com.oycm.month2026.march;

public class Solution_11 {

    /**
     * 1009. <a href="https://leetcode.cn/problems/complement-of-base-10-integer/description/">十进制整数的反码</a> 1235
     *
     * @param n
     * @return
     */
    public int bitwiseComplement(int n) {
        /*
        0 特殊处理
        找到 n 的左边最高位移动后 - 1, 再异或取反
         */
        return n == 0 ? 1 : n ^ ((1 << 32 - Integer.numberOfLeadingZeros(n)) - 1);
    }

}
