package com.oycm.week.lc2026.No509;

public class Solution_2 {

    /**
     * 3983. 一次替换后的子序列
     * <br>
     * 3983. <a href="https://leetcode.cn/problems/subsequence-after-one-replacement/description/">一次替换后的子序列</a>
     *
     * @param s
     * @param t
     * @return
     */
    public boolean canMakeSubsequence(String s, String t) {
        /*
        给你两个由小写英文字母组成的字符串 s 和 t。
        你最多可以选择 s 中的一个下标，并将该下标处的字符 替换 为任意小写英文字母。
        如果可以使 s 成为 t 的一个 子序列，则返回 true；否则返回 false。
        子序列 是指通过删除另一个字符串中的某些字符或不删除任何字符，并且不改变剩余字符相对顺序后得到的字符串。
         */
        /*
        前后缀分解：
            pre[i] 定义，子串 s[0 : i] 是子串 t[0 : pre[i]] 的子序列。
            suf[i] 定义，子串 s[i : n-1] 是子串 t[suf[i] : m-1] 的子序列。
            枚举修改位置 i [0, n-1]
            如果 (pre[i-1], suf[i+1]) 区间存在字符串，则可以修改 s[i]，使得 s 是 t 的子序列，也就是 suf[i+1] - pre[i-1] > 1;
         */
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        int n = cs.length;
        int m = ts.length;
        int[] suf = new int[n + 1];
        suf[n] = m;
        int j = m;
        for (int i = n - 1; i >= 0; i--) {
            j--;
            while (j >= 0 && ts[j] != cs[i]) {
                j--;
            }
            suf[i] = j;
        }
        // s 是 t 的子序列
        if (suf[0] >= 0) {
            return true;
        }

        int pre = -1;
        for (int i = 0; i < n; i++) {
            // 修改位置 i
            if (suf[i + 1] - pre > 1) return true;

            pre++;
            while (pre < m && ts[pre] != cs[i]) {
                pre++;
            }
        }

        return false;

    }

    public boolean canMakeSubsequence_dp(String s, String t) {
        /*
        状态机 dp
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        // 不修改的情况下，s[0 : j0-1] 是 t 前缀的子序列
        int j0 = 0;
        // 修改的情况下，s[0 : j1-1] 是 t 前缀的子序列
        int j1 = 0;

        for (char c : t.toCharArray()) {
            if (cs[j1] == c) {
                j1++;
            }
            // 修改匹配，不能在 j0 判断之前，最后要有字符才能修改
            j1 = Math.max(j1, j0 + 1);

            if (cs[j0] == c) {
                j0++;
            }

            if (j1 == n) return true;
        }

        return false;
    }

}
