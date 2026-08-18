package com.oycm.string.manacher;

public class SolutionExtend_2 {

    /**
     * 3844. <a href="https://leetcode.cn/problems/longest-almost-palindromic-substring/description/">最长的准回文子字符串</a> 1990
     *
     * @param s
     * @return
     */
    public int almostPalindromic(String s) {
        /*
        给你一个由小写英文字母组成的字符串 s。
        如果一个子字符串在删除 恰好 一个字符后变成回文字符串，那么这个子字符串就是 准回文串（almost-palindromic）。
        返回一个整数，表示字符串 s 中最长的 准回文串 的长度。
        子字符串是字符串中任意连续的、非空 字符序列。
        回文串是一个 非空 字符串，正着读和反着读都相同。
         */
        /*
        中心扩展法：枚举以 i 或 i 和 i+1 为 中心，当不匹配时，删除一个字符能得到的最长回文串。
        删除左边，删除右边，两者取最大值。
        在不匹配之前执行删除操作，会得到更优的结果吗？
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2;
            int r = (i + 1) / 2;
            while (l >= 0 && r < n && cs[l] == cs[r]) {
                l--;
                r++;
            }
            ans = Math.max(ans, expand(cs, l - 1, r));
            ans = Math.max(ans, expand(cs, l, r + 1));

        }
        // 当整个字符串时回文串时，不加判断，上面的结果会是 n+1
        return Math.min(ans, n);
    }

    private int expand(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
            l--;
            r++;
        }
        // (l, r) 才是回文串 r - l + 1 - 2
        return r - l - 1;
    }

}
