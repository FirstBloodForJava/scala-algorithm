package com.oycm.algorithm.h.mind;


public class Solution_3 {

    /**
     * 2571. <a href="https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0/description/">将整数减少到零需要的最少操作数</a> 1649
     *
     * @param n 正整数
     * @return 将 n 加上或减去 2 某个幂(2^i, i>=0), n 变成 0 的最少操作次数
     */
    public static int minOperations(int n) {
        /*
        100111 + 000001 = 101000
        n + (1 << lowbit) 产生新的 lowbit 差 大于 1, 则 可以继续 和 新的 lowbit 继续删除

         */
        int ans = 0;
        while (n > 0) {
            int lowbit = n & -n;
            int temp = n + lowbit;
            int newLowbit = temp & -temp;
            if (Integer.numberOfLeadingZeros(lowbit) - Integer.numberOfLeadingZeros(newLowbit) > 1) {
                n = temp;
            } else {
                n = n ^ lowbit;
            }
            ans++;
        }


        return ans;
    }

    public static void main(String[] args) {
        System.out.println(31 - Integer.numberOfLeadingZeros(39 & -39));
        System.out.println(minOperations(39));
    }
}
