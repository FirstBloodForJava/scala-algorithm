package com.oycm.week.lc2022.No274;

public class Solution_2 {

    /**
     * 2125. <a href="https://leetcode.cn/problems/number-of-laser-beams-in-a-bank/description/">银行中的激光束数量</a> 1280
     *
     * @param bank
     * @return
     */
    public int numberOfBeams(String[] bank) {
        /*
        银行内部的防盗安全装置已经激活。给你一个下标从 0 开始的二进制字符串数组 bank ，表示银行的平面图，这是一个大小为 m x n 的二维矩阵。
        bank[i] 表示第 i 行的设备分布，由若干 '0' 和若干 '1' 组成。'0' 表示单元格是空的，而 '1' 表示单元格有一个安全设备。
        对任意两个安全设备而言，如果同时 满足下面两个条件，则二者之间存在 一个 激光束：
            两个设备位于两个 不同行 ：r1 和 r2 ，其中 r1 < r2 。
            满足 r1 < i < r2 的 所有 行 i ，都 没有安全设备 。
         */
        /*
        统计前一行 安全设备的数量，如果当前行 安全设备数量大于 0，则 ans += pre * cur, pre = cur，否则 pre 不变
         */
        int ans = 0;
        int pre = 0;
        for (String s : bank) {
            int cur = 0;
            for (char c : s.toCharArray()) {
                cur += c & 1;
            }
            if (cur > 0) {
                ans += pre * cur;
                pre = cur;
            }
        }

        return ans;
    }

}
