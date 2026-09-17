package com.oycm.dp.d.lcs.advance;

public class Solution_8 {

    /**
     * 44. <a href="https://leetcode.cn/problems/wildcard-matching/description/">通配符匹配</a>
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        /*
        给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
            '?' 可以匹配任何单个字符。
            '*' 可以匹配任意字符序列（包括空字符序列）。
        判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
         */
        /*
        f[i][j] 表示 s 的前 i 个字符串和模式 p 的前 j 个字符是否能匹配。
         */
        int n = s.length();
        int m = p.length();
        boolean[] f = new boolean[m + 1];

        char[] cs = s.toCharArray();
        char[] ps = p.toCharArray();
        // 递归边界条件
        f[0] = true;
        for (int j = 1; j <= m; j++) {
            if (ps[j - 1] == '*') {
                f[j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            boolean pre = f[0];
            f[0] = false;

            for (int j = 1; j <= m; j++) {
                boolean tmp = f[j];
                if (ps[j - 1] == '*') {
                    // * 不占用匹配 f[i+1][j]
                    // * 匹配 f[i][j+1](多次使用), f[i][j](第一次使用)
                    // f[j] 依赖前面 f[i-1][j], 需要正序遍历
                    f[j] = f[j - 1] || f[j];
                } else if (ps[j - 1] == '?' || cs[i - 1] == ps[j - 1]) {
                    f[j] = pre;
                } else {
                    f[j] = false;
                }
                pre = tmp;
            }
        }
        return f[m];
    }


}
