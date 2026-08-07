package com.oycm.month2026.august;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_7 {

    /**
     * 3348. <a href="https://leetcode.cn/problems/smallest-divisible-digit-product-ii/description/">最小可整除数位乘积 II</a> 3101
     *
     * @param num
     * @param t
     * @return
     */
    public String smallestNumber(String num, long t) {
        /*
        给你一个字符串 num ，表示一个 正 整数，同时给你一个整数 t 。
        如果一个整数 没有 任何数位是 0 ，那么我们称这个整数是 无零 数字。
        请你返回一个字符串，这个字符串对应的整数是大于等于 num 的 最小无零 整数，且 各数位之积 能被 t 整除。
        如果不存在这样的数字，请你返回 "-1" 。
         */
        long tmp = t;
        // t 的质因子个数
        int cnt = 0;
        // 数位范围只有 [1, 9] 可选，其中质因子只有 2,3,5,7，如果 t 中包含其它质因子，则答案一定不存在。
        for (int p : new int[]{2, 3, 5, 7}) {
            while (tmp % p == 0) {
                tmp /= p;
                cnt++;
            }
        }
        if (tmp > 1) {
            return "-1";
        }

        /*
        补前导零（至少一个）
            补 1 的情况，num = 999，t = 2；
            质因子数量比 num 长，也要补前导 0，这里为什么还要加 1？能否去掉这个 + 1？
            可以去掉因为如果 cnt < n，就算 num = 999 这种最大整数，由于 和 1 去了 max，所以这个长度肯定可以找到符合要求的答案。
         */
        cnt = Math.max(cnt - num.length(), 1);
        num = "0".repeat(cnt) + num;

        int n = num.length();
        char[] ans = new char[n];
        Arrays.fill(ans, '0');

        Set<Long>[] vis = new HashSet[n];
        Arrays.setAll(vis, i -> new HashSet<>());

        dfs(0, t, true, cnt, num.toCharArray(), ans, vis);
        for (int i = 0; ; i++) {
            if (ans[i] != '0') {
                return new String(ans, i, n - i); // 去掉前导零
            }
        }
    }

    private boolean dfs(int i, long t, boolean isLimit, int cnt, char[] s, char[] ans, Set<Long>[] vis) {
        if (i == s.length) {
            return t == 1;
        }
        if (!isLimit && !vis[i].add(t)) {
            return false;
        }
        /*
        n = max(n + 1, cnt)
        这里为什么要加 i < cnt 的判断？ cnt 表示补的前导 0 数量， [0, cnt) 可以填 0
         */
        if (isLimit && i < cnt && dfs(i + 1, t, true, cnt, s, ans, vis)) {
            return true;
        }

        int low = isLimit ? s[i] - '0' : 0;
        for (int d = Math.max(low, 1); d <= 9; d++) {
            if (dfs(i + 1, t / gcd(t, d), isLimit && d == low, cnt, s, ans, vis)) {
                ans[i] = (char) ('0' + d);
                return true;
            }
        }
        return false;
    }

    private long gcd(long a, long b) {
        while (a != 0) {
            long tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
    

}
