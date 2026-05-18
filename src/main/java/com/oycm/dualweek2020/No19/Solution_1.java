package com.oycm.dualweek2020.No19;

public class Solution_1 {

    /**
     * 1342. <a href="https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-to-zero/description/">将数字变成 0 的操作次数</a> 1164
     *
     * @param num [0, 1e6]
     * @return
     */
    public int numberOfSteps(int num) {
        /*
        给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
         */
        /*
        位运算思路：考虑会执行多少次除法操作，执行多少次减法操作
        14 = 1110
        除法操作，就是二进制整体右移一位，最高位的 1 要移动到最低位，需要移动 L - 1 次，L 表示二进制的长度
        减法操作，二进制的 1 不能直接去掉，多少个 1 多少个减法操作
        次数 = L - 1 + 二进制中 1 的个数
        numberOfLeadingZeros 计算前导 0 的个数 L = 32 - numberOfLeadingZeros
        num 可能为 0，需特殊处理
         */

        return num == 0 ? 0 : 31 - Integer.numberOfLeadingZeros(num) + Integer.bitCount(num);
    }

}
