package com.leetcode.interview_question.e;

public class Solution_7 {

    /**
     * 面试题 05.07. <a href="https://leetcode.cn/problems/exchange-lcci/description/">配对交换</a>
     *
     * @param num
     * @return
     */
    public int exchangeBits(int num) {
        /*
        配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位 0 与位 1 交换，位 2 与位 3 交换，以此类推）。
         */
        /*
        偶数下标位左移一位
        奇数下标位右移一位
        能不能快速找出 num 的 奇数位值/偶数位值 的值，num - 奇数位值/偶数位值 = 偶数位值/奇数位值
        1010
         */
        int x = 0xaaaaaaaa;
        // 奇数位值
        int odd = num & x;
        // 偶数位值
        int even = num - odd;
        return (even << 1) | (odd >> 1);
    }

}
