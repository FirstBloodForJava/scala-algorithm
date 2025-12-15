package com.oycm.algorithm.h.basic;


public class Solution_6 {

    /**
     * 1342. <a href="https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-to-zero/description/">将数字变成 0 的操作次数</a> 1164
     * <p>
     * num 是偶数，可以把它除以 2，否则减去 1
     *
     * @param num 非负整数
     * @return
     */
    public int numberOfSteps(int num) {
        /*
        循环写法：
            num & 1 == 1 num--;
            num & 1 == 0 num/2;
         */
        int ans = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                num--;
            } else {
                num /= 2;
            }
            ans++;
        }
        return ans;
    }

    public int answer(int num) {
        /*
        num 执行 /2 的次数是 num 的二进制长度 - 1
        num 执行 -1 的次数是 num 中 1 的个数
         */
        return num == 0 ? 0 : Integer.bitCount(num) + 31 - Integer.numberOfLeadingZeros(num);
    }
}
