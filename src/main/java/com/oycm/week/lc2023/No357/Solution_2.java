package com.oycm.week.lc2023.No357;

import java.util.List;

public class Solution_2 {

    /**
     * 2811. 判断是否能拆分数组
     * <br>
     * 2811. <a href="https://leetcode.cn/problems/check-if-it-is-possible-to-split-array/description/">判断是否能拆分数组</a> 1543
     *
     * @param nums
     * @param m
     * @return
     */
    public boolean canSplitArray(List<Integer> nums, int m) {
        /*
        给你一个长度为 n 的数组 nums 和一个整数 m 。请你判断能否执行一系列操作，将数组拆分成 n 个 非空 数组。
        一个数组被称为 好 的，如果：
            子数组的长度为 1 ，或者
            子数组元素之和 大于或等于 m 。
        在每一步操作中，你可以选择一个 长度至少为 2 的现有数组（之前步骤的结果） 并将其拆分成 2 个子数组，而得到的 每个 子数组都需要是好的。
         */
        /*
        先计算 nums 元素。
         */
        /*
        脑筋急转弯，
        当 n <= 2 时，可以直接拆分。
        当 n >= 3 时，不管怎么拆分，最终都会剩下一个长为 2 的子数组，只有它的和大于等于 m，才能得到这个长为 2 的子数组。
        问题转换成，数组中是否存在长为 2 的子数组，和 大于等于 m
         */
        int n = nums.size();
        if (n <= 2) return true;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) + nums.get(i - 1) >= m) return true;
        }
        return false;
    }


}
