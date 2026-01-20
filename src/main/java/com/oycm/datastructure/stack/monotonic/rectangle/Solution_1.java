package com.oycm.datastructure.stack.monotonic.rectangle;


public class Solution_1 {

    /**
     * 84. <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/description/">柱状图中最大的矩形</a>
     *
     * @param heights 长度为 n 的数组, n 是正整数, 每根柱子彼此相邻, 且宽度为 1
     * @return 在柱状图中, 能勾勒出最大的矩形面积
     */
    public int largestRectangleArea(int[] heights) {
        /*
        选择 [i, j] 区间作为矩形的长, 面积是 (j - i + 1) * min 短板效应
        [2,1,5,6,2,3]
        面积最大的矩形一定是 heights 中的元素, 反证法, 如果高度不再 heights 中, 可以增加高到某根柱子的顶部, 底边长不变, 高增加, 面积变大, 和前面矛盾, 所以 高度一定在 heights 中
        枚举每个 h = heights[i], 作为矩形的高, 需要找出矩形的宽是多少:
            在 i 的左侧, 找出 小于 heights[i] 最近 下标 left, 如果不存在, 则为 -1, 左边柱子为 left + 1, left = -1 时, 表示为最左边的柱子
            在 i 的右侧, 找出 小于 heights[i] 最近 下标 right, 如果不存在, 则为 n, 右边柱子为 right - 1, right = n 时, 表示为最右边的柱子
            长为 (right - 1) - (left + 1) + 1 = right - left - 1;
        问题变成怎么快速计算: heights[i] 的 left 和 right
         */
        int ans = 0;

        return ans;
    }

}
