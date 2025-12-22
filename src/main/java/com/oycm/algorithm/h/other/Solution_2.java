package com.oycm.algorithm.h.other;


import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    /**
     * 260. <a href="https://leetcode.cn/problems/single-number-iii/description/">只出现一次的数字 III</a>
     *
     * @param nums 恰好有两个元素只出现一次, 其他元素均出现 2 次
     * @return
     */
    public static int[] singleNumber(int[] nums) {
        /*
        根据 136 题, nums 的异或和 就是只出现 一次元素的异或和
        先计算 nums 的所有异或和, xor == a ^ b, xor ^ a == b, 哈希表的方式查找另外的一个值 空间复杂度是 O(n) 的
         */

        if (nums.length == 2) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
            map.merge(num, 1, Integer::sum);
        }
        for (int num : nums) {
            if (map.getOrDefault(num ^ xor, 0) == 1) {
                return new int[]{num, num ^ xor};
            }
        }

        return nums;
    }

    public int[] answer(int[] nums) {
        /*
        题解思路: 
        nums 数组的异或和 某个 为 1 的比特位, 其中一个数 肯定是 1, 另外一个也肯定为 0
        再次遍历的时 将 lowbit 是 1 的数分为 1组, 为 0 的数分为一组, 这样最终的异或和就是 答案
        问题转换成了 136
         */
        if (nums.length == 2) {
            return nums;
        }
        int xor1 = 0;
        for (int num : nums) {
            xor1 ^= num;
        }
        int lowbit = xor1 & -xor1;
        int xor2 = xor1;
        // 这里也可以调整重新计算异或和
        for (int num : nums) {
            if ((lowbit & num) > 0) {
                xor1 ^= num;
            }
        }

        return new int[]{xor1, xor1 ^ xor2};
    }

    public static void main(String[] args) {
        singleNumber(new int[]{7, 1, 7, 2, 6, 6, 3, 1});
    }

}
