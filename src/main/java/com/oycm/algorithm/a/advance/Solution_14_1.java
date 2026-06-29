package com.oycm.algorithm.a.advance;

public class Solution_14_1 {

    /**
     * 2156. <a href="https://leetcode.cn/problems/find-substring-with-given-hash-value/description/">查找给定哈希值的子串</a>
     *
     * @param s
     * @param power
     * @param modulo
     * @param k
     * @param hashValue
     * @return
     */
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        /*
        给定整数 p 和 m ，一个长度为 k 且下标从 0 开始的字符串 s 的哈希值按照如下函数计算：
            hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m
        val(s[i]) 表示 s[i] 在字母表中的下标，从 val('a') = 1 到 val('z') = 26。
        给你一个字符串 s 和整数 power，modulo，k 和 hashValue 。请你返回 s 中 第一个 长度为 k 的 子串 sub ，满足 hash(sub, power, modulo) == hashValue。
         */
        /*
        倒序遍历，窗口形成时，去掉最后一个字符串
         */
        int ansIdx = 0;
        char[] cs = s.toCharArray();
        int n = cs.length;
        long pk = pow(power, k - 1, modulo);
        long hash = 0;
        for (int i = n - 1; i >= 0; i--) {
            hash = (hash * power + (cs[i] & 31)) % modulo;
            int right = k + i - 1;
            if (right >= n) continue;
            // 更新左端点
            if (hash == hashValue) ansIdx = i;

            // 移除右端点
            hash = (hash - (cs[right] & 31) * pk % modulo + modulo) % modulo;

        }

        return s.substring(ansIdx, ansIdx + k);
    }

    private long pow(long x, int n, int mod) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }

        return res;
    }

}
