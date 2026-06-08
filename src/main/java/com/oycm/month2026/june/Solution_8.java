package com.oycm.month2026.june;

import java.util.ArrayList;
import java.util.List;

public class Solution_8 {

    /**
     * 2161. <a href="https://leetcode.cn/problems/partition-array-according-to-given-pivot/">根据给定数字划分数组</a> 1338
     *
     * @param nums
     * @param pivot
     * @return
     */
    public int[] pivotArray(int[] nums, int pivot) {
        /*
        给你一个下标从 0 开始的整数数组 nums 和一个整数 pivot 。请你将 nums 重新排列，使得以下条件均成立：
            所有小于 pivot 的元素都出现在所有大于 pivot 的元素 之前 。
            所有等于 pivot 的元素都出现在小于和大于 pivot 的元素 中间 。
            小于 pivot 的元素之间和大于 pivot 的元素之间的 相对顺序 不发生改变。
         */
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                a.add(i);
            } else if (nums[i] == pivot) {
                b.add(i);
            } else {
                c.add(i);
            }
        }
        int[] ans = new int[n];
        int idx = 0;
        for (int i : a) ans[idx++] = nums[i];
        for (int i : b) ans[idx++] = nums[i];
        for (int i : c) ans[idx++] = nums[i];

        return ans;
    }

}
