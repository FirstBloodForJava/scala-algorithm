package com.oycm.string.hash;

public class Solution_6 {

    /**
     * 3722. 反转后字典序最小的字符串
     * <br>
     * 3722. <a href="https://leetcode.cn/problems/lexicographically-smallest-string-after-reverse/description/">反转后字典序最小的字符串</a> 1414
     *
     * @param s
     * @return
     */
    public String lexSmallest(String s) {
        /*
        给你一个由小写英文字母组成的、长度为 n 的字符串 s。
        你 必须执行 恰好 一次操作：选择一个整数 k，满足 1 <= k <= n，然后执行以下两个选项之一：
            反转 s 的 前 k 个字符，或
            反转 s 的 后 k 个字符。
        返回在 恰好 执行一次此类操作后可以获得的 字典序最小 的字符串。
         */
        /*
        k = 1，执行一次就是自己
        暴力做法：枚举 k [2, n]，反转前后 k 个字符，如果比 答案小，则更新。 n^2
         */
        String ans = s;
        int n = s.length();
        for (int k = 2; k <= n; k++) {
            String preRev = new StringBuilder(s.substring(0, k)).reverse() + s.substring(k, n);
            if (preRev.compareTo(ans) < 0) ans = preRev;
            String sufRev = s.substring(0, n - k) + new StringBuilder(s.substring(n - k)).reverse();
            if (sufRev.compareTo(ans) < 0) ans = sufRev;
        }

        return ans;
    }

}
