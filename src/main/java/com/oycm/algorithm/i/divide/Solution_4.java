package com.oycm.algorithm.i.divide;

public class Solution_4 {

    /**
     * 1849. <a href="https://leetcode.cn/problems/splitting-a-string-into-descending-consecutive-values/description/">将字符串拆分为递减的连续值</a> 1747
     *
     * @param s s.length [1, 20]
     * @return 判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1
     */
    public boolean splitString(String s) {
        /*
        题解思路: 暴力的做法
        确定第一个数，判断后面是否存在符合条件的第二个，第三个 ...
         */
        int n = s.length();
        outer:
        for (int i = 1; i < n; i++) {
            long pre = Long.parseLong(s.substring(0, i));
            // 长度超过一半
            if (pre > Long.MAX_VALUE / 2) break;
            pre--;
            String t = s.substring(i);
            while (!t.isEmpty()) {
                // 去掉前导 0，保留至少一位数字
                while (t.length() > 1 && t.charAt(0) == '0') {
                    t = t.substring(1);
                }
                String temp = String.valueOf(pre);
                if (!t.startsWith(temp)) {
                    continue outer;
                }
                t = t.substring(temp.length());
                pre--;
            }
            // 能到这，说明符合要求
            return true;
        }
        return false;
    }

    public boolean dfs(int i, String s, long pre, int cnt) {
        // 分割完成
        if (i == s.length()) {
            return cnt >= 2;
        }
        long t = 0;
        for (int j = i; j < s.length(); j++) {
            // 如果 t 溢出了，有可能出现错误答案
            t = t * 10 + (s.charAt(j) - '0');
            if (t / 10 > Integer.MAX_VALUE) {
                // 20 位 11 不可能有
                break;
            }
            // 第一个数, 或满足分割条件
            if (pre == -1 || pre - t == 1) {
                if (dfs(j + 1, s, t, cnt + 1)) {
                    return true;
                }
            }
            if (pre != -1 && t >= pre) {
                break;
            }
        }
        return false;
    }
}
