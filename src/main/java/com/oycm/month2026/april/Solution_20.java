package com.oycm.month2026.april;

public class Solution_20 {

    /**
     * 2078. <a href="https://leetcode.cn/problems/two-furthest-houses-with-different-colors/description/">两栋颜色不同且距离最远的房子</a> 1241
     *
     * @param colors colors[i] 表示第  i 栋房子的颜色, colors.length [2, 100]; colors[i] [0, 100]; colors[i] 至少有两个不同值
     * @return 返回 两栋 颜色 不同 房子之间的 最大 距离
     */
    public int maxDistance(int[] colors) {
        /*
        记录左边相同颜色的最小值
         */
        /*
        如果 colors[0] != colors[n-1], 则 答案就是 n-1;
        如果 color = colors[0] == colors[n-1]
            右边第一个与 color 不同的下标 r, 与 0 计算距离，没有比它更大了
            左边第一个与 color 不同的下标 l，与 n-1 计算距离，没有比它更大了
        固定 colors[0], 右边出现第一个 colors[r] != colors[0] 的结果一定是左边开始的最大距离
        固定 colors[n-1], 左边出现第一个 colors[l] != colors[n-1] 的结果一定是
         */
        int r = colors.length - 1;
        while (colors[r] == colors[0]) {
            r--;
        }
        int l = 0;
        while (colors[l] == colors[colors.length - 1]) {
            l++;
        }

        return Math.max(r, colors.length - 1 - l);
    }

}
