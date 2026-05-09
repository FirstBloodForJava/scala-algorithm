package com.oycm.hot100.two_points;

public class Solution_2 {

    /**
     * 11. <a href="https://leetcode.cn/problems/container-with-most-water/description/">盛最多水的容器</a>
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        /*
        给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
         */
        /*
        容器的容量由 长 * 宽决定，初始化 l = 0, r = height.length - 1;
            计算 [l, r] 的容量 (r - l) * min(height[l], height[r]);
            如果 height[l] < height[r], l 和 (l, r) 之间组成容量都不会超过当前计算容量，固定 r 不变，右移 l
            height[l] > height[r] 同理
            相等时，由于移动使得长越来越短，需要找到两个更高的柱子，才能取到更大的容量，所有只有当左右两边都找到比前面相等时更高的柱子时，才有可能产生新答案
         */
        int ans = 0;

        return ans;
    }

}
