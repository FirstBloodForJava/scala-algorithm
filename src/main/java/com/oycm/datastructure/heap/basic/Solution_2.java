package com.oycm.datastructure.heap.basic;

import java.util.PriorityQueue;

/**
 * @author ouyangcm
 * create 2025/12/1
 */
public class Solution_2 {

    /**
     * 3264. <a href="https://leetcode.cn/problems/final-array-state-after-k-multiplication-operations-i/">K 次乘运算后的最终数组 I</a> 1178
     *
     * <p>
     * 对 nums 数组执行 k 次操作，每次操作中：
     * <p>- 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。</p>
     * - 将 x 替换为 x * multiplier
     * <p>
     * 求执行 k 次操作后的 nums 数组
     *
     * @param nums       正整数数组
     * @param k          正整数
     * @param multiplier 正整数
     * @return
     */
    public int[] getFinalState(int[] nums, int k, int multiplier) {

        PriorityQueue<int[]> heap = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] < o2[0]) {
                return -1;
            } else if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return 1;
            }
        }));
        for (int i = 0; i < nums.length; i++) {
            heap.add(new int[]{nums[i], i});
        }
        while (k > 0) {
            int[] temp = heap.poll();
            temp[0] = temp[0] * multiplier;
            nums[temp[1]] = temp[0];
            heap.add(temp);
            k--;
        }

        return nums;
    }


}
