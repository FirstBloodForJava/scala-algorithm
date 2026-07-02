package com.oycm.dualweek.lc2024.No139;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 3285. 找到稳定山的下标
     * <br>
     * 3285. <a href="https://leetcode.cn/problems/find-indices-of-stable-mountains/description/">找到稳定山的下标</a> 1166
     *
     * @param height
     * @param threshold
     * @return
     */
    public List<Integer> stableMountains(int[] height, int threshold) {
        /*
        有 n 座山排成一列，每座山都有一个高度。给你一个整数数组 height ，其中 height[i] 表示第 i 座山的高度，再给你一个整数 threshold。
        对于下标不为 0 的一座山，如果它左侧相邻的山的高度 严格大于 threshold ，那么我们称它是 稳定 的。我们定义下标为 0 的山 不是 稳定的。
         */
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] > threshold) ans.add(i + 1);
        }
        return ans;
    }

}
