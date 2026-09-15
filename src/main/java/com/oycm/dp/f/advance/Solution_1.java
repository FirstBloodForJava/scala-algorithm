package com.oycm.dp.f.advance;

public class Solution_1 {

    /**
     * 3628. <a href="https://leetcode.cn/problems/maximum-number-of-subsequences-after-one-inserting/description/">插入一个字母的最大子序列数</a> 1754
     *
     * @param s
     * @return
     */
    public long numOfSubsequences(String s) {
        /*
        给你一个由大写英文字母组成的字符串 s。
        你可以在字符串的 任意 位置（包括字符串的开头或结尾）最多插入一个 大写英文字母。
        返回在 最多插入一个字母 后，字符串中可以形成的 "LCT" 子序列的 最大 数量。
        子序列 是从另一个字符串中删除某些字符（可以不删除）且不改变剩余字符顺序后得到的一个 非空 字符串。
         */
        /*
        分类讨论：
        插入 L 字符，最左边插入最优，计算 s 的子序列 CT 出现的次数
        插入 T 字符，最右边插入最优，计算 s 的子序列 LC 出现的次数
        插入 C 前后缀分解计算（乘法）新形成的子序列数量
         */
        char[] cs = s.toCharArray();
        return Math.max(calcInsertC(cs), Math.max(numDistinct(cs, "CT"), numDistinct(cs, "LC"))) + numDistinct(cs, "LCT");
    }

    public long numDistinct(char[] cs, String t) {
        char[] ts = t.toCharArray();
        if (cs.length < ts.length) {
            return 0;
        }

        int n = cs.length;
        int m = ts.length;
        long[] f = new long[m + 1];
        f[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(i, m - 1); j >= Math.max(0, m - n + i); j--) {
                if (cs[i] == ts[j]) {
                    f[j + 1] += f[j];
                }
            }
        }

        return f[m];
    }

    private long calcInsertC(char[] cs) {
        int cntT = 0;
        for (char c : cs) {
            if (c == 'T') {
                cntT++;
            }
        }
        long ans = 0;
        int cntL = 0;
        for (char c : cs) {
            if (c == 'L') {
                cntL++;
            }
            if (c == 'T') {
                cntT--;
            }
            ans = Math.max(ans, (long) cntL * cntT);
        }


        return ans;
    }

    public long numOfSubsequences_2(String s) {
        /*
        计算 CT, LC, LCT 子序列的 dp 合并到一个循环
         */
        int cntT = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'T') {
                cntT++;
            }
        }
        long l = 0, lc = 0, lct = 0, c = 0, ct = 0, lt = 0;

        for (char x : cs) {
            if (x == 'L') {
                l++;
            } else if (x == 'C') {
                lc += l;
                c++;
            } else if (x == 'T') {
                lct += lc;
                ct += c;
                cntT--;
            }

            lt = Math.max(lt, l * cntT);
        }

        return Math.max(lt, Math.max(ct, lc)) + lct;
    }

}
