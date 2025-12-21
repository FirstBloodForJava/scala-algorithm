package com.oycm.algorithm.h.fill;

import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 421. <a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/">数组中两个数的最大异或值</a>
     *
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {
        /*
        题解：nums[i] 的最高二进制位位 从高到低, 看每次的 值是否能填 1
        再 通过 高位 newValue = fill & num[i], newValue = a ^ b
        再通过枚举有维护左 查找是否有符合条件的答案 newValue ^ a == b
         */
        int ans = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int k = 31 - Integer.numberOfLeadingZeros(max);
        Set<Integer> set = new HashSet<>();
        int fill = 0;
        for (int i = k; i >= 0; i--) {
            set.clear();
            // a ^ b = newValue
            // newValue ^ b == a; newValue ^ a == b
            // 不断保留 高位 1
            fill |= 1 << i;
            int newValue = ans | (1 << i);
            for (int num : nums) {
                // 这里和 fill 取交集的原因是 newValue = 110
                num &= fill;
                if (set.contains(num ^ newValue)) {
                    // 这个位置填 1
                    ans = newValue;
                    break;
                }
                set.add(num);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

}
