package com.oycm.algorithm.h.basic;


public class Solution_9 {

    /**
     * 868. <a href="https://leetcode.cn/problems/binary-gap/description/">二进制间距</a> 1307
     * <p>
     * 相邻: 只有 0 将两个 1 分隔开（可能不存在 0 ）
     *
     * @param n
     * @return 求 n 的二进制表示中两个 相邻 1 之间的 最长距离
     */
    public int binaryGap(int n) {
        /*
        二进制中 附近 1 之间的最长距离
         */
        if (Integer.bitCount(n) == 1) {
            return 0;
        }
        int ans = 0;
        int distance = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                distance = 1;
            } else if (distance > 0) {
                distance++;
            }
            n = n >> 1;
            ans = Math.max(ans, distance);
        }
        return ans;
    }
}
