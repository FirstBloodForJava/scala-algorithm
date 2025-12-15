package com.oycm.algorithm.h.basic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_19 {

    /**
     * 2154. <a href="https://leetcode.cn/problems/keep-multiplying-found-values-by-two/description/">将找到的值乘以 2</a> 1236
     *
     * @param nums
     * @param original
     * @return
     */
    public int findFinalValue(int[] nums, int original) {
        /*
        查找 nums 中是否存在 original 存在 则 original * 2 继续查找，找不到则结束查找
        todo 优化思路 nums 元素要是 original 2^k 倍数
         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(original)) {
            original *= 2;
        }
        return original;
    }
}
