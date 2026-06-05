package com.oycm.dp.j.count_num;

public class Solution_1 {

    /**
     * 3747. <a href="https://leetcode.cn/problems/count-distinct-integers-after-removing-zeros/description/">统计移除零后不同整数的数目</a> 1848
     *
     * @param n
     * @return
     */
    public long countDistinct(long n) {
        /*
        给你一个 正 整数 n。
        对于从 1 到 n 的每个整数 x，我们记下通过移除 x 的十进制表示中的所有零而得到的整数。
        返回一个整数，表示记下的 不同 整数的数量。
         */
        /*
        1 <= n <= 1e15
         */
        /*
        1230 去掉 0 得到 123 这个数看到在前面出现过，且包含 0，本质是统计 [1, n] 不含 0 的个数；
        长为 1 不含 0 的数字有 9 个，长为 2 不含 0 的数字有 9*9 个
        长为 [1, m-1] 的数字共有 9 + 9^2 + ... + 9^(m-1)
        等比数列求和：(9^m - 9)/8
        从数字高位遍历到低位
            最高位
        */
        char[] cs = String.valueOf(n).toCharArray();
        int m = cs.length;
        long pow = (long) Math.pow(9, m);
        long ans = (pow - 9) / 8;

        for (int i = 0; i < m && cs[i] > '0'; i++) {
            int d = cs[i] - '1';
            pow /= 9;
            if (i == m-1) {
                d++;
            }
            ans += d * pow;
        }

        return ans;
    }


}
