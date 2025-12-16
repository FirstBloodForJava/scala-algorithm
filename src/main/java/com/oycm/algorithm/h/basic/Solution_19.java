package com.oycm.algorithm.h.basic;

import java.util.HashSet;
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

    public int optimizeSet(int[] nums, int original) {
        /*
        set 只记录 original 的 2 的幂次 值
         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            int k = num / original;
            if (num % original == 0 && (k & (k - 1)) == 0) {
                set.add(num);
            }
        }
        while (set.contains(original)) {
            original *= 2;
        }
        return original;
    }

    public int optimizeSpace(int[] nums, int original) {
        /*
        主要看所有 k 进行或运算后，最低位的 0 在哪里，结果就是 original * (1 << idx)
        现在就是怎么找出最低位的 0 的位置，取反后最低位的1
            1101 连续 + 1 可进位 与取反的交集就是 低位 0 对应的值
            0010
            (cnt + 1) & ~cnt
        也可以通过 取反和取反的负值找最低位 1
            cnt = ~cnt
            cnt & -cnt
         */
        int cnt = 0;
        for (int num : nums) {
            int k = num / original;
            if (num % original == 0 && (k & (k - 1)) == 0) {
                cnt |= k;
            }
        }

        return original * ((cnt + 1) & ~cnt);
    }
}
