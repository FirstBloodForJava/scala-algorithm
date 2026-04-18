package com.oycm.month2026.april;

public class Solution_18 {

    /**
     * 3783. <a href="https://leetcode.cn/problems/mirror-distance-of-an-integer/description/">整数的镜像距离</a> 1171
     *
     * @param n
     * @return
     */
    public int mirrorDistance(int n) {
        /*
         镜像距离 为：abs(n - reverse(n))，其中 reverse(n) 表示将 n 的数字反转后形成的整数
         */
        /*
        反转数字
         */
        int ans = n;
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }

        return Math.abs(ans - rev);
    }

}
