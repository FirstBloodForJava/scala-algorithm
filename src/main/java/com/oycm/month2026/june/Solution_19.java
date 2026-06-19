package com.oycm.month2026.june;

public class Solution_19 {

    /**
     * 1732. <a href="https://leetcode.cn/problems/find-the-highest-altitude/description/">找到最高海拔</a> 1257
     *
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        /*
        有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。自行车手从海拔为 0 的点 0 开始骑行。
        给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
         */
        int ans = 0;
        int sum = 0;
        for (int x : gain) {
            sum += x;
            ans = Math.max(ans, sum);
        }
        return ans;
    }

}
