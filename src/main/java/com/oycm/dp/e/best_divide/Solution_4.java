package com.oycm.dp.e.best_divide;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 2767. <a href="https://leetcode.cn/problems/partition-string-into-minimum-beautiful-substrings/description/">将字符串分割为最少的美丽子字符串</a> 1865
     *
     * @param s
     * @return
     */
    public int minimumBeautifulSubstrings(String s) {
        /*
        给你一个二进制字符串 s ，你需要将字符串分割成一个或者多个 子字符串  ，使每个子字符串都是 美丽 的。
        如果一个字符串满足以下条件，我们称它是 美丽 的：
            它不包含前导 0 。
            它是 5 的幂的 二进制 表示。
        请你返回分割后的子字符串的 最少 数目。如果无法将字符串 s 分割成美丽子字符串，请你返回 -1。
        子字符串是一个字符串中一段连续的字符序列。
         */
        /*
        字符串很短，可以字符串转数组判断是否为 5 的幂
        dfs(i) [0, i] 最优分割法案数
            [0, i] 是 5 的幂 返回 0
            dfs(l) + [l+1, i] s[l+1] 是 1，且为 5 的幂，l [0, i-1] min
        怎么快速判断 val 是否为 5 的幂次？ 2^15 最大 5 的幂是多少 15625，判断 val 是 15625 的因子
        优化思路：
        倒序遍历 + hash 表记录 5 的幂次二进制字符串
         */
        init();
        if (s.charAt(0) == '0') return -1;
        int n = s.length();
        // f[i] 表示 [i, n-1] 最优分割数
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            f[i] = n + 1;
            if (s.charAt(i) == '0') {
                continue;
            }
            for (String t : pow5) {
                int len = t.length();
                if (i + len > n) {
                    break;
                }
                if (s.startsWith(t, i)) {
                    f[i] = Math.min(f[i], f[i + len] + 1);
                }
            }
        }

        return f[0] > n ? -1 : f[0];
    }

    public static List<String> pow5 = new ArrayList<>();
    public static boolean initialized = false;

    public void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i < 1 << 15; i *= 5) {
            pow5.add(Integer.toBinaryString(i));
        }
    }

}
