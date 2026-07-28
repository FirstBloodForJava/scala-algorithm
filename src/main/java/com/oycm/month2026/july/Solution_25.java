package com.oycm.month2026.july;

public class Solution_25 {

    /**
     * 3536. <a href="https://leetcode.cn/problems/maximum-product-of-two-digits/description/">两个数字的最大乘积</a> 1199
     *
     * @param n
     * @return
     */
    public int maxProduct(int n) {
        /*
        给定一个正整数 n。
        返回 任意两位数字 相乘所得的 最大 乘积。
        注意：如果某个数字在 n 中出现多次，你可以多次使用该数字。
         */
        /*
        从低位到高位，用一个遍历表示比当前位低的最大数字
         */
        int mx = 0;
        int ans = 0;
        while (n > 0) {
            int cur = n % 10;
            ans = Math.max(ans, cur * mx);
            mx = Math.max(mx, cur);
            n /= 10;
        }

        return ans;
    }

}
