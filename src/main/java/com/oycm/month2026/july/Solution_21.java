package com.oycm.month2026.july;

public class Solution_21 {

    /**
     * 3499. <a href="https://leetcode.cn/problems/maximize-active-section-with-trade-i/description/">操作后最大活跃区段数 I</a> 1729
     *
     * @param s
     * @return
     */
    public int maxActiveSectionsAfterTrade(String s) {
        /*
        给你一个长度为 n 的二进制字符串 s，其中：
            '1' 表示一个 活跃 区段。
            '0' 表示一个 非活跃 区段。
        你可以执行 最多一次操作 来最大化 s 中的活跃区段数量。在一次操作中，你可以：
            将一个被 '0' 包围的连续 '1' 区块转换为全 '0'。
            然后，将一个被 '1' 包围的连续 '0' 区块转换为全 '1'。
        返回在执行最优操作后，s 中的 最大 活跃区段数。
        注意：处理时需要在 s 的两侧加上 '1' ，即 t = '1' + s + '1'。这些加上的 '1' 不会影响最终的计数。
         */
        /*
        关键点：求的是 s 中 1 的个数，不一定需要连续。
        找到一个 0+1+0+ 模式最长字符串。该长度减去 1 再加上所有 1 的个数就是操作后的 1 的数量。
         */
        char[] cs = s.toCharArray();
        int total1 = 0;
        // 010 模式串前面长度，MIN_VALUE 表示模式串前面没有 0 的情况
        int pre0 = Integer.MIN_VALUE;
        // 连续 1/0 的个数
        int cnt = 0;
        // 010 模式串中 0 的个数
        int mx = 0;
        for (int i = 0; i < cs.length; i++) {
            cnt++;
            // 不匹配时，一定时 0/1 或 1/0 交替出现
            if (i == cs.length - 1 || cs[i] != cs[i + 1]) {
                if (cs[i] == '1') {
                    // 出现 1/0，前面如果有出现 0，那么 pre0 就是前面出现 0 的数量，否则还是最小值
                    total1 += cnt;
                } else {
                    // 出现 0/1，前面如果出现模式串，更新 mx，否则只更新 pre0 数量
                    mx = Math.max(mx, pre0 + cnt);
                    pre0 = cnt;
                }
                cnt = 0;
            }
        }
        return total1 + mx;
    }

}
