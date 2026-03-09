package com.oycm.week.No492;

public class Solution_4 {

    /**
     * 3864. <a href="https://leetcode.cn/problems/minimum-cost-to-partition-a-binary-string/description/">划分二进制字符串的最小费用</a>
     * s[i] = '1' 表示第 i 个元素是敏感的，而 s[i] = '0' 表示它不是敏感的
     * 对于一个长度为 L 且包含 X 个敏感元素的分段:
     * <p>
     * 如果 X = 0，费用为 flatCost
     * <p>
     * 如果 X > 0，费用为 L * X * encCost
     * 一个分段具有 偶数长度，你可以将其拆分为两个长度 相等 的 连续分段，此次拆分的费用是所得分段的 费用之和
     *
     * @param s        二进制字符串
     * @param encCost
     * @param flatCost
     * @return
     */
    public long minCost(String s, int encCost, int flatCost) {
        /*
        题解思路: 暴力分治
        1010,1010
        奇数的分段费用是固定的
         */
        this.encCost = encCost;
        this.flatCost = flatCost;

        int n = s.length();

        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) - '0');
        }

        return dfs(0, n);
    }


    private long encCost;
    private long flatCost;
    private int[] prefix;


    private long dfs(int l, int r) {
        int cnt = prefix[r] - prefix[l];
        int L = r - l;
        long cost = cnt > 0 ? (long) L * cnt * encCost : flatCost;

        // 拆分
        if (L % 2 == 0) {
            int mid = (r + l) / 2;
            cost = Math.min(cost, dfs(l, mid) + dfs(mid, r));
        }
        return cost;
    }

}
